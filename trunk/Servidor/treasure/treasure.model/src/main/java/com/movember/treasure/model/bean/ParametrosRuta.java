package com.movember.treasure.model.bean;

import javax.persistence.Entity;

/**
 * The Class ParametrosRuta.
 */
@Entity
public class ParametrosRuta extends AbstractBean {

	/** The ip_usuario. */
	private String securityToken;

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object otra) {
		ParametrosRuta parametrosRuta = (ParametrosRuta) otra;
		return this.getId().equals(parametrosRuta.getId());
	}

	public String getSecurityToken() {
		return securityToken;
	}

	public void setSecurityToken(String securityToken) {
		this.securityToken = securityToken;
	}
}