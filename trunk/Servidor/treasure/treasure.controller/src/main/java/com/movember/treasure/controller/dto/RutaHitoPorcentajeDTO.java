package com.movember.treasure.controller.dto;

import java.text.DecimalFormat;
import com.movember.treasure.model.bean.RutaHitoPorcentaje;
import com.movember.treasure.model.exception.AppException;

// TODO: Auto-generated Javadoc
/**
 * The Class RutaHitoPorcentajeDTO.
 */
public class RutaHitoPorcentajeDTO extends AbstractDTO {

	/** The Constant df. */
	private static final DecimalFormat df = new DecimalFormat("0.00");

	/** The num_hitos_checkeados. */
	private Integer num_hitos_checkeados;
	private Integer num_hitos_distintos_checkeados;

	/** The num_hitos_necesarios. */
	private Integer num_hitos_necesarios;

	private Integer num_hitos_distintos;

	/** The num_hitos_totales. */
	private Integer num_hitos_totales;

	/** The porcentaje_finalizado. */
	private String porcentaje_finalizado;
	private String porcentaje_distintos_finalizado;

	/** The ruta. */
	private String ruta;

	/**
	 * Gets the num_hitos_checkeados.
	 * 
	 * @return the num_hitos_checkeados
	 */
	public Integer getNum_hitos_checkeados() {
		return num_hitos_checkeados;
	}

	/**
	 * Gets the num_hitos_necesarios.
	 * 
	 * @return the num_hitos_necesarios
	 */
	public Integer getNum_hitos_necesarios() {
		return num_hitos_necesarios;
	}

	public void setNum_hitos_distintos(Integer num_hitos_distintos) {
		this.num_hitos_distintos = num_hitos_distintos;
	}

	public Integer getNum_hitos_distintos() {
		return num_hitos_distintos;
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
	 * Gets the porcentaje_finalizado.
	 * 
	 * @return the porcentaje_finalizado
	 */
	public String getPorcentaje_finalizado() {
		return porcentaje_finalizado;
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
	 * Sets the num_hitos_checkeados.
	 * 
	 * @param num_hitos_checkeados
	 *            the new num_hitos_checkeados
	 */
	public void setNum_hitos_checkeados(Integer num_hitos_checkeados) {
		this.num_hitos_checkeados = num_hitos_checkeados;
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
	 * Sets the porcentaje_finalizado.
	 * 
	 * @param porcentaje_finalizado
	 *            the new porcentaje_finalizado
	 */
	public void setPorcentaje_finalizado(String porcentaje_finalizado) {
		this.porcentaje_finalizado = porcentaje_finalizado;
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

	/*
	 * (non-Javadoc)
	 * @see
	 * com.movember.treasure.controller.dto.AbstractDTO#toBusiness(java.lang
	 * .Object)
	 */
	@Override
	public void toBusiness(Object object) throws AppException {

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.movember.treasure.controller.dto.AbstractDTO#toRest(java.lang.Object)
	 */
	@Override
	public void toRest(Object object) throws AppException {
		RutaHitoPorcentaje rutaHitoPorcentaje = (RutaHitoPorcentaje) object;
		this.num_hitos_distintos_checkeados = rutaHitoPorcentaje.getNum_hitos_distintos_checkeados();
		this.num_hitos_checkeados = rutaHitoPorcentaje.getNum_hitos_checkeados();
		this.num_hitos_necesarios = rutaHitoPorcentaje.getNum_hitos_necesarios();
		this.num_hitos_distintos = rutaHitoPorcentaje.getNum_hitos_distintos();
		this.num_hitos_totales = rutaHitoPorcentaje.getNum_hitos_totales();
		this.ruta = rutaHitoPorcentaje.getRuta();
		this.setId(rutaHitoPorcentaje.getId());
		Float porcentaje;
		if (!num_hitos_necesarios.equals(0)) {
			porcentaje = Float.valueOf(num_hitos_checkeados) / Float.valueOf(num_hitos_necesarios) * 100;
			if (porcentaje.compareTo(new Float(100.00)) > 0) {
				porcentaje = new Float(100.00);
			}
		}
		else {
			porcentaje = new Float(100.00);
		}
		this.porcentaje_finalizado = df.format(porcentaje).toString() + "%";

		if (!num_hitos_distintos.equals(0)) {
			porcentaje = Float.valueOf(num_hitos_distintos_checkeados) / Float.valueOf(num_hitos_distintos) * 100;
			if (porcentaje.compareTo(new Float(100.00)) > 0) {
				porcentaje = new Float(100.00);
			}
		}
		else {
			porcentaje = new Float(100.00);
		}
		this.porcentaje_distintos_finalizado = df.format(porcentaje).toString() + "%";
	}

	public void setNum_hitos_distintos_checkeados(Integer num_hitos_distintos_checkeados) {
		this.num_hitos_distintos_checkeados = num_hitos_distintos_checkeados;
	}

	public Integer getNum_hitos_distintos_checkeados() {
		return num_hitos_distintos_checkeados;
	}

	public void setPorcentaje_distintos_finalizado(String porcentaje_distintos_finalizado) {
		this.porcentaje_distintos_finalizado = porcentaje_distintos_finalizado;
	}

	public String getPorcentaje_distintos_finalizado() {
		return porcentaje_distintos_finalizado;
	}
}
