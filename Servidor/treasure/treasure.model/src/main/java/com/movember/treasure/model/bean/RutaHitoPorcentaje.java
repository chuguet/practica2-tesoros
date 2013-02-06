package com.movember.treasure.model.bean;

// TODO: Auto-generated Javadoc
/**
 * The Class RutaHitoPorcentaje.
 */
public class RutaHitoPorcentaje extends AbstractBean {

	/** The num_hitos_totales. */
	private Integer num_hitos_totales;

	/** The num_hitos_checkeados. */
	private Integer num_hitos_checkeados;

	private Integer num_hitos_distintos_checkeados;

	/** The num_hitos_necesarios. */
	private Integer num_hitos_necesarios;

	private Integer num_hitos_distintos;

	/** The ruta. */
	private String ruta;

	/**
	 * Gets the num_hitos_totales.
	 * 
	 * @return the num_hitos_totales
	 */
	public Integer getNum_hitos_totales() {
		return num_hitos_totales;
	}

	/**
	 * Sets the num_hitos_totales.
	 * 
	 * @param num_hitos_totales
	 *            the new num_hitos_totales
	 */
	public void setNum_hitos_totales(Integer num_hitos_totales) {
		this.num_hitos_totales = num_hitos_totales;
	}

	/**
	 * Gets the num_hitos_checkeados.
	 * 
	 * @return the num_hitos_checkeados
	 */
	public Integer getNum_hitos_checkeados() {
		return num_hitos_checkeados;
	}

	/**
	 * Sets the num_hitos_checkeados.
	 * 
	 * @param num_hitos_checkeados
	 *            the new num_hitos_checkeados
	 */
	public void setNum_hitos_checkeados(Integer num_hitos_checkeados) {
		this.num_hitos_checkeados = num_hitos_checkeados;
	}

	/**
	 * Gets the ruta.
	 * 
	 * @return the ruta
	 */
	public String getRuta() {
		return ruta;
	}

	/**
	 * Sets the ruta.
	 * 
	 * @param ruta
	 *            the new ruta
	 */
	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	/**
	 * Gets the num_hitos_necesarios.
	 * 
	 * @return the num_hitos_necesarios
	 */
	public Integer getNum_hitos_necesarios() {
		return num_hitos_necesarios;
	}

	/**
	 * Sets the num_hitos_necesarios.
	 * 
	 * @param num_hitos_necesarios
	 *            the new num_hitos_necesarios
	 */
	public void setNum_hitos_necesarios(Integer num_hitos_necesarios) {
		this.num_hitos_necesarios = num_hitos_necesarios;
	}

	public void setNum_hitos_distintos(Integer num_hitos_distintos) {
		this.num_hitos_distintos = num_hitos_distintos;
	}

	public Integer getNum_hitos_distintos() {
		return num_hitos_distintos;
	}

	public void setNum_hitos_distintos_checkeados(Integer num_hitos_distintos_checkeados) {
		this.num_hitos_distintos_checkeados = num_hitos_distintos_checkeados;
	}

	public Integer getNum_hitos_distintos_checkeados() {
		return num_hitos_distintos_checkeados;
	}
}
