package com.movember.treasure.controller.dto;

import com.movember.treasure.model.bean.Hito;
import com.movember.treasure.model.exception.AppException;

public class HitoEncontradoDTO extends AbstractDTO {

	private String codigo;
	private String latitud;
	private String longitud;
	private String securityToken;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
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

	public String getSecurityToken() {
		return securityToken;
	}

	public void setSecurityToken(String securityToken) {
		this.securityToken = securityToken;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.movember.quizz.controller.dto.AbstractDTO#toRest(java.lang.Object)
	 */
	@Override
	public void toRest(Object object) throws AppException {
		Hito hito = (Hito) object;

		this.codigo = hito.getCodigo();
		this.latitud = hito.getLatitud();
		this.longitud = hito.getLongitud();
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

		hito.setCodigo(this.codigo);
		hito.setLatitud(this.latitud);
		hito.setLongitud(this.longitud);
	}
}