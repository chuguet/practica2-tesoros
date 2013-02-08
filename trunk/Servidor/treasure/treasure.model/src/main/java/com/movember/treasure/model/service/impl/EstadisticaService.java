package com.movember.treasure.model.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import com.movember.treasure.model.bean.EstadisticaHito;
import com.movember.treasure.model.bean.EstadisticaRuta;
import com.movember.treasure.model.bean.EstadisticaUsuario;
import com.movember.treasure.model.bean.Hito;
import com.movember.treasure.model.bean.ParametrosHito;
import com.movember.treasure.model.bean.Ruta;
import com.movember.treasure.model.bean.RutaHitoPorcentaje;
import com.movember.treasure.model.dao.IEstadisticaDAO;
import com.movember.treasure.model.exception.AppException;
import com.movember.treasure.model.service.IEstadisticaService;
import com.movember.treasure.model.service.IHitoService;
import com.movember.treasure.model.service.IRutaService;
import com.movember.treasure.model.service.IUsuarioService;

/**
 * The Class EstadisticaService.
 */
@Service
class EstadisticaService implements IEstadisticaService {

	@Inject
	private IEstadisticaDAO estadisticaDAO;
	@Inject
	private IRutaService rutaService;
	@Inject
	private IUsuarioService usuarioService;
	@Inject
	private IHitoService hitoService;

	public EstadisticaRuta retrieveEstadisticaRuta(Integer pIdEncuesta) throws AppException {
		try {
			EstadisticaRuta estadistica = new EstadisticaRuta();
			Ruta ruta = rutaService.retrieve(pIdEncuesta);
			estadistica.setId(ruta.getId());
			estadistica.setFecha_fin(ruta.getFecha_fin());
			estadistica.setFecha_inicio(ruta.getFecha_inicio());
			estadistica.setRuta(ruta.getNombre());
			estadistica.setHitos(getHitoEstadistica(ruta));
			estadistica.setContador_total(getContadorTotal(estadistica.getHitos()));
			estadistica.setUsuarios_ruta_completada(estadisticaDAO.recuperarNumeroUsuariosHanTerminadoRuta(pIdEncuesta));
			return estadistica;
		}
		catch (SQLException e) {
			throw new AppException("Se ha producido un error al recuperar la ruta para generar las estadísticas");
		}
	}

	private List<EstadisticaHito> getHitoEstadistica(Ruta ruta) throws AppException {
		try {
			List<EstadisticaHito> hitos = new ArrayList<EstadisticaHito>();
			EstadisticaHito hitoEstadistica;
			for (Hito hito : ruta.getHitos()) {
				hitoEstadistica = new EstadisticaHito();
				hitoEstadistica.setId(hito.getId());
				hitoEstadistica.setLatitud(hito.getLatitud());
				hitoEstadistica.setLongitud(hito.getLongitud());
				hitoEstadistica.setNombre(hito.getNombre());
				hitoEstadistica.setCodigo(hito.getCodigo());
				hitoEstadistica.setContador_usuarios_identificados(estadisticaDAO.recuperarHitosUsuIdent(ruta.getId()).get(hito.getId()));
				hitoEstadistica.setContador_no_usuarios_identificados(estadisticaDAO.recuperarHitosUsuNoIdent(ruta.getId()).get(hito.getId()));
				if (hitoEstadistica.getContador_no_usuarios_identificados() == null) {
					hitoEstadistica.setContador_no_usuarios_identificados(0);
				}
				if (hitoEstadistica.getContador_usuarios_identificados() == null) {
					hitoEstadistica.setContador_usuarios_identificados(0);
				}
				hitos.add(hitoEstadistica);
			}
			return hitos;
		}
		catch (SQLException e) {
			throw new AppException("Se ha producido un error al recuperar los hitos de una ruta para generar las estadísticas");
		}
	}

	private Integer getContadorTotal(List<EstadisticaHito> hitos) {
		Integer contador = 0;
		for (EstadisticaHito hitoEstadistica : hitos) {
			contador = contador + hitoEstadistica.getContador_no_usuarios_identificados() + hitoEstadistica.getContador_usuarios_identificados();
		}
		return contador;
	}

