package com.movember.treasure.model.bean;

import javax.persistence.Entity;

@Entity
public class Dispositivo extends AbstractBean {

	private String uuid;

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getUuid() {
		return uuid;
	}

	@Override
	public boolean equals(Object otro) {
		Dispositivo dispositivo = (Dispositivo) otro;
		return this.uuid.equals(dispositivo.uuid);
	}
}