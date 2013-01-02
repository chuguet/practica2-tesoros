package com.movember.treasure.model.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.movember.treasure.model.bean.EstadisticaRuta;
import com.movember.treasure.model.bean.EstadisticaUsuario;
import com.movember.treasure.model.bean.Hito;
import com.movember.treasure.model.bean.HitoEstadistica;
import com.movember.treasure.model.bean.Ruta;
import com.movember.treasure.model.bean.RutaHitoPorcentaje;
import com.movember.treasure.model.dao.IEstadisticaDAO;
import com.movember.treasure.model.exception.AppException;

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

	public EstadisticaRuta retrieveEstadisticaRuta(Integer pId)
			throws AppException {
		EstadisticaRuta estadistica = new EstadisticaRuta();

		Ruta ruta = rutaService.retrieve(pId);
		estadistica.setId(ruta.getId());
		estadistica.setFecha_fin(ruta.getFecha_fin());
		estadistica.setFecha_inicio(ruta.getFecha_inicio());
		estadistica.setRuta(ruta.getNombre());
		estadistica.setHitos(getHitoEstadistica(ruta));
		estadistica.setContador_total(getContadorTotal(estadistica.getHitos()));

		return estadistica;
	}

	private List<HitoEstadistica> getHitoEstadistica(Ruta ruta) {
		List<HitoEstadistica> hitos = new ArrayList<HitoEstadistica>();
		try {
			HitoEstadistica hitoEstadistica;
			for (Hito hito : ruta.getHitos()) {
				hitoEstadistica = new HitoEstadistica();
				hitoEstadistica.setId(hito.getId());
				hitoEstadistica.setLatitud(hito.getLatitud());
				hitoEstadistica.setLongitud(hito.getLongitud());
				hitoEstadistica.setNombre(hito.getNombre());
				hitoEstadistica
						.setContador_usuarios_identificados(estadisticaDAO
								.recuperarHitosUsuIdent(ruta.getId()).get(
										hito.getId()));
				hitoEstadistica
						.setContador_no_usuarios_identificados(estadisticaDAO
								.recuperarHitosUsuNoIdent(ruta.getId()).get(
										hito.getId()));
				if (hitoEstadistica.getContador_no_usuarios_identificados() == null) {
					hitoEstadistica.setContador_no_usuarios_identificados(0);
				}
				if (hitoEstadistica.getContador_usuarios_identificados() == null) {
					hitoEstadistica.setContador_usuarios_identificados(0);
				}
				hitos.add(hitoEstadistica);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return hitos;
	}

	private Integer getContadorTotal(List<HitoEstadistica> hitos) {
		Integer contador = 0;
		for (HitoEstadistica hitoEstadistica : hitos) {
			contador = contador
					+ hitoEstadistica.getContador_no_usuarios_identificados()
					+ hitoEstadistica.getContador_usuarios_identificados();
		}
		return contador;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.movember.treasure.model.service.IEstadisticaService#
	 * retrieveEstadisticaUsuario(java.lang.Integer)
	 */
	public EstadisticaUsuario retrieveEstadisticaUsuario(Integer pIdUsuario)
			throws AppException {
		EstadisticaUsuario estadisticaUsuario = new EstadisticaUsuario();
		try {
			// Recuperamos el usuario
			estadisticaUsuario.setUsuario(usuarioService.retrieve(pIdUsuario));
			//Seteamos el id de usuario
			estadisticaUsuario.setId(pIdUsuario);
			// Recuperamos numero totales de rutas y de hitos
			estadisticaUsuario.setNum_hitos_totales(estadisticaDAO
					.recuperarNumeroTotalesHitos());
			estadisticaUsuario.setNum_rutas_totales(estadisticaDAO
					.recuperarNumeroTotalesRutas());
			// Recuperamos las rutas terminadas e hitos terminados
			List<Hito> hitosTerminados = estadisticaDAO
					.recuperarNumeroHitosTerminados(pIdUsuario);
			List<Ruta> rutasTerminadas = estadisticaDAO
					.recuperarNumeroRutasTerminadas(pIdUsuario);
			estadisticaUsuario.setHitos_terminados(hitosTerminados.size());
			estadisticaUsuario.setRutas_terminadas(rutasTerminadas.size());
			List<RutaHitoPorcentaje> listaRutaHitoPorcentaje = new ArrayList<RutaHitoPorcentaje>();
			for (Ruta ruta : rutaService.selectAll()) {
				RutaHitoPorcentaje rutaHitoPorcentaje = new RutaHitoPorcentaje();
				rutaHitoPorcentaje.setId(ruta.getId());
				rutaHitoPorcentaje.setRuta(ruta.getNombre());
				rutaHitoPorcentaje.setNum_hitos_necesarios(ruta
						.getHitos_necesarios());
				List<Hito> hitoRutas = getListHitosDeRuta(ruta.getId(),
						hitoService.recuperarDeRuta(ruta.getId()));
				List<Hito> hitoPorcentaje = getListHitosDeRuta(ruta.getId(),
						hitosTerminados);
				rutaHitoPorcentaje.setNum_hitos_checkeados(hitoPorcentaje
						.size());
				rutaHitoPorcentaje.setNum_hitos_totales(hitoRutas.size());
				listaRutaHitoPorcentaje.add(rutaHitoPorcentaje);
			}
			estadisticaUsuario
					.setPorcentaje_rutas_hitos(listaRutaHitoPorcentaje);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return estadisticaUsuario;
	}

	private List<Hito> getListHitosDeRuta(Integer idRuta,
			List<Hito> hitosTerminados) {
		List<Hito> result = new ArrayList<Hito>();
		for (Hito hito : hitosTerminados) {
			if (hito.getId_ruta().equals(idRuta)) {
				result.add(hito);
			}
		}
		return result;
	}
}
