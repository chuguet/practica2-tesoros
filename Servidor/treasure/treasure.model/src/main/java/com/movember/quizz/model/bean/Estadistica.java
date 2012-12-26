package com.movember.quizz.model.bean;

import java.util.List;

/**
 * The Class Estadistica.
 */
public class Estadistica {

	/** The encuesta. */
	private String encuesta;

	/** The preguntas. */
	private List<PreguntaEstadistica> preguntas;

	/** The id encuesta. */
	private Integer idEncuesta;

	/**
	 * Gets the encuesta.
	 * 
	 * @return the encuesta
	 */
	public String getEncuesta() {
		return encuesta;
	}

	/**
	 * Sets the encuesta.
	 * 
	 * @param pEncuesta
	 *            the new encuesta
	 */
	public void setEncuesta(String pEncuesta) {
		encuesta = pEncuesta;
	}

	/**
	 * Gets the preguntas.
	 * 
	 * @return the preguntas
	 */
	public List<PreguntaEstadistica> getPreguntas() {
		return preguntas;
	}

	/**
	 * Sets the preguntas.
	 * 
	 * @param preguntas
	 *            the new preguntas
	 */
	public void setPreguntas(List<PreguntaEstadistica> preguntas) {
		this.preguntas = preguntas;
	}

	/**
	 * Gets the id encuesta.
	 * 
	 * @return the id encuesta
	 */
	public Integer getIdEncuesta() {
		return idEncuesta;
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
}
