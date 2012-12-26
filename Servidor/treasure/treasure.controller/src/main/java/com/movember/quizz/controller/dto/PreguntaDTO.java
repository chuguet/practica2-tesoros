package com.movember.quizz.controller.dto;

import java.util.ArrayList;
import java.util.List;
import com.movember.quizz.model.bean.Pregunta;
import com.movember.quizz.model.bean.Respuesta;
import com.movember.quizz.model.exception.AppException;


/**
 * The Class PreguntaDTO.
 */
public class PreguntaDTO extends AbstractDTO {

	/** The title. */
	private String title;

	/** The is folder. */
	private boolean isFolder;

	/** The key. */
	private String key;

	/** The children. */
	private List<RespuestaDTO> children = new ArrayList<RespuestaDTO>();

	/**
	 * Gets the title.
	 * 
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the title.
	 * 
	 * @param title
	 *            the new title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Gets the checks if is folder.
	 * 
	 * @return the checks if is folder
	 */
	public boolean getIsFolder() {
		return isFolder;
	}

	/**
	 * Sets the checks if is folder.
	 * 
	 * @param isFolder
	 *            the new checks if is folder
	 */
	public void setIsFolder(boolean isFolder) {
		this.isFolder = isFolder;
	}

	/**
	 * Gets the key.
	 * 
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * Sets the key.
	 * 
	 * @param key
	 *            the new key
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * Gets the children.
	 * 
	 * @return the children
	 */
	public List<RespuestaDTO> getChildren() {
		return children;
	}

	/**
	 * Sets the children.
	 * 
	 * @param children
	 *            the new children
	 */
	public void setChildren(List<RespuestaDTO> children) {
		this.children = children;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.movember.quizz.controller.dto.AbstractDTO#toRest(java.lang.Object)
	 */
	@Override
	public void toRest(Object object) throws AppException {
		Pregunta pregunta = (Pregunta) object;
		if (pregunta.getId() != null) {
			this.key = "p" + pregunta.getId().toString();
			this.setId(pregunta.getId());
		}
		this.title = pregunta.getPregunta();
		this.isFolder = true;
		if (pregunta.getRespuestas() != null
				&& pregunta.getRespuestas().size() > 0) {
			for (Respuesta respuesta : pregunta.getRespuestas()) {
				RespuestaDTO respuestaDTO = new RespuestaDTO();
				respuestaDTO.toRest(respuesta);
				this.children.add(respuestaDTO);
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
		Pregunta pregunta = (Pregunta) object;
		if (this.key.indexOf('p') != -1) {
			pregunta.setId(Integer.parseInt(this.key.replace("p", "")));
		}
		pregunta.setPregunta(this.title);

		if (this.children != null && this.children.size() > 0) {
			for (RespuestaDTO respuestaDTO : this.children) {
				Respuesta respuesta = new Respuesta();
				respuesta.setId_pregunta(pregunta.getId());
				respuestaDTO.toBusiness(respuesta);
				pregunta.getRespuestas().add(respuesta);
			}
		}
	}
}
