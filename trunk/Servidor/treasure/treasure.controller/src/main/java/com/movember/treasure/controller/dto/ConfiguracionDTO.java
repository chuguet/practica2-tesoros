package com.movember.treasure.controller.dto;

import java.util.ArrayList;
import java.util.List;
import com.movember.treasure.model.bean.Configuracion;
import com.movember.treasure.model.bean.ItemConfiguracion;
import com.movember.treasure.model.exception.AppException;

public class ConfiguracionDTO extends AbstractDTO {

	private Integer numero;
	private List<String> mensajes;
	private List<ItemConfiguracionDTO> itemsConfiguracion;

	public ConfiguracionDTO() {
		this.mensajes = new ArrayList<String>();
		this.itemsConfiguracion = new ArrayList<ItemConfiguracionDTO>();
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

	public List<ItemConfiguracionDTO> getItemsConfiguracion() {
		return itemsConfiguracion;
	}

	public void setItemsConfiguracion(List<ItemConfiguracionDTO> itemsConfiguracion) {
		this.itemsConfiguracion = itemsConfiguracion;
	}

	public void setMensajes(List<String> mensajes) {
		this.mensajes = mensajes;
	}

	@Override
	public void toBusiness(Object object) throws AppException {
		super.toBusiness(object);
		Configuracion configuracion = (Configuracion) object;
		configuracion.setId(this.getNumero());
		configuracion.setMensajes(this.getMensajes());
		for (ItemConfiguracionDTO itemConfiguracionDTO : this.getItemsConfiguracion()) {
			ItemConfiguracion itemConfiguracion = new ItemConfiguracion();
			itemConfiguracionDTO.toBusiness(itemConfiguracion);
			configuracion.getItemsConfiguracion().add(itemConfiguracion);
		}
	}

	@Override
	public void toRest(Object object) throws AppException {
		Configuracion configuracion = (Configuracion) object;
		this.setId(configuracion.getId());
		this.setNumero(configuracion.getId());
		this.setMensajes(configuracion.getMensajes());
		for (ItemConfiguracion itemConfiguracion : configuracion.getItemsConfiguracion()) {
			ItemConfiguracionDTO itemConfiguracionDTO = new ItemConfiguracionDTO();
			itemConfiguracionDTO.toRest(itemConfiguracion);
			this.getItemsConfiguracion().add(itemConfiguracionDTO);
		}
	}
}
