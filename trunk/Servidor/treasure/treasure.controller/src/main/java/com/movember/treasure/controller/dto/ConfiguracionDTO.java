package com.movember.treasure.controller.dto;

import java.util.ArrayList;
import java.util.List;
import com.movember.treasure.model.bean.Configuracion;
import com.movember.treasure.model.exception.AppException;

public class ConfiguracionDTO extends AbstractDTO {

	private Integer numero;
	private List<String> mensajes;

	public ConfiguracionDTO() {
		this.mensajes = new ArrayList<String>();
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public List<String> getMensajes() {
		return mensajes;
	}

	public void setMensajes(List<String> mensajes) {
		this.mensajes = mensajes;
	}

	@Override
	public void toBusiness(Object object) throws AppException {
		Configuracion configuracion = (Configuracion) object;
		configuracion.setId(this.getNumero());
		configuracion.setMensajes(this.getMensajes());
	}

	@Override
	public void toRest(Object object) throws AppException {
		Configuracion configuracion = (Configuracion) object;
		this.setId(configuracion.getId());
		this.setNumero(configuracion.getId());
		this.setMensajes(configuracion.getMensajes());
	}
}
