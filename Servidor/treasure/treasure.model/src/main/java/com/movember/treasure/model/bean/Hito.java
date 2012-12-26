package com.movember.treasure.model.bean;

import javax.persistence.Entity;

// TODO: Auto-generated Javadoc
/**
 * The Class Pregunta.
 */
@Entity
public class Hito extends AbstractBean {

	/** The codigo. */
	private String codigo;

	/** The id_encuesta. */
	private Integer id_hito;

	/** The pregunta. */
	private String latitud;

	/** The longitud. */
	private String longitud;

	/** The pista. */
	private String pista;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object otra) {
		Hito hito = (Hito) otra;
		return this.getId().equals(hito.getId());
	}

	/**
	 * Gets the codigo.
	 * 
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * Gets the id_hito.
	 * 
	 * @return the id_hito
	 */
	public Integer getId_hito() {
		return id_hito;
	}

	/**
	 * Gets the latitud.
	 * 
	 * @return the latitud
	 */
	public String getLatitud() {
		return latitud;
	}

	/**
	 * Gets the longitud.
	 * 
	 * @return the longitud
	 */
	public String getLongitud() {
		return longitud;
	}

	/**
	 * Gets the pista.
	 * 
	 * @return the pista
	 */
	public String getPista() {
		return pista;
	}

	/**
	 * Sets the codigo.
	 * 
	 * @param codigo
	 *            the new codigo
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * Sets the id_hito.
	 * 
	 * @param id_hito
	 *            the new id_hito
	 */
	public void setId_hito(Integer id_hito) {
		this.id_hito = id_hito;
	}

	/**
	 * Sets the latitud.
	 * 
	 * @param latitud
	 *            the new latitud
	 */
	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}

	/**
	 * Sets the longitud.
	 * 
	 * @param longitud
	 *            the new longitud
	 */
	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}

	/**
	 * Sets the pista.
	 * 
	 * @param pista
	 *            the new pista
	 */
	public void setPista(String pista) {
		this.pista = pista;
	}
}