package com.movember.treasure.model.bean;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.annotations.Entity;

@Entity
public class Configuracion extends AbstractBean {

	private List<String> mensajes;

	public Configuracion() {
		this.setMensajes(new ArrayList<String>());
	}

	public List<String> getMensajes() {
		return mensajes;
	}

	public void setMensajes(List<String> mensajes) {
		this.mensajes = mensajes;
	}
}