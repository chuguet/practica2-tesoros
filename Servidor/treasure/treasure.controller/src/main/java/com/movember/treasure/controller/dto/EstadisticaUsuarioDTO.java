package com.movember.treasure.controller.dto;

import java.util.ArrayList;
import java.util.List;

import com.movember.treasure.model.bean.EstadisticaUsuario;
import com.movember.treasure.model.bean.RutaHitoPorcentaje;
import com.movember.treasure.model.exception.AppException;

public class EstadisticaUsuarioDTO extends AbstractDTO {
	/** The num_hitos_terminados. */
	private Integer hitos_terminados;

	/** The num_hitos_totales. */
	private Integer num_hitos_totales;

	/** The num_rutas_totales. */
	private Integer num_rutas_totales;

	/** The porcentaje_rutas_hitos. */
	private List<RutaHitoPorcentajeDTO> porcentaje_rutas_hitos;

	/** The num_rutas_terminadas. */
	private Integer rutas_terminadas;

	/** The usuario. */
	private String usuario;

	public Integer getHitos_terminados() {
		return hitos_terminados;
	}

	public Integer getNum_hitos_totales() {
		return num_hitos_totales;
	}

	public Integer getNum_rutas_totales() {
		return num_rutas_totales;
	}

	public List<RutaHitoPorcentajeDTO> getPorcentaje_rutas_hitos() {
		return porcentaje_rutas_hitos;
	}

	public Integer getRutas_terminadas() {
		return rutas_terminadas;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setHitos_terminados(Integer hitos_terminados) {
		this.hitos_terminados = hitos_terminados;
	}

	public void setNum_hitos_totales(Integer num_hitos_totales) {
		this.num_hitos_totales = num_hitos_totales;
	}

	public void setNum_rutas_totales(Integer num_rutas_totales) {
		this.num_rutas_totales = num_rutas_totales;
	}

	public void setPorcentaje_rutas_hitos(
			List<RutaHitoPorcentajeDTO> porcentaje_rutas_hitos) {
		this.porcentaje_rutas_hitos = porcentaje_rutas_hitos;
	}

	public void setRutas_terminadas(Integer rutas_terminadas) {
		this.rutas_terminadas = rutas_terminadas;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	@Override
	public void toRest(Object object) throws AppException {
		EstadisticaUsuario estadisticaUsuario = (EstadisticaUsuario)object;
		
		this.hitos_terminados=estadisticaUsuario.getHitos_terminados();
		this.num_hitos_totales=estadisticaUsuario.getNum_hitos_totales();
		this.num_rutas_totales=estadisticaUsuario.getNum_rutas_totales();
		RutaHitoPorcentajeDTO hitoPorcentajeDTO;
		List<RutaHitoPorcentajeDTO> listRutaHitoPorcentajeDTO = new ArrayList<RutaHitoPorcentajeDTO>();
		for(RutaHitoPorcentaje rutaHitoPorcentaje : estadisticaUsuario.getPorcentaje_rutas_hitos()){
			hitoPorcentajeDTO = new RutaHitoPorcentajeDTO();
			hitoPorcentajeDTO.toRest(rutaHitoPorcentaje);
			listRutaHitoPorcentajeDTO.add(hitoPorcentajeDTO);
		}
		this.setPorcentaje_rutas_hitos(listRutaHitoPorcentajeDTO);
		this.rutas_terminadas=estadisticaUsuario.getRutas_terminadas();
		this.usuario=estadisticaUsuario.getUsuario().getNombre()+" "+estadisticaUsuario.getUsuario().getApellidos();
		
	}

	@Override
	public void toBusiness(Object object) throws AppException {
		// TODO Auto-generated method stub

	}

}
