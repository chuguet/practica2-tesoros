package com.movember.treasure.controller.dto;

/**
 * The Class MensajeDTO.
 */
public class MensajeDTO {

	/** The mensaje. */
	private String mensaje;

	/** The correcto. */
	private Boolean correcto;

	/**
	 * Gets the mensaje.
	 * 
	 * @return the mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * Sets the mensaje.
	 * 
	 * @param mensaje
	 *            the new mensaje
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	/**
	 * Gets the correcto.
	 * 
	 * @return the correcto
	 */
	public Boolean getCorrecto() {
		return correcto;
	}

	/**
	 * Sets the correcto.
	 * 
	 * @param correcto
	 *            the new correcto
	 */
	public void setCorrecto(Boolean correcto) {
		this.correcto = correcto;
	}

	/**
	 * Instantiates a new mensaje dto.
	 */
	public MensajeDTO() {
		this.correcto = true;
	}
}