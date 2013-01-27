package com.movember.treasure.model.bean;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;

// TODO: Auto-generated Javadoc
/**
 * The Class Pregunta.
 */
@Entity
public class EstadisticaRuta extends AbstractBean {

	/** The contador_total. */
	private Integer contador_total;
	
	/** The fecha_fin. */
	private Date fecha_fin;
	
	/** The fecha_inicio. */
	private Date fecha_inicio;

	/** The id_encuesta. */
	private List<EstadisticaHito> hitos;

	/** The codigo. */
	private String ruta;
	
	private Integer usuarios_ruta_completada;

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object otra) {
		EstadisticaRuta hito = (EstadisticaRuta) otra;
		return this.getId().equals(hito.getId());
	}

	/**
	 * Gets the contador_total.
	 *
	 * @return the contador_total
	 */
	public Integer getContador_total() {
		return contador_total;
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
	public List<EstadisticaHito> getHitos() {
		return hitos;
	}

	/**
	 * Gets the ruta.
	 *
	 * @return the ruta
	 */
	public String getRuta() {
		return ruta;
	}
	
	/**
	 * Sets the contador_total.
	 *
	 * @param contador_total the new contador_total
	 */
	public void setContador_total(Integer contador_total) {
		this.contador_total = contador_total;
	}

	/**
	 * Sets the fecha_fin.
	 *
	 * @param fecha_fin the new fecha_fin
	 */
	public void setFecha_fin(Date fecha_fin) {
		this.fecha_fin = fecha_fin;
	}

	/**
	 * Sets the fecha_inicio.
	 *
	 * @param fecha_inicio the new fecha_inicio
	 */
	public void setFecha_inicio(Date fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}

	/**
	 * Sets the hitos.
	 *
	 * @param hitos the new hitos
	 */
	public void setHitos(List<EstadisticaHito> hitos) {
		this.hitos = hitos;
	}

	/**
	 * Sets the ruta.
	 *
	 * @param ruta the new ruta
	 */
	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	public Integer getUsuarios_ruta_completada() {
		return usuarios_ruta_completada;
	}

	public void setUsuarios_ruta_completada(Integer usuarios_ruta_completada) {
		this.usuarios_ruta_completada = usuarios_ruta_completada;
	}

	
}