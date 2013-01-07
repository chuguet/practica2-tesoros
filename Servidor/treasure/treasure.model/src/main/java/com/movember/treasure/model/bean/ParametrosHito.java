package com.movember.treasure.model.bean;

public class ParametrosHito {
	public ParametrosHito(){
		
	}
	public ParametrosHito(Integer idHito, Integer idDispositivoUsuario){
		this.idDipositivoUsuario=idDispositivoUsuario;
		this.idHito=idHito;
	}
	private Integer idHito;
	private Integer idDipositivoUsuario;
	public Integer getIdDipositivoUsuario() {
		return idDipositivoUsuario;
	}
	public void setIdDipositivoUsuario(Integer idDipositivoUsuario) {
		this.idDipositivoUsuario = idDipositivoUsuario;
	}
	public Integer getIdHito() {
		return idHito;
	}
	public void setIdHito(Integer idHito) {
		this.idHito = idHito;
	}
}
