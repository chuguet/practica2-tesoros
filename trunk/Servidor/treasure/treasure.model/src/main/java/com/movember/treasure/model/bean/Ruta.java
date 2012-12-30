package com.movember.treasure.model.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.annotations.Entity;

// TODO: Auto-generated Javadoc
/**
 * The Class Encuesta.
 */
@Entity
public class Ruta extends AbstractBean {

	/** The fecha_fin. */
	private Date fecha_fin;

	/** The fecha_inicio. */
	private Date fecha_inicio;
	/** The preguntas. */
	private List<Hito> hitos;

	/** The hitos_necesarios. */
	private Integer hitos_necesarios;

	/** The id. */
	private Integer id;

	/** The nombre. */
	private String nombre;

	/** The premio_identificados. */
	private String premio_identificados;

	/** The premio_no_identificados. */
	private String premio_no_identificados;

	/**
	 * Instantiates a new encuesta.
	 */
	public Ruta() {
		this.setHitos(new ArrayList<Hito>());
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
	 * Gets the fecha_inicio.
	 * 
	 * @return the fecha_inicio
	 */
	public Date getFecha_inicio() {
		return fecha_inicio;
	}

	/**
	 * Gets the hitos.
	 * 
	 * @return the hitos
	 */
	public List<Hito> getHitos() {
		return hitos;
	}

	/**
	 * Gets the hitos_necesarios.
	 * 
	 * @return the hitos_necesarios
	 */
	public Integer getHitos_necesarios() {
		return hitos_necesarios;
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
	 * Gets the premio_identificados.
	 * 
	 * @return the premio_identificados
	 */
	public String getPremio_identificados() {
		return premio_identificados;
	}

	/**
	 * Gets the premio_no_identificados.
	 * 
	 * @return the premio_no_identificados
	 */
	public String getPremio_no_identificados() {
		return premio_no_identificados;
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
	 * Sets the hitos.
	 * 
	 * @param hitos
	 *            the new hitos
	 */
	public void setHitos(List<Hito> hitos) {
		this.hitos = hitos;
	}

	/**
	 * Sets the hitos_necesarios.
	 * 
	 * @param hitos_necesarios
	 *            the new hitos_necesarios
	 */
	public void setHitos_necesarios(Integer hitos_necesarios) {
		this.hitos_necesarios = hitos_necesarios;
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
	 * Sets the premio_identificados.
	 * 
	 * @param premio_identificados
	 *            the new premio_identificados
	 */
	public void setPremio_identificados(String premio_identificados) {
		this.premio_identificados = premio_identificados;
	}

	/**
	 * Sets the premio_no_identificados.
	 * 
	 * @param premio_no_identificados
	 *            the new premio_no_identificados
	 */
	public void setPremio_no_identificados(String premio_no_identificados) {
		this.premio_no_identificados = premio_no_identificados;
	}

}
