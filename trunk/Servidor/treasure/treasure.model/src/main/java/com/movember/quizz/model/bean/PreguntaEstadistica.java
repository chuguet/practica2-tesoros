package com.movember.quizz.model.bean;

import java.util.List;

/**
 * The Class PreguntaEstadistica.
 */
public class PreguntaEstadistica {

	/** The pregunta. */
	private String pregunta;

	/** The id encuesta. */
	private Integer idEncuesta;

	/** The respuestas. */
	private List<RespuestaEstadistica> respuestas;

	/**
	 * Gets the id encuesta.
	 * 
	 * @return the id encuesta
	 */
	public Integer getIdEncuesta() {
		return idEncuesta;
	}

	/**
	 * Gets the pregunta.
	 * 
	 * @return the pregunta
	 */
	public String getPregunta() {
		return pregunta;
	}

	/**
	 * Gets the respuestas.
	 * 
	 * @return the respuestas
	 */
	public List<RespuestaEstadistica> getRespuestas() {
		return respuestas;
	}

	/**
	 * Sets the id encuesta.
	 * 
	 * @param idEncuesta
	 *            the new id encuesta
	 */
	public void setIdEncuesta(Integer idEncuesta) {
		this.idEncuesta = idEncuesta;
	}

	/**
	 * Sets the pregunta.
	 * 
	 * @param pregunta
	 *            the new pregunta
	 */
	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}

	/**
	 * Sets the respuestas.
	 * 
	 * @param respuestas
	 *            the new respuestas
	 */
	public void setRespuestas(List<RespuestaEstadistica> respuestas) {
		this.respuestas = respuestas;
	}
}
