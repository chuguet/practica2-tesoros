package com.movember.quizz.controller.dto;

import com.movember.quizz.model.bean.ParametrosEncuesta;
import com.movember.quizz.model.exception.AppException;


/**
 * The Class ParametrosEncuestaDTO.
 */
public class ParametrosEncuestaDTO extends AbstractDTO {

	/** The ip_usuario. */
	private String ip_usuario;

	/** The id_usuario. */
	private String id_usuario;

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
	public String getId_usuario() {
		return id_usuario;
	}

	/**
	 * Sets the id_usuario.
	 * 
	 * @param id_usuario
	 *            the new id_usuario
	 */
	public void setId_usuario(String id_usuario) {
		this.id_usuario = id_usuario;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.movember.quizz.controller.dto.AbstractDTO#toRest(java.lang.Object)
	 */
	@Override
	public void toRest(Object object) throws AppException {
		ParametrosEncuesta parametrosEncuesta = (ParametrosEncuesta) object;
		this.ip_usuario = parametrosEncuesta.getIp_usuario();
		if (parametrosEncuesta.getId_usuario() != null) {
			this.id_usuario = parametrosEncuesta.getId_usuario().toString();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.movember.quizz.controller.dto.AbstractDTO#toBusiness(java.lang.Object
	 * )
	 */
	@Override
	public void toBusiness(Object object) throws AppException {
		ParametrosEncuesta parametrosEncuesta = (ParametrosEncuesta) object;

		if (this.id_usuario != null && this.id_usuario.length() > 0) {
			parametrosEncuesta.setId_usuario(Integer.parseInt(this.id_usuario));
		} else {
			parametrosEncuesta.setIp_usuario(this.ip_usuario);
		}
	}
}