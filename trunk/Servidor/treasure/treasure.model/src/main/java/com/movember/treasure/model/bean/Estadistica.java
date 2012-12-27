package com.movember.treasure.model.bean;

import javax.persistence.Entity;

// TODO: Auto-generated Javadoc
/**
 * The Class Pregunta.
 */
@Entity
public class Estadistica extends AbstractBean {

	/** The codigo. */
	private String codigo;

	/** The id_encuesta. */
	private Integer id_ruta;

	/** The pregunta. */
	private String latitud;

	/** The longitud. */
	private String longitud;

	/** The pista. */
	private String pista;

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object otra) {
		Estadistica hito = (Estadistica) otra;
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
	public Integer getId_ruta() {
		return id_ruta;
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
	 * Sets the id_ruta.
	 * 
	 * @param id_ruta
	 *            the new id_ruta
	 */
	public void setId_ruta(Integer id_ruta) {
		this.id_ruta = id_ruta;
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