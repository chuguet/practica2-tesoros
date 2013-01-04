package com.movember.treasure.model.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import com.movember.treasure.model.bean.Dispositivo;
import com.movember.treasure.model.bean.Hito;
import com.movember.treasure.model.bean.HitoDispositivo;
import com.movember.treasure.model.bean.Ruta;
import com.movember.treasure.model.bean.Usuario;
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

	public String checkHito(Hito hito, String uuid) throws AppException {
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
		this.hitoDispositivoService.insert(hitoDispositivo);

		// Si el numero de hitos chequeados mas el que acabamos de chequear es
		// igual al numero de hitos
		// necesarios para conseguir el premio, se manda el premio al movil
		Ruta ruta = this.rutaService.retrieve(hitoBBDD.getId_ruta());
		hitosCheckeados = this.hitoDispositivoService.selectByCriterios(null, dispositivo.getId(), null, null, null, null, ruta.getId());

		String mensaje = "";
		if (ruta.getHitos_necesarios().equals(hitosCheckeados.size())) {
			mensaje = (identificado.equals(1)) ? ruta.getPremio_identificados() : ruta.getPremio_no_identificados();
		}
		else {
			mensaje = hitoBBDD.getPista();
		}
		return mensaje;
	}

	public Hito recuperarPorCodigo(String codigo) throws AppException {
		try {
			return this.hitoDAO.selectByCodigo(codigo);
		}
		catch (SQLException e) {
			throw new AppException("Se ha producido un error de acceso a datos al recuperar un hito por su código");
		}
	}
}