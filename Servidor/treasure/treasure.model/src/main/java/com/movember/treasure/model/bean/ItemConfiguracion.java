package com.movember.treasure.model.bean;

public class ItemConfiguracion extends AbstractBean {
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

}
