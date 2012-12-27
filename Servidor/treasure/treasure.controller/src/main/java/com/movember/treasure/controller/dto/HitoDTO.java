package com.movember.treasure.controller.dto;

import com.movember.treasure.model.bean.Hito;
import com.movember.treasure.model.exception.AppException;

/**
 * The Class HitoDTO.
 */
public class HitoDTO extends AbstractDTO {

	/** The title. */
	private String title;

	/** The is folder. */
	private boolean isFolder;

	/** The key. */
	private String key;

	/** The codigo. */
	private String codigo;

	/** The pregunta. */
	private String latitud;

	/** The longitud. */
	private String longitud;

	/** The pista. */
	private String pista;

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

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}

	public String getLatitud() {
		return latitud;
	}

	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}

	public String getLongitud() {
		return longitud;
	}

	public void setPista(String pista) {
		this.pista = pista;
	}

	public String getPista() {
		return pista;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.movember.quizz.controller.dto.AbstractDTO#toRest(java.lang.Object)
	 */
	@Override
	public void toRest(Object object) throws AppException {
		Hito hito = (Hito) object;
		if (hito.getId() != null) {
			this.key = "p" + hito.getId().toString();
			this.setId(hito.getId());
		}
		this.title = hito.getNombre();
		this.isFolder = true;
		this.codigo = hito.getCodigo();
		this.latitud = hito.getLatitud();
		this.longitud = hito.getLongitud();
		this.pista = hito.getPista();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.movember.quizz.controller.dto.AbstractDTO#toBusiness(java.lang.Object
	 * )
	 */
	@Override
	public void toBusiness(Object object) throws AppException {
		Hito hito = (Hito) object;
		if (this.key.indexOf('p') != -1) {
			hito.setId(Integer.parseInt(this.key.replace("p", "")));
		}
		hito.setNombre(this.title);
		hito.setCodigo(this.codigo);
		hito.setLatitud(this.latitud);
		hito.setLongitud(this.longitud);
		hito.setPista(this.pista);
	}
}