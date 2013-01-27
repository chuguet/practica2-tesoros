package com.movember.treasure.model.service;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.movember.treasure.model.bean.Dispositivo;
import com.movember.treasure.model.bean.Hito;
import com.movember.treasure.model.bean.HitoDispositivo;
import com.movember.treasure.model.bean.ItemConfiguracion;
import com.movember.treasure.model.bean.Mensaje;
import com.movember.treasure.model.bean.Ruta;
import com.movember.treasure.model.bean.Usuario;
import com.movember.treasure.model.config.SpringModelConfiguration;
import com.movember.treasure.model.dao.IHitoDAO;
import com.movember.treasure.model.exception.AppException;

/**
 * The Class HitoService.
 */
@Service
class HitoService implements IHitoService {

	/** The hito dao. */
	@Inject
	private IHitoDAO hitoDAO;

	@Inject
	private IHitoDispositivoService hitoDispositivoService;

	@Inject
	private IDispositivoService dispositivoService;

	@Inject
	private IUsuarioService usuarioService;

	@Inject
	private IRutaService rutaService;

	@Inject
	private IConfiguracionService configuracionService;

	@Inject
	private SpringModelConfiguration configuration;

	public void insert(Hito hito) throws AppException {
		try {
			hitoDAO.insert(hito);
		}
		catch (SQLException e) {
			throw new AppException("Se ha producido un error al insertar un hito");
		}
	}

	public void update(Hito hito) throws AppException {
		try {
			hitoDAO.update(hito);
		}
		catch (SQLException e) {
			throw new AppException("Se ha producido un error al actualizar un hito");
		}
	}

	public void delete(Hito hito) throws AppException {
		try {
			hitoDispositivoService.eliminarDeHito(hito.getId());
			hitoDAO.delete(hito.getId());
		}
		catch (SQLException e) {
			throw new AppException("Se ha producido un error al borrar un hito");
		}
	}

	public Hito retrieve(Integer id) throws AppException {
		try {
			return hitoDAO.retrieve(id);
		}
		catch (SQLException e) {
			throw new AppException("Se ha producido un error al recuperar un hito");
		}
	}

	public List<Hito> selectAll() throws AppException {
		try {
			return hitoDAO.selectAll();
		}
		catch (SQLException e) {
			throw new AppException("Se ha producido un error al recuperar un hito");
		}
	}

	public List<Hito> recuperarDeRuta(Integer idRuta) throws AppException {
		try {
			return this.hitoDAO.recuperarDeRuta(idRuta);
		}
		catch (SQLException e) {
			throw new AppException("Se ha producido un error al recuperar los hitos de una ruta");
		}
	}

	public List<String> checkHito(Hito hito, String uuid) throws AppException {
		Dispositivo dispositivo = dispositivoService.selectByUUID(uuid);
		if (dispositivo == null) {
			throw new AppException("No se puede checkear un hito si no está reconocido el dispositivo.");
		}

		Usuario usuario = usuarioService.selectByIdDispositivo(dispositivo.getId());
		Integer identificado = (usuario != null) ? 1 : 0;

		Hito hitoBBDD = this.recuperarPorCodigo(hito.getCodigo());
		if (hitoBBDD == null) {
			throw new AppException("No se puede checkear un hito que no existe en el sistema.");
		}

		Ruta ruta = this.rutaService.retrieve(hitoBBDD.getId_ruta());
		Date fechaActual = new Date();
		if (ruta.getFecha_fin().before(fechaActual) || ruta.getFecha_inicio().after(fechaActual)) {
			throw new AppException("La ruta a la que está asociado este hito no esta activa en este momento");
		}

		// Comprobamos que ese hito aun no se haya chequeado
		List<HitoDispositivo> hitosCheckeados = this.hitoDispositivoService.selectByCriterios(hitoBBDD.getId(), dispositivo.getId(), null, null, identificado, null, null);
		if (hitosCheckeados.size() > 0) {
			throw new AppException("Ya ha checkeado este hito con anterioridad.");
		}
		HitoDispositivo hitoDispositivo = new HitoDispositivo();
		hitoDispositivo.setId_hito(hitoBBDD.getId());
		hitoDispositivo.setId_dispositivo(dispositivo.getId());
		hitoDispositivo.setLongitud(hito.getLongitud());
		hitoDispositivo.setLatitud(hito.getLatitud());
		hitoDispositivo.setIdentificado(identificado);
		hitoDispositivo.setFecha(new Date());

		if (!verificarDistancia(hitoDispositivo, hitoBBDD)) {
			throw new AppException("Está haciendo trampas, puesto que no está a menos de 50 metros del hito");
		}

		this.hitoDispositivoService.insert(hitoDispositivo);

		// Si el numero de hitos chequeados mas el que acabamos de chequear es
		// igual al numero de hitos
		// necesarios para conseguir el premio, se manda el premio al movil
		hitosCheckeados = this.hitoDispositivoService.selectByCriterios(null, dispositivo.getId(), null, null, null, null, ruta.getId());

		List<String> mensajes = new ArrayList<String>();
		mensajes.add(hitoBBDD.getPista());
		String premio = "";
		if (ruta.getHitos_necesarios().equals(hitosCheckeados.size())) {
			premio = (identificado.equals(1)) ? ruta.getPremio_identificados() : ruta.getPremio_no_identificados();
		}

		mensajes.add(premio);
		mensajes.add(this.recuperarFelicitacionPorDispositivo(dispositivo.getId()));
		return mensajes;
	}

