package com.movember.treasure.model.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.movember.treasure.model.bean.Estadistica;
import com.movember.treasure.model.bean.Hito;
import com.movember.treasure.model.bean.HitoEstadistica;
import com.movember.treasure.model.bean.Ruta;
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

	public Estadistica retrieve(Integer pId) throws AppException {
		Estadistica estadistica = new Estadistica();

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
}
