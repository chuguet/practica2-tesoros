package com.movember.treasure.controller.dto;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.movember.treasure.model.bean.EstadisticaUsuario;
import com.movember.treasure.model.bean.RutaHitoPorcentaje;
import com.movember.treasure.model.exception.AppException;

// TODO: Auto-generated Javadoc
/**
 * The Class EstadisticaUsuarioDTO.
 */
public class EstadisticaUsuarioDTO extends AbstractDTO {

	private Integer id;

	/** The Constant df. */
	private static final DecimalFormat df = new DecimalFormat("0.00");

	/** The num_hitos_terminados. */
	private Integer num_hitos_terminados;

	/** The num_hitos_totales. */
	private Integer num_hitos_totales;

	/** The num_rutas_terminadas. */
	private Integer num_rutas_terminadas;

	/** The num_rutas_totales. */
	private Integer num_rutas_totales;

	/** The porcentaje_hitos_totales. */
	private String porcentaje_hitos_totales;

	/** The porcentaje_rutas_hitos. */
	private List<RutaHitoPorcentajeDTO> porcentaje_rutas_hitos;

	/** The porcentaje_rutas_totales. */
	private String porcentaje_rutas_totales;

	/** The usuario. */
	private String usuario;

	/**
	 * Calculo porcentaje.
	 * 
	 * @param rutas_terminadas
	 *            the rutas_terminadas
	 * @param num_rutas_totales
	 *            the num_rutas_totales
	 * @return the string
	 */
	private String calculoPorcentaje(Integer rutas_terminadas,
			Integer num_rutas_totales) {
		Float result = new Float("0.00");
		Float parcial = Float.valueOf(rutas_terminadas);
		Float total = Float.valueOf(num_rutas_totales);
		if (total.compareTo(result) > 0) {
			result = parcial / total * 100;
		}
		return df.format(result).toString() + "%";
	}

	public Integer getNum_hitos_terminados() {
		return num_hitos_terminados;
	}

	public Integer getNum_hitos_totales() {
		return num_hitos_totales;
	}

	public Integer getNum_rutas_terminadas() {
		return num_rutas_terminadas;
	}

	public Integer getNum_rutas_totales() {
		return num_rutas_totales;
	}

	/**
	 * Gets the porcentaje_hitos_totales.
	 * 
	 * @return the porcentaje_hitos_totales
	 */
	public String getPorcentaje_hitos_totales() {
		return porcentaje_hitos_totales;
	}

	/**
	 * Gets the porcentaje_rutas_hitos.
	 * 
	 * @return the porcentaje_rutas_hitos
	 */
	public List<RutaHitoPorcentajeDTO> getPorcentaje_rutas_hitos() {
		return porcentaje_rutas_hitos;
	}

	/**
	 * Gets the porcentaje_rutas_totales.
	 * 
	 * @return the porcentaje_rutas_totales
	 */
	public String getPorcentaje_rutas_totales() {
		return porcentaje_rutas_totales;
	}

	/**
	 * Gets the usuario.
	 * 
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	public void setNum_hitos_terminados(Integer num_hitos_terminados) {
		this.num_hitos_terminados = num_hitos_terminados;
	}

	public void setNum_hitos_totales(Integer num_hitos_totales) {
		this.num_hitos_totales = num_hitos_totales;
	}

	public void setNum_rutas_terminadas(Integer num_rutas_terminadas) {
		this.num_rutas_terminadas = num_rutas_terminadas;
	}

	public void setNum_rutas_totales(Integer num_rutas_totales) {
		this.num_rutas_totales = num_rutas_totales;
	}

	/**
	 * Sets the porcentaje_hitos_totales.
	 * 
	 * @param porcentaje_hitos_totales
	 *            the new porcentaje_hitos_totales
	 */
	public void setPorcentaje_hitos_totales(String porcentaje_hitos_totales) {
		this.porcentaje_hitos_totales = porcentaje_hitos_totales;
	}

	/**
	 * Sets the porcentaje_rutas_hitos.
	 * 
	 * @param porcentaje_rutas_hitos
	 *            the new porcentaje_rutas_hitos
	 */
	public void setPorcentaje_rutas_hitos(
			List<RutaHitoPorcentajeDTO> porcentaje_rutas_hitos) {
		this.porcentaje_rutas_hitos = porcentaje_rutas_hitos;
	}

	/**
	 * Sets the porcentaje_rutas_totales.
	 * 
	 * @param porcentaje_rutas_totales
	 *            the new porcentaje_rutas_totales
	 */
	public void setPorcentaje_rutas_totales(String porcentaje_rutas_totales) {
		this.porcentaje_rutas_totales = porcentaje_rutas_totales;
	}

	/**
	 * Sets the usuario.
	 * 
	 * @param usuario
	 *            the new usuario
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.movember.treasure.controller.dto.AbstractDTO#toBusiness(java.lang
	 * .Object)
	 */
	@Override
	public void toBusiness(Object object) throws AppException {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.movember.treasure.controller.dto.AbstractDTO#toRest(java.lang.Object)
	 */
	@Override
	public void toRest(Object object) throws AppException {
		EstadisticaUsuario estadisticaUsuario = (EstadisticaUsuario) object;

		this.id = estadisticaUsuario.getId();
		this.porcentaje_hitos_totales = calculoPorcentaje(
				estadisticaUsuario.getHitos_terminados(),
				estadisticaUsuario.getNum_hitos_totales());
		this.porcentaje_rutas_totales = calculoPorcentaje(
				estadisticaUsuario.getRutas_terminadas(),
				estadisticaUsuario.getNum_rutas_totales());
		this.num_hitos_terminados = estadisticaUsuario.getHitos_terminados();
		this.num_hitos_totales = estadisticaUsuario.getNum_hitos_totales();
		this.num_rutas_terminadas = estadisticaUsuario.getRutas_terminadas();
		this.num_rutas_totales = estadisticaUsuario.getNum_rutas_totales();
		RutaHitoPorcentajeDTO hitoPorcentajeDTO;
		List<RutaHitoPorcentajeDTO> listRutaHitoPorcentajeDTO = new ArrayList<RutaHitoPorcentajeDTO>();
		for (RutaHitoPorcentaje rutaHitoPorcentaje : estadisticaUsuario
				.getPorcentaje_rutas_hitos()) {
			hitoPorcentajeDTO = new RutaHitoPorcentajeDTO();
			hitoPorcentajeDTO.toRest(rutaHitoPorcentaje);
			listRutaHitoPorcentajeDTO.add(hitoPorcentajeDTO);
		}
		this.setPorcentaje_rutas_hitos(listRutaHitoPorcentajeDTO);
		this.usuario = estadisticaUsuario.getUsuario().getNombre() + " "
				+ estadisticaUsuario.getUsuario().getApellidos();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
