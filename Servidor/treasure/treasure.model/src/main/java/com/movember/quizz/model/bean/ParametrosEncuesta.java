package com.movember.quizz.model.bean;

import javax.persistence.Entity;

/**
 * The Class ParametrosEncuesta.
 */
@Entity
public class ParametrosEncuesta extends AbstractBean {

	/** The ip_usuario. */
	private String ip_usuario;

	/** The id_usuario. */
	private Integer id_usuario;

	/**
	 * Gets the ip_usuario.
	 * 
	 * @return the ip_usuario
	 */
	public String getIp_usuario() {
		return ip_usuario;
	}

	/**
	 * Sets the ip_usuario.
	 * 
	 * @param ip_usuario
	 *            the new ip_usuario
	 */
	public void setIp_usuario(String ip_usuario) {
		this.ip_usuario = ip_usuario;
	}

	/**
	 * Gets the id_usuario.
	 * 
	 * @return the id_usuario
	 */
	public Integer getId_usuario() {
		return id_usuario;
	}

	/**
	 * Sets the id_usuario.
	 * 
	 * @param id_usuario
	 *            the new id_usuario
	 */
	public void setId_usuario(Integer id_usuario) {
		this.id_usuario = id_usuario;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object otra) {
		ParametrosEncuesta parametrosEncuesta = (ParametrosEncuesta) otra;
		return this.getId().equals(parametrosEncuesta.getId());
	}
}