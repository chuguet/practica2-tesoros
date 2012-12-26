package com.movember.quizz.model.bean;

/**
 * The Class RespuestaEstadistica.
 */
public class RespuestaEstadistica {

	/** The respuesta. */
	private String respuesta;

	/** The veces contestada. */
	private int vecesContestada;

	/** The id pregunta. */
	private Integer idPregunta;

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
	 * Gets the veces contestada.
	 * 
	 * @return the veces contestada
	 */
	public int getVecesContestada() {
		return vecesContestada;
	}

	/**
	 * Sets the veces contestada.
	 * 
	 * @param vecesContestada
	 *            the new veces contestada
	 */
	public void setVecesContestada(int vecesContestada) {
		this.vecesContestada = vecesContestada;
	}

	/**
	 * Gets the id pregunta.
	 * 
	 * @return the id pregunta
	 */
	public Integer getIdPregunta() {
		return idPregunta;
	}

	/**
	 * Sets the id pregunta.
	 * 
	 * @param idPregunta
	 *            the new id pregunta
	 */
	public void setIdPregunta(Integer idPregunta) {
		this.idPregunta = idPregunta;
	}
}
