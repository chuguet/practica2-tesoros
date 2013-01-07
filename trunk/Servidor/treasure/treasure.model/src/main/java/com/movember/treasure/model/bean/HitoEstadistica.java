package com.movember.treasure.model.bean;

import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * The Class EncuestaContestada.
 */
public class HitoEstadistica extends AbstractBean {

	/** The contador_no_usuarios_identificados. */
	private Integer contador_no_usuarios_identificados;

	/** The contador_usuarios_identificados. */
	private Integer contador_usuarios_identificados;

	/** The latitud. */
	private String latitud;

	/** The longitud. */
	private String longitud;

	/** The nombre. */
	private String nombre;
	
	private String codigo;
	
	private Date fecha_checkin;

	/**
	 * Gets the contador_no_usuarios_identificados.
	 * 
	 * @return the contador_no_usuarios_identificados
	 */
	public Integer getContador_no_usuarios_identificados() {
		return contador_no_usuarios_identificados;
	}

	/**
	 * Gets the contador_usuarios_identificados.
	 * 
	 * @return the contador_usuarios_identificados
	 */
	public Integer getContador_usuarios_identificados() {
		return contador_usuarios_identificados;
	}

	/**
	 * Gets the latitud.
	 * 
	 * @return the latitud
	 */
	public String getLatitud() {
		return latitud;
	}

	/**
	 * Gets the longitud.
	 * 
	 * @return the longitud
	 */
	public String getLongitud() {
		return longitud;
	}

	/**
	 * Gets the nombre.
	 * 
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Sets the contador_no_usuarios_identificados.
	 * 
	 * @param contador_no_usuarios_identificados
	 *            the new contador_no_usuarios_identificados
	 */
	public void setContador_no_usuarios_identificados(
			Integer contador_no_usuarios_identificados) {
		this.contador_no_usuarios_identificados = contador_no_usuarios_identificados;
	}

	/**
	 * Sets the contador_usuarios_identificados.
	 * 
	 * @param contador_usuarios_identificados
	 *            the new contador_usuarios_identificados
	 */
	public void setContador_usuarios_identificados(
			Integer contador_usuarios_identificados) {
		this.contador_usuarios_identificados = contador_usuarios_identificados;
	}

	/**
	 * Sets the latitud.
	 * 
	 * @param latitud
	 *            the new latitud
	 */
	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}

	/**
	 * Sets the longitud.
	 * 
	 * @param longitud
	 *            the new longitud
	 */
	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}

	/**
	 * Sets the nombre.
	 * 
	 * @param nombre
	 *            the new nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Date getFecha_checkin() {
		return fecha_checkin;
	}

	public void setFecha_checkin(Date fecha_checkin) {
		this.fecha_checkin = fecha_checkin;
	}

}