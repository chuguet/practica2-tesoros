package com.movember.treasure.model.bean;

import java.util.Date;

public class HitoUsuario extends AbstractBean {

	private Integer id_hito;
	private Integer id_usuario;
	private String longitud;
	private String latitud;
	private Integer identificado;
	private Date fecha;

	public Integer getId_hito() {
		return id_hito;
	}

	public void setId_hito(Integer id_hito) {
		this.id_hito = id_hito;
	}

	public Integer getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(Integer id_usuario) {
		this.id_usuario = id_usuario;
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
}