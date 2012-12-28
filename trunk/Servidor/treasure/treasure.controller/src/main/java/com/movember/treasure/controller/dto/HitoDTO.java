package com.movember.treasure.controller.dto;

import com.movember.treasure.model.bean.Hito;
import com.movember.treasure.model.exception.AppException;

/**
 * The Class HitoDTO.
 */
public class HitoDTO extends AbstractDTO {

	private String nombre;
	private String codigo;
	private String latitud;
	private String longitud;
	private String pista;

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
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
		this.setId(hito.getId());
		this.nombre = hito.getNombre();
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

		hito.setId(this.getId());
		hito.setNombre(this.nombre);
		hito.setCodigo(this.codigo);
		hito.setLatitud(this.latitud);
		hito.setLongitud(this.longitud);
		hito.setPista(this.pista);
	}
}