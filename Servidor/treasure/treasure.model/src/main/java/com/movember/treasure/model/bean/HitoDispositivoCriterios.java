package com.movember.treasure.model.bean;

import java.util.Date;

public class HitoDispositivoCriterios {

	private Integer id_hito;
	private Integer id_dispositivo;
	private String longitud;
	private String latitud;
	private Integer identificado;
	private Date fecha;
	private Integer id_ruta;

	public Integer getId_hito() {
		return id_hito;
	}

	public void setId_hito(Integer id_hito) {
		this.id_hito = id_hito;
	}

	public Integer getId_dispositivo() {
		return id_dispositivo;
	}

	public void setId_dispositivo(Integer id_dispositivo) {
		this.id_dispositivo = id_dispositivo;
	}

	public String getLongitud() {
		return longitud;
	}

	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}

	public String getLatitud() {
		return latitud;
	}

	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}

	public Integer getIdentificado() {
		return identificado;
	}

	public void setIdentificado(Integer identificado) {
		this.identificado = identificado;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public void setId_ruta(Integer id_ruta) {
		this.id_ruta = id_ruta;
	}

	public Integer getId_ruta() {
		return id_ruta;
	}
}