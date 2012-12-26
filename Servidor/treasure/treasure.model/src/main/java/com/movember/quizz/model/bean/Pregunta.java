package com.movember.quizz.model.bean;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;

/**
 * The Class Pregunta.
 */
@Entity
public class Pregunta extends AbstractBean {

	/** The pregunta. */
	private String pregunta;

	/** The id_encuesta. */
	private Integer id_encuesta;

	/** The respuestas. */
	private List<Respuesta> respuestas;

	/**
	 * Instantiates a new pregunta.
	 */
	public Pregunta() {
		this.respuestas = new ArrayList<Respuesta>();
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
	 * Sets the pregunta.
	 * 
	 * @param pregunta
	 *            the new pregunta
	 */
	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}

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
	 * Gets the respuestas.
	 * 
	 * @return the respuestas
	 */
	public List<Respuesta> getRespuestas() {
		return respuestas;
	}

	/**
	 * Sets the respuestas.
	 * 
	 * @param respuestas
	 *            the new respuestas
	 */
	public void setRespuestas(List<Respuesta> respuestas) {
		this.respuestas = respuestas;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object otra) {
		Pregunta pregunta = (Pregunta) otra;
		return this.getId().equals(pregunta.getId());
	}
}