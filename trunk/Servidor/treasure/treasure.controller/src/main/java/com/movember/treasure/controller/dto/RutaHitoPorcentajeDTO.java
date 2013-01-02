package com.movember.treasure.controller.dto;

import java.text.DecimalFormat;

import com.movember.treasure.model.bean.RutaHitoPorcentaje;
import com.movember.treasure.model.exception.AppException;

public class RutaHitoPorcentajeDTO extends AbstractDTO {
	private static final DecimalFormat df = new DecimalFormat("0.00");
	/** The num_hitos_totales. */
	private Integer num_hitos_totales;

	/** The num_hitos_checkeados. */
	private Integer num_hitos_checkeados;

	private String porcentaje_finalizado;

	/** The num_hitos_necesarios. */
	private Integer num_hitos_necesarios;

	/** The ruta. */
	private String ruta;

	public Integer getNum_hitos_totales() {
		return num_hitos_totales;
	}

	public void setNum_hitos_totales(Integer num_hitos_totales) {
		this.num_hitos_totales = num_hitos_totales;
	}

	public Integer getNum_hitos_checkeados() {
		return num_hitos_checkeados;
	}

	public void setNum_hitos_checkeados(Integer num_hitos_checkeados) {
		this.num_hitos_checkeados = num_hitos_checkeados;
	}

	public Integer getNum_hitos_necesarios() {
		return num_hitos_necesarios;
	}

	public void setNum_hitos_necesarios(Integer num_hitos_necesarios) {
		this.num_hitos_necesarios = num_hitos_necesarios;
	}

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	@Override
	public void toRest(Object object) throws AppException {
		RutaHitoPorcentaje rutaHitoPorcentaje = (RutaHitoPorcentaje) object;
		this.num_hitos_checkeados = rutaHitoPorcentaje
				.getNum_hitos_checkeados();
		this.num_hitos_necesarios = rutaHitoPorcentaje
				.getNum_hitos_necesarios();
		this.num_hitos_totales = rutaHitoPorcentaje.getNum_hitos_totales();
		this.ruta = rutaHitoPorcentaje.getRuta();
		Float porcentaje;
		if (!num_hitos_necesarios.equals(0)) {
			porcentaje = Float.valueOf(num_hitos_checkeados)
					/ Float.valueOf(num_hitos_necesarios) * 100;
			if (porcentaje.compareTo(new Float(100.00)) > 0) {
				porcentaje = new Float(100.00);
			}
		} else {
			porcentaje = new Float(100.00);
		}
		this.porcentaje_finalizado = df.format(porcentaje).toString() + "%";
	}

	@Override
	public void toBusiness(Object object) throws AppException {
		// TODO Auto-generated method stub

	}

	public String getPorcentaje_finalizado() {
		return porcentaje_finalizado;
	}

	public void setPorcentaje_finalizado(String porcentaje_finalizado) {
		this.porcentaje_finalizado = porcentaje_finalizado;
	}
}