	/*
	 * (non-Javadoc)
	 * @see com.movember.treasure.model.service.IEstadisticaService#
	 * retrieveEstadisticaUsuario(java.lang.Integer)
	 */
	public EstadisticaUsuario retrieveEstadisticaUsuario(Integer pIdUsuario) throws AppException {
		try {
			EstadisticaUsuario estadisticaUsuario = new EstadisticaUsuario();

			// Recuperamos el usuario
			estadisticaUsuario.setUsuario(usuarioService.retrieve(pIdUsuario));
			// Seteamos el id de usuario
			estadisticaUsuario.setId(pIdUsuario);
			// Recuperamos numero totales de rutas y de hitos
			estadisticaUsuario.setNum_hitos_totales(estadisticaDAO.recuperarNumeroTotalesHitos());
			estadisticaUsuario.setNum_rutas_totales(estadisticaDAO.recuperarNumeroTotalesRutas());
			// Recuperamos las rutas terminadas e hitos terminados
			List<Hito> hitosTerminados = estadisticaDAO.recuperarNumeroHitosTerminados(pIdUsuario);
			estadisticaUsuario.setHitos_terminados(toHitoEstadistica(hitosTerminados, estadisticaUsuario.getUsuario().getId_dispositivo()));

			// estadisticaUsuario.setRutas_terminadas(rutasTerminadas.size());
			List<RutaHitoPorcentaje> listaRutaHitoPorcentaje = new ArrayList<RutaHitoPorcentaje>();
			Integer numHitosTerminados = 0;
			for (Ruta ruta : rutaService.selectAll()) {
				RutaHitoPorcentaje rutaHitoPorcentaje = new RutaHitoPorcentaje();
				rutaHitoPorcentaje.setId(ruta.getId());
				rutaHitoPorcentaje.setRuta(ruta.getNombre());
				rutaHitoPorcentaje.setNum_hitos_necesarios(ruta.getHitos_necesarios());
				rutaHitoPorcentaje.setNum_hitos_distintos(ruta.getHitos_distintos());
				List<Hito> hitoPorcentaje = getListHitosDeRuta(ruta.getId(), hitosTerminados);
				if (hitoPorcentaje.size() > 0) {
					// si se ha checkeado algun hito se muestran las
					// estadisticas de ruta
					rutaHitoPorcentaje.setNum_hitos_checkeados(hitoPorcentaje.size());
					rutaHitoPorcentaje.setNum_hitos_distintos_checkeados(this.recuperarNumeroHitosDistintosCheckeados(hitoPorcentaje));
					rutaHitoPorcentaje.setNum_hitos_totales(hitoService.recuperarDeRuta(ruta.getId()).size());

					if (rutaHitoPorcentaje.getNum_hitos_checkeados() >= rutaHitoPorcentaje.getNum_hitos_necesarios() && rutaHitoPorcentaje.getNum_hitos_distintos_checkeados() >= rutaHitoPorcentaje.getNum_hitos_distintos()) {
						estadisticaUsuario.setRutas_terminadas(estadisticaUsuario.getRutas_terminadas() + 1);
					}

					numHitosTerminados += rutaHitoPorcentaje.getNum_hitos_distintos_checkeados();
					listaRutaHitoPorcentaje.add(rutaHitoPorcentaje);
				}
			}
			estadisticaUsuario.setNum_hitos_terminados(hitosTerminados.size());
			estadisticaUsuario.setPorcentaje_rutas_hitos(listaRutaHitoPorcentaje);

			return estadisticaUsuario;
		}
		catch (SQLException e) {
			throw new AppException("Se ha producido un error al calcular las estadísticas de un usuario");
		}
	}

	private Integer recuperarNumeroHitosDistintosCheckeados(List<Hito> hitosCheckeados) {
		List<Integer> hitosDistintos = new ArrayList<Integer>();
		for (Hito hito : hitosCheckeados) {
			if (!hitosDistintos.contains(hito.getId())) {
				hitosDistintos.add(hito.getId());
			}
		}
		return hitosDistintos.size();
	}

