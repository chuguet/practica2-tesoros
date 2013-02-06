package com.movember.treasure.model.bean;

public class ParametrosHito {

	private Integer idHito;
	private Integer idDispositivoUsuario;

	public Integer getIdHito() {
		return idHito;
	}

	public void setIdHito(Integer idHito) {
		this.idHito = idHito;
	}

	public Integer getIdDispositivoUsuario() {
		return idDispositivoUsuario;
	}

	public void setIdDispositivoUsuario(Integer idDispositivoUsuario) {
		this.idDispositivoUsuario = idDispositivoUsuario;
	}

	public ParametrosHito() {

	}

	public ParametrosHito(Integer idHito, Integer idDispositivoUsuario) {
		this.idDispositivoUsuario = idDispositivoUsuario;
		this.idHito = idHito;
	}
}
