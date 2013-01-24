package com.movember.treasure.controller.dto;

import com.movember.treasure.model.bean.ItemConfiguracion;
import com.movember.treasure.model.exception.AppException;

public class ItemConfiguracionDTO extends AbstractDTO {
	private String clave;
	private Integer valor;

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public Integer getValor() {
		return valor;
	}

	public void setValor(Integer valor) {
		this.valor = valor;
	}

	@Override
	public void toRest(Object object) throws AppException {
		super.toRest(object);
		ItemConfiguracion itemConfiguracion = (ItemConfiguracion) object;
		this.setClave(itemConfiguracion.getClave());
		this.setValor(itemConfiguracion.getValor());
	}

	@Override
	public void toBusiness(Object object) throws AppException {
		super.toBusiness(object);
		ItemConfiguracion itemConfiguracion = (ItemConfiguracion) object;
		itemConfiguracion.setClave(this.getClave());
		itemConfiguracion.setValor(this.getValor());
	}

}
