package com.movember.treasure.controller.dto;

import java.util.ArrayList;
import java.util.List;
import com.movember.treasure.model.bean.Estadistica;
import com.movember.treasure.model.exception.AppException;

/**
 * The Class EstadisticaDTO.
 */
public class EstadisticaDTO extends AbstractDTO {

	/** The encuesta. */
	private String encuesta;

	/** The preguntas. */
	private List<HitoEstadisticaDTO> preguntas;

	/**
	 * Gets the encuesta.
	 * 
	 * @return the encuesta
	 */
	public String getRuta() {
		return encuesta;
	}

	/**
	 * Sets the encuesta.
	 * 
	 * @param pRuta
	 *            the new encuesta
	 */
	public void setRuta(String pRuta) {
		encuesta = pRuta;
	}

	/**
	 * Gets the preguntas.
	 * 
	 * @return the preguntas
	 */
	public List<HitoEstadisticaDTO> getPreguntas() {
		return preguntas;
	}

	/**
	 * Sets the preguntas.
	 * 
	 * @param preguntas
	 *            the new preguntas
	 */
	public void setPreguntas(List<HitoEstadisticaDTO> preguntas) {
		this.preguntas = preguntas;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.movember.quizz.controller.dto.AbstractDTO#toRest(java.lang.Object)
	 */
	@Override
	public void toRest(Object object) throws AppException {
		Estadistica estadistica = (Estadistica) object;

		// this.setRuta(estadistica.getRuta());
		// this.setId(estadistica.getIdRuta());
		// if (estadistica.getPreguntas() != null &&
		// estadistica.getPreguntas().size() > 0) {
		// PreguntaEstadisticaDTO preguntaEstadisticaDTO;
		// for (PreguntaEstadistica pregunta : estadistica.getPreguntas()) {
		// preguntaEstadisticaDTO = new PreguntaEstadisticaDTO();
		// preguntaEstadisticaDTO.toRest(pregunta);
		// this.addPreguntaDTO(preguntaEstadisticaDTO);
		// }
		// }
	}

	/**
	 * Adds the pregunta dto.
	 * 
	 * @param preguntaEstadisticaDTO
	 *            the pregunta estadistica dto
	 */
	private void addPreguntaDTO(HitoEstadisticaDTO preguntaEstadisticaDTO) {
		if (this.getPreguntas() == null) {
			this.preguntas = new ArrayList<HitoEstadisticaDTO>();
		}
		preguntas.add(preguntaEstadisticaDTO);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.movember.quizz.controller.dto.AbstractDTO#toBusiness(java.lang.Object
	 * )
	 */
	@Override
	public void toBusiness(Object object) throws AppException {
	}

}