	private List<EstadisticaHito> toHitoEstadistica(List<Hito> hitosTerminados, Integer idDispositivo) throws AppException {
		try {
			List<EstadisticaHito> result = new ArrayList<EstadisticaHito>();
			EstadisticaHito hitoEstadistica;
			for (Hito hito : hitosTerminados) {
				hitoEstadistica = new EstadisticaHito();
				hitoEstadistica.setId(hito.getId());
				hitoEstadistica.setLatitud(hito.getLatitud());
				hitoEstadistica.setLongitud(hito.getLongitud());
				hitoEstadistica.setCodigo(hito.getCodigo());
				hitoEstadistica.setNombre(hito.getNombre());
				hitoEstadistica.setFecha_checkin(this.estadisticaDAO.recuperarFechaCheckin(new ParametrosHito(hito.getId(), idDispositivo)));
				result.add(hitoEstadistica);
			}
			return result;
		}
		catch (SQLException e) {
			throw new AppException("Se ha producido un error al calcular las estadísticas de los hitos de una ruta");
		}
	}

	private List<Hito> getListHitosDeRuta(Integer idRuta, List<Hito> hitosTerminados) {
		List<Hito> result = new ArrayList<Hito>();
		for (Hito hito : hitosTerminados) {
			if (hito.getId_ruta().equals(idRuta)) {
				result.add(hito);
			}
		}
		return result;
	}

	public EstadisticaHito recuperarContadorHitoPorDias(Integer idHito) throws AppException {
		try {
			EstadisticaHito estadisticaHito = new EstadisticaHito();
			Hito hito = this.hitoService.retrieve(idHito);
			estadisticaHito.setId(hito.getId());
			estadisticaHito.setLatitud(hito.getLatitud());
			estadisticaHito.setLongitud(hito.getLongitud());
			estadisticaHito.setCodigo(hito.getCodigo());
			estadisticaHito.setNombre(hito.getNombre());

			Map<Date, Long> contadorIdentificados = this.estadisticaDAO.recuperarContadorHitoPorDiasIdentificado(idHito);
			Map<Date, Long> contadorNoIdentificados = this.estadisticaDAO.recuperarContadorHitoPorDiasNoIdentificado(idHito);
			Map<Date, Long> contadorHitoPorDiasIdentificado = new HashMap<Date, Long>();
			Map<Date, Long> contadorHitoPorDiasNoIdentificado = new HashMap<Date, Long>();
			Ruta rutaDelHito = this.rutaService.retrieve(hito.getId_ruta());

			Date fechaInicio = rutaDelHito.getFecha_inicio();
			Date fechaFin = rutaDelHito.getFecha_fin();
			Long contadorIdent, contadorNoIdent;
			while (fechaInicio.before(fechaFin) || fechaInicio.equals(fechaFin)) {
				contadorIdent = (contadorIdentificados.containsKey(fechaInicio)) ? contadorIdentificados.get(fechaInicio) : new Long(0);
				contadorHitoPorDiasIdentificado.put(fechaInicio, contadorIdent);

				contadorNoIdent = (contadorNoIdentificados.containsKey(fechaInicio)) ? contadorNoIdentificados.get(fechaInicio) : new Long(0);
				contadorHitoPorDiasNoIdentificado.put(fechaInicio, contadorNoIdent);

				fechaInicio = this.addDays(fechaInicio, 1);
			}

			estadisticaHito.setContadorPorDiasIdentificado(contadorHitoPorDiasIdentificado);
			estadisticaHito.setContadorPorDiasNoIdentificado(contadorHitoPorDiasNoIdentificado);
			return estadisticaHito;
		}
		catch (SQLException e) {
			throw new AppException("Se ha producido un error al recuperar las estadísticas de checkin de un hito");
		}
	}

	private Date addDays(Date date, int days) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, days); // minus number would decrement the days
		return cal.getTime();
	}
}