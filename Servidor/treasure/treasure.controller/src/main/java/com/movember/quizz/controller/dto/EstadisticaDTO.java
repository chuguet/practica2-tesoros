package com.movember.quizz.controller.dto;

import java.util.ArrayList;
import java.util.List;

import com.movember.quizz.model.bean.Estadistica;
import com.movember.quizz.model.bean.PreguntaEstadistica;
import com.movember.quizz.model.exception.AppException;


/**
 * The Class EstadisticaDTO.
 */
public class EstadisticaDTO extends AbstractDTO {

	/** The encuesta. */
	private String encuesta;

	/** The preguntas. */
	private List<PreguntaEstadisticaDTO> preguntas;

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
	public List<PreguntaEstadisticaDTO> getPreguntas() {
		return preguntas;
	}

	/**
	 * Sets the preguntas.
	 * 
	 * @param preguntas
	 *            the new preguntas
	 */
	public void setPreguntas(List<PreguntaEstadisticaDTO> preguntas) {
		this.preguntas = preguntas;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.movember.quizz.controller.dto.AbstractDTO#toRest(java.lang.Object)
	 */
	@Override
	public void toRest(Object object) throws AppException {
		Estadistica estadistica = (Estadistica) object;
		// this.setId(estadistica.getId());
		this.setEncuesta(estadistica.getEncuesta());
		this.setId(estadistica.getIdEncuesta());
		if (estadistica.getPreguntas() != null
				&& estadistica.getPreguntas().size() > 0) {
			PreguntaEstadisticaDTO preguntaEstadisticaDTO;
			for (PreguntaEstadistica pregunta : estadistica.getPreguntas()) {
				preguntaEstadisticaDTO = new PreguntaEstadisticaDTO();
				preguntaEstadisticaDTO.toRest(pregunta);
				this.addPreguntaDTO(preguntaEstadisticaDTO);
			}
		}
	}

	/**
	 * Adds the pregunta dto.
	 * 
	 * @param preguntaEstadisticaDTO
	 *            the pregunta estadistica dto
	 */
	private void addPreguntaDTO(PreguntaEstadisticaDTO preguntaEstadisticaDTO) {
		if (this.getPreguntas() == null) {
			this.preguntas = new ArrayList<PreguntaEstadisticaDTO>();
		}
		preguntas.add(preguntaEstadisticaDTO);
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
