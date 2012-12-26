package com.movember.quizz.controller.dto;

import com.movember.quizz.model.bean.Respuesta;
import com.movember.quizz.model.exception.AppException;


/**
 * The Class RespuestaDTO.
 */
public class RespuestaDTO extends AbstractDTO {

	/** The title. */
	private String title;

	/** The key. */
	private String key;

	/** The is folder. */
	private boolean isFolder;

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
	 * Sets the checks if is folder.
	 * 
	 * @param isFolder
	 *            the new checks if is folder
	 */
	public void setIsFolder(boolean isFolder) {
		this.isFolder = isFolder;
	}

	/**
	 * Gets the checks if is folder.
	 * 
	 * @return the checks if is folder
	 */
	public boolean getIsFolder() {
		return isFolder;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.movember.quizz.controller.dto.AbstractDTO#toRest(java.lang.Object)
	 */
	@Override
	public void toRest(Object object) throws AppException {
		Respuesta respuesta = (Respuesta) object;
		if (respuesta.getId() != null) {
			this.key = "r" + respuesta.getId().toString();
			this.setId(respuesta.getId());
		}
		this.title = respuesta.getRespuesta();
		this.isFolder = false;
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
		Respuesta respuesta = (Respuesta) object;
		if (this.key.indexOf('r') != -1) {
			respuesta.setId(Integer.parseInt(this.key.replace("r", "")));
		}
		respuesta.setRespuesta(this.title);
	}
}
