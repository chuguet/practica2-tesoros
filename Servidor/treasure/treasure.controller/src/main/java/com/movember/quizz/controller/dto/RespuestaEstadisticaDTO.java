package com.movember.quizz.controller.dto;

import com.movember.quizz.model.bean.RespuestaEstadistica;
import com.movember.quizz.model.exception.AppException;


/**
 * The Class RespuestaEstadisticaDTO.
 */
public class RespuestaEstadisticaDTO extends AbstractDTO {

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.movember.quizz.controller.dto.AbstractDTO#toRest(java.lang.Object)
	 */
	@Override
	public void toRest(Object object) throws AppException {
		RespuestaEstadistica respuestaEstadistica = (RespuestaEstadistica) object;
		this.setIdPregunta(respuestaEstadistica.getIdPregunta());
		this.setRespuesta(respuestaEstadistica.getRespuesta());
		this.setVecesContestada(respuestaEstadistica.getVecesContestada());
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
	}
}