	private boolean verificarDistancia(HitoDispositivo hitoDispositivo, Hito hitoBBDD) throws AppException {
		ItemConfiguracion controlDistancia = this.configuracionService.recuperarItemConfiguracion("controlDistancia");
		if (controlDistancia.getValor().equals(new Integer(1))) {
			ItemConfiguracion distanciaMaxima = this.configuracionService.recuperarItemConfiguracion("distanciaMaxima");
			Double lat1 = new Double(hitoDispositivo.getLatitud());
			Double lon1 = new Double(hitoDispositivo.getLongitud());
			Double lat2 = new Double(hitoBBDD.getLatitud());
			Double lon2 = new Double(hitoBBDD.getLongitud());
			double toRad = 0.0174532925;
			double dLat = (lat2 - lat1) * toRad;
			double dLon = (lon2 - lon1) * toRad;
			double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(lat1 * toRad) * Math.cos(lat2 * toRad) * Math.sin(dLon / 2) * Math.sin(dLon / 2);
			double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
			// radio de la tierra en metros
			double distancia = 6371000 * c;

			return (distancia <= distanciaMaxima.getValor());
		}
		else {
			return true;
		}
	}

	private String recuperarFelicitacionPorDispositivo(Integer idDispositivo) throws AppException {
		String felicitacion = "";
		List<HitoDispositivo> hitosCheckeados = this.hitoDispositivoService.selectByCriterios(null, idDispositivo, null, null, null, null, null);
		Integer numeroHitosCheckeados = hitosCheckeados.size();
		List<Mensaje> mensajes = this.configuracionService.recuperarMensajes();

		for (Mensaje mensaje : mensajes) {
			if (numeroHitosCheckeados.equals(mensaje.getId())) {
				felicitacion = mensaje.getMensaje();
				break;
			}
		}
		return felicitacion;
	}

	public Hito recuperarPorCodigo(String codigo) throws AppException {
		try {
			return this.hitoDAO.selectByCodigo(codigo);
		}
		catch (SQLException e) {
			throw new AppException("Se ha producido un error de acceso a datos al recuperar un hito por su código");
		}
	}

	public String generarQR(String codigo) throws AppException {
		try {
			Charset charset = Charset.forName("ISO-8859-1");
			CharsetEncoder encoder = charset.newEncoder();
			byte[] b = null;

			// Convert a string to ISO-8859-1 bytes in a ByteBuffer
			ByteBuffer bbuf = encoder.encode(CharBuffer.wrap(codigo));
			b = bbuf.array();

			String data;

			data = new String(b, "ISO-8859-1");

			// get a byte matrix for the data
			BitMatrix matrix = null;
			int h = 100;
			int w = 100;
			com.google.zxing.Writer writer = new QRCodeWriter();

			matrix = writer.encode(data, com.google.zxing.BarcodeFormat.QR_CODE, w, h);

			String image = codigo.replace(' ', '_') + ".png";
			String filePath = configuration.getQrPath() + image;
			File file = new File(filePath);

			MatrixToImageWriter.writeToFile(matrix, "PNG", file);
			System.out.println("printing to " + file.getAbsolutePath());
			return image;
		}
		catch (UnsupportedEncodingException e) {
			throw new AppException("Se ha producido un error de codificación no soportado al generar el código QR");
		}
		catch (CharacterCodingException e) {
			throw new AppException("Se ha producido un error de codificación de caracteres al generar el código QR");
		}
		catch (IOException e) {
			throw new AppException("Se ha producido un error de escritura al guardar la imagen del código QR");
		}
		catch (com.google.zxing.WriterException e) {
			throw new AppException("Se ha producido un error de escritura del código QR");
		}
	}
}