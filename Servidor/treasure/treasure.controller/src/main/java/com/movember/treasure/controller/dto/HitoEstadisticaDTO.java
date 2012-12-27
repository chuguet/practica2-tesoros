package com.movember.treasure.controller.dto;

import com.movember.treasure.model.exception.AppException;

/**
 * The Class PreguntaEstadisticaDTO.
 */
public class HitoEstadisticaDTO extends AbstractDTO {

	/** The pregunta. */
	private String hito;

	/** The id ruta. */
	private Integer idRuta;

	public void setHito(String hito) {
		this.hito = hito;
	}

	public String getHito() {
		return hito;
	}

	public void setIdRuta(Integer idRuta) {
		this.idRuta = idRuta;
	}

	public Integer getIdRuta() {
		return idRuta;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.movember.quizz.controller.dto.AbstractDTO#toRest(java.lang.Object)
	 */
	@Override
	public void toRest(Object object) throws AppException {
		// PreguntaEstadistica pregunta = (PreguntaEstadistica) object;
		// this.setPregunta(pregunta.getPregunta());
		// this.setIdEncuesta(pregunta.getIdEncuesta());

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
