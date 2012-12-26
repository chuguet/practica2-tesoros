package com.movember.quizz.model.bean;

import javax.persistence.Entity;

/**
 * The Class Respuesta.
 */
@Entity
public class Respuesta extends AbstractBean {

	/** The respuesta. */
	private String respuesta;

	/** The id_pregunta. */
	private Integer id_pregunta;

	/**
	 * Gets the respuesta.
	 * 
	 * @return the respuesta
	 */
	public String getRespuesta() {
		return respuesta;
	}

	/**
	 * Sets the respuesta.
	 * 
	 * @param respuesta
	 *            the new respuesta
	 */
	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}

	/**
	 * Sets the id_pregunta.
	 * 
	 * @param id_pregunta
	 *            the new id_pregunta
	 */
	public void setId_pregunta(Integer id_pregunta) {
		this.id_pregunta = id_pregunta;
	}

	/**
	 * Gets the id_pregunta.
	 * 
	 * @return the id_pregunta
	 */
	public Integer getId_pregunta() {
		return id_pregunta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object otra) {
		Respuesta respuesta = (Respuesta) otra;
		return this.getId().equals(respuesta.getId());
	}
}