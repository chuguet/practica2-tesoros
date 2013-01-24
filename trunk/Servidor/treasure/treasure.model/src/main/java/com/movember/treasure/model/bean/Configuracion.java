package com.movember.treasure.model.bean;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.annotations.Entity;

@Entity
public class Configuracion extends AbstractBean {

	private List<String> mensajes;

	private List<ItemConfiguracion> itemsConfiguracion;

	public Configuracion() {
		this.setMensajes(new ArrayList<String>());
		this.setItemsConfiguracion(new ArrayList<ItemConfiguracion>());
	}

	public List<String> getMensajes() {
		return mensajes;
	}

	public void setMensajes(List<String> mensajes) {
		this.mensajes = mensajes;
	}

	public void setItemsConfiguracion(List<ItemConfiguracion> itemsConfiguracion) {
		this.itemsConfiguracion = itemsConfiguracion;
	}

	public List<ItemConfiguracion> getItemsConfiguracion() {
		return itemsConfiguracion;
	}
}