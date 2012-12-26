package com.movember.quizz.controller.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import com.movember.quizz.model.bean.Encuesta;
import com.movember.quizz.model.bean.Pregunta;
import com.movember.quizz.model.exception.AppException;

/**
 * The Class EncuestaDTO.
 */
public class EncuestaDTO extends AbstractDTO {

	/** The nombre. */
	private String nombre;

	/** The fecha_inicio. */
	private String fecha_inicio;

	/** The fecha_fin. */
	private String fecha_fin;

	/** The preguntas dto. */
	private List<PreguntaDTO> preguntasDTO;

	/**
	 * Instantiates a new encuesta dto.
	 */
	public EncuestaDTO() {
		preguntasDTO = new ArrayList<PreguntaDTO>();
	}

	/**
	 * Gets the nombre.
	 * 
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Sets the nombre.
	 * 
	 * @param nombre
	 *            the new nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Gets the fecha_inicio.
	 * 
	 * @return the fecha_inicio
	 */
	public String getFecha_inicio() {
		return fecha_inicio;
	}

	/**
	 * Sets the fecha_inicio.
	 * 
	 * @param fecha_inicio
	 *            the new fecha_inicio
	 */
	public void setFecha_inicio(String fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}

	/**
	 * Gets the fecha_fin.
	 * 
	 * @return the fecha_fin
	 */
	public String getFecha_fin() {
		return fecha_fin;
	}

	/**
	 * Sets the fecha_fin.
	 * 
	 * @param fecha_fin
	 *            the new fecha_fin
	 */
	public void setFecha_fin(String fecha_fin) {
		this.fecha_fin = fecha_fin;
	}

	/**
	 * Gets the preguntas dto.
	 * 
	 * @return the preguntas dto
	 */
	public List<PreguntaDTO> getPreguntasDTO() {
		return preguntasDTO;
	}

	/**
	 * Sets the preguntas dto.
	 * 
	 * @param preguntasDTO
	 *            the new preguntas dto
	 */
	public void setPreguntasDTO(List<PreguntaDTO> preguntasDTO) {
		this.preguntasDTO = preguntasDTO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.movember.quizz.controller.dto.AbstractDTO#toRest(java.lang.Object)
	 */
	@Override
	public void toRest(Object object) throws AppException {
		Encuesta encuesta = (Encuesta) object;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		this.setId(encuesta.getId());
		this.nombre = encuesta.getNombre();
		this.fecha_inicio = sdf.format(encuesta.getFecha_inicio());
		this.fecha_fin = sdf.format(encuesta.getFecha_fin());

		if (encuesta.getPreguntas() != null
				&& encuesta.getPreguntas().size() > 0) {
			for (Pregunta pregunta : encuesta.getPreguntas()) {
				PreguntaDTO preguntaDTO = new PreguntaDTO();
				preguntaDTO.toRest(pregunta);
				this.preguntasDTO.add(preguntaDTO);
			}
		}
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
		SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
		Encuesta encuesta = (Encuesta) object;
		encuesta.setId(this.getId());
		encuesta.setNombre(this.nombre);
		try {
			encuesta.setFecha_inicio(formatoDelTexto.parse(this.fecha_inicio));
			encuesta.setFecha_fin(formatoDelTexto.parse(this.fecha_fin));
		} catch (ParseException e) {
			throw new AppException("Error en la conversión de fechas");
		}

		if (this.preguntasDTO != null && this.preguntasDTO.size() > 0) {
			for (PreguntaDTO preguntaDTO : this.preguntasDTO) {
				Pregunta pregunta = new Pregunta();
				pregunta.setId_encuesta(this.getId());
				preguntaDTO.toBusiness(pregunta);
				encuesta.getPreguntas().add(pregunta);
			}
		}
	}
}
