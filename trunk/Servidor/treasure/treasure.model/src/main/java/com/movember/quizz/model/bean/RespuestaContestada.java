package com.movember.quizz.model.bean;

/**
 * The Class RespuestaContestada.
 */
public class RespuestaContestada extends AbstractBean {

	/** The id_respuesta. */
	private Integer id_respuesta;

	/** The id_encuesta_contestada. */
	private Integer id_encuesta_contestada;

	/**
	 * Gets the id_respuesta.
	 * 
	 * @return the id_respuesta
	 */
	public Integer getId_respuesta() {
		return id_respuesta;
	}

	/**
	 * Sets the id_respuesta.
	 * 
	 * @param id_respuesta
	 *            the new id_respuesta
	 */
	public void setId_respuesta(Integer id_respuesta) {
		this.id_respuesta = id_respuesta;
	}

	/**
	 * Gets the id_encuesta_contestada.
	 * 
	 * @return the id_encuesta_contestada
	 */
	public Integer getId_encuesta_contestada() {
		return id_encuesta_contestada;
	}

	/**
	 * Sets the id_encuesta_contestada.
	 * 
	 * @param id_encuesta_contestada
	 *            the new id_encuesta_contestada
	 */
	public void setId_encuesta_contestada(Integer id_encuesta_contestada) {
		this.id_encuesta_contestada = id_encuesta_contestada;
	}

}