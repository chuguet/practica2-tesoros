package com.movember.treasure.model.bean;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class EstadisticaUsuario.
 */
public class EstadisticaUsuario extends AbstractBean{

	/** The usuario. */
	private Usuario usuario;

	/** The num_rutas_terminadas. */
	private Integer rutas_terminadas;

	/** The num_hitos_terminados. */
	private Integer hitos_terminados;

	/** The porcentaje_rutas_hitos. */
	private List<RutaHitoPorcentaje> porcentaje_rutas_hitos;

	/** The num_rutas_totales. */
	private Integer num_rutas_totales;

	/** The num_hitos_totales. */
	private Integer num_hitos_totales;

	/**
	 * Gets the usuario.
	 * 
	 * @return the usuario
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * Sets the usuario.
	 * 
	 * @param usuario
	 *            the new usuario
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	/**
	 * Gets the porcentaje_rutas_hitos.
	 * 
	 * @return the porcentaje_rutas_hitos
	 */
	public List<RutaHitoPorcentaje> getPorcentaje_rutas_hitos() {
		return porcentaje_rutas_hitos;
	}

	/**
	 * Sets the porcentaje_rutas_hitos.
	 * 
	 * @param porcentaje_rutas_hitos
	 *            the new porcentaje_rutas_hitos
	 */
	public void setPorcentaje_rutas_hitos(
			List<RutaHitoPorcentaje> porcentaje_rutas_hitos) {
		this.porcentaje_rutas_hitos = porcentaje_rutas_hitos;
	}

	/**
	 * Gets the num_rutas_totales.
	 * 
	 * @return the num_rutas_totales
	 */
	public Integer getNum_rutas_totales() {
		return num_rutas_totales;
	}

	/**
	 * Sets the num_rutas_totales.
	 * 
	 * @param num_rutas_totales
	 *            the new num_rutas_totales
	 */
	public void setNum_rutas_totales(Integer num_rutas_totales) {
		this.num_rutas_totales = num_rutas_totales;
	}

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

	public Integer getHitos_terminados() {
		return hitos_terminados;
	}

	public void setHitos_terminados(Integer hitos_terminados) {
		this.hitos_terminados = hitos_terminados;
	}

	public Integer getRutas_terminadas() {
		return rutas_terminadas;
	}

	public void setRutas_terminadas(Integer rutas_terminadas) {
		this.rutas_terminadas = rutas_terminadas;
	}

}
