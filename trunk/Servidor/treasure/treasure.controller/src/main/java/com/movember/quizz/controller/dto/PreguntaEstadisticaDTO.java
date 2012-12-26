package com.movember.quizz.controller.dto;

import java.util.ArrayList;
import java.util.List;

import com.movember.quizz.model.bean.PreguntaEstadistica;
import com.movember.quizz.model.bean.RespuestaEstadistica;
import com.movember.quizz.model.exception.AppException;


/**
 * The Class PreguntaEstadisticaDTO.
 */
public class PreguntaEstadisticaDTO extends AbstractDTO {

	/** The pregunta. */
	private String pregunta;

	/** The id encuesta. */
	private Integer idEncuesta;

	/** The respuestas. */
	private List<RespuestaEstadisticaDTO> respuestas;

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
	public List<RespuestaEstadisticaDTO> getRespuestas() {
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
	public void setRespuestas(List<RespuestaEstadisticaDTO> respuestas) {
		this.respuestas = respuestas;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.movember.quizz.controller.dto.AbstractDTO#toRest(java.lang.Object)
	 */
	@Override
	public void toRest(Object object) throws AppException {
		PreguntaEstadistica pregunta = (PreguntaEstadistica) object;
		// this.setId(estadistica.getId());
		this.setPregunta(pregunta.getPregunta());
		this.setIdEncuesta(pregunta.getIdEncuesta());

		if (pregunta.getRespuestas() != null
				&& pregunta.getRespuestas().size() > 0) {
			RespuestaEstadisticaDTO respuestaEstadisticaDTO;
			for (RespuestaEstadistica respuesta : pregunta.getRespuestas()) {
				respuestaEstadisticaDTO = new RespuestaEstadisticaDTO();
				respuestaEstadisticaDTO.toRest(respuesta);
				this.addRespuestaDTO(respuestaEstadisticaDTO);
			}
		}
	}

	/**
	 * Adds the respuesta dto.
	 * 
	 * @param respuestaEstadisticaDTO
	 *            the respuesta estadistica dto
	 */
	private void addRespuestaDTO(RespuestaEstadisticaDTO respuestaEstadisticaDTO) {
		if (this.getRespuestas() == null) {
			this.respuestas = new ArrayList<RespuestaEstadisticaDTO>();
		}
		respuestas.add(respuestaEstadisticaDTO);
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
