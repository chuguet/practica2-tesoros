package com.movember.treasure.model.bean;

import org.hibernate.annotations.Entity;

@Entity
public class Mensaje extends AbstractBean {

	private String mensaje;

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getMensaje() {
		return mensaje;
	}

}