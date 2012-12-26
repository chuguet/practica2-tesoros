package com.movember.treasure.model.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.annotations.Entity;

/**
 * The Class Encuesta.
 */
@Entity
public class Ruta extends AbstractBean {

	private Integer id;
	
	/** The nombre. */
	private String nombre;

	/** The fecha_inicio. */
	private Date fecha_inicio;

	/** The fecha_fin. */
	private Date fecha_fin;

	/** The preguntas. */
	private List<Hito> hitos;

	/**
	 * Instantiates a new encuesta.
	 */
	public Ruta() {
		this.setHitos(new ArrayList<Hito>());
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
	public Date getFecha_inicio() {
		return fecha_inicio;
	}

	/**
	 * Sets the fecha_inicio.
	 * 
	 * @param fecha_inicio
	 *            the new fecha_inicio
	 */
	public void setFecha_inicio(Date fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}

	/**
	 * Gets the fecha_fin.
	 * 
	 * @return the fecha_fin
	 */
	public Date getFecha_fin() {
		return fecha_fin;
	}

	/**
	 * Sets the fecha_fin.
	 * 
	 * @param fecha_fin
	 *            the new fecha_fin
	 */
	public void setFecha_fin(Date fecha_fin) {
		this.fecha_fin = fecha_fin;
	}

	public List<Hito> getHitos() {
		return hitos;
	}

	public void setHitos(List<Hito> hitos) {
		this.hitos = hitos;
	}

}
