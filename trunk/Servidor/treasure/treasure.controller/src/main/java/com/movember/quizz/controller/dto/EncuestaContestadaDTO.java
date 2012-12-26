package com.movember.quizz.controller.dto;

import java.util.ArrayList;
import java.util.List;
import com.movember.quizz.model.bean.EncuestaContestada;
import com.movember.quizz.model.exception.AppException;


/**
 * The Class EncuestaContestadaDTO.
 */
public class EncuestaContestadaDTO extends AbstractDTO {

	/** The id_encuesta. */
	private Integer id_encuesta;

	/** The id_usuario. */
	private Integer id_usuario;

	/** The ip_usuario. */
	private String ip_usuario;

	/** The id respuestas contestadas. */
	private List<Integer> idRespuestasContestadas;

	/**
	 * Sets the id_encuesta.
	 * 
	 * @param id_encuesta
	 *            the new id_encuesta
	 */
	public void setId_encuesta(Integer id_encuesta) {
		this.id_encuesta = id_encuesta;
	}

	/**
	 * Gets the id_encuesta.
	 * 
	 * @return the id_encuesta
	 */
	public Integer getId_encuesta() {
		return id_encuesta;
	}

	/**
	 * Gets the id_usuario.
	 * 
	 * @return the id_usuario
	 */
	public Integer getId_usuario() {
		return id_usuario;
	}

	/**
	 * Sets the id_usuario.
	 * 
	 * @param id_usuario
	 *            the new id_usuario
	 */
	public void setId_usuario(Integer id_usuario) {
		this.id_usuario = id_usuario;
	}

	/**
	 * Gets the ip_usuario.
	 * 
	 * @return the ip_usuario
	 */
	public String getIp_usuario() {
		return ip_usuario;
	}

	/**
	 * Sets the ip_usuario.
	 * 
	 * @param ip_usuario
	 *            the new ip_usuario
	 */
	public void setIp_usuario(String ip_usuario) {
		this.ip_usuario = ip_usuario;
	}

	/**
	 * Gets the id respuestas contestadas.
	 * 
	 * @return the id respuestas contestadas
	 */
	public List<Integer> getIdRespuestasContestadas() {
		return idRespuestasContestadas;
	}

	/**
	 * Sets the id respuestas contestadas.
	 * 
	 * @param idRespuestasContestadas
	 *            the new id respuestas contestadas
	 */
	public void setIdRespuestasContestadas(List<Integer> idRespuestasContestadas) {
		this.idRespuestasContestadas = idRespuestasContestadas;
	}

	/**
	 * Instantiates a new encuesta contestada dto.
	 */
	public EncuestaContestadaDTO() {
		idRespuestasContestadas = new ArrayList<Integer>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.movember.quizz.controller.dto.AbstractDTO#toRest(java.lang.Object)
	 */
	@Override
	public void toRest(Object object) throws AppException {
		EncuestaContestada encuestaContestada = (EncuestaContestada) object;
		this.id_encuesta = encuestaContestada.getId_encuesta();
		this.id_usuario = encuestaContestada.getId_usuario();
		this.ip_usuario = encuestaContestada.getIp_usuario();
		this.idRespuestasContestadas = encuestaContestada
				.getIdRespuestasContestadas();
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
		EncuestaContestada encuestaContestada = (EncuestaContestada) object;
		encuestaContestada.setId_encuesta(this.id_encuesta);
		encuestaContestada.setId_usuario(this.id_usuario);
		encuestaContestada.setIp_usuario(this.ip_usuario);
		encuestaContestada
				.setIdRespuestasContestadas(this.idRespuestasContestadas);
	}
}
