package com.movember.treasure.controller.dto;

import java.text.SimpleDateFormat;

import com.movember.treasure.model.bean.HitoEstadistica;
import com.movember.treasure.model.exception.AppException;

// TODO: Auto-generated Javadoc
/**
 * The Class PreguntaEstadisticaDTO.
 */
public class HitoEstadisticaDTO extends AbstractDTO {
	private static final SimpleDateFormat sdf = new SimpleDateFormat(
			"dd/MM/YYYY HH:mm");
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

	private String fecha_checkin;

	public String getFecha_checkin() {
		return fecha_checkin;
	}

	public void setFecha_checkin(String fecha_checkin) {
		this.fecha_checkin = fecha_checkin;
	}

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.movember.treasure.controller.dto.AbstractDTO#toBusiness(java.lang
	 * .Object)
	 */
	@Override
	public void toBusiness(Object object) throws AppException {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.movember.treasure.controller.dto.AbstractDTO#toRest(java.lang.Object)
	 */
	@Override
	public void toRest(Object object) throws AppException {
		HitoEstadistica hitoEstadistica = (HitoEstadistica) object;
		this.setContador_no_usuarios_identificados(hitoEstadistica
				.getContador_no_usuarios_identificados());
		this.setContador_usuarios_identificados(hitoEstadistica
				.getContador_usuarios_identificados());
		this.setId(hitoEstadistica.getId());
		this.setLatitud(hitoEstadistica.getLatitud());
		this.setLongitud(hitoEstadistica.getLongitud());
		this.setNombre(hitoEstadistica.getNombre());
		this.setCodigo(hitoEstadistica.getCodigo());
		if (hitoEstadistica.getFecha_checkin() != null) {
			this.setFecha_checkin(sdf.format(hitoEstadistica.getFecha_checkin()));
		}
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

}
