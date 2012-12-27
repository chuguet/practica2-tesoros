package com.movember.treasure.model.bean;

import javax.persistence.Entity;

// TODO: Auto-generated Javadoc
/**
 * The Class Pregunta.
 */
@Entity
public class Hito extends AbstractBean {

	/** The nombre. */
	private String nombre;

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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Integer getId_ruta() {
		return id_ruta;
	}

	public void setId_ruta(Integer id_ruta) {
		this.id_ruta = id_ruta;
	}

	public String getLatitud() {
		return latitud;
	}

	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}

	public String getLongitud() {
		return longitud;
	}

	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}

	public String getPista() {
		return pista;
	}

	public void setPista(String pista) {
		this.pista = pista;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object otra) {
		Hito hito = (Hito) otra;
		return this.getId().equals(hito.getId());
	}

}