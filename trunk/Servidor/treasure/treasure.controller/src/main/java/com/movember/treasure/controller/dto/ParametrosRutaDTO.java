package com.movember.treasure.controller.dto;

import com.movember.treasure.model.bean.ParametrosRuta;
import com.movember.treasure.model.exception.AppException;

/**
 * The Class ParametrosRutaDTO.
 */
public class ParametrosRutaDTO extends AbstractDTO {

	/** The securityToken. */
	private String securityToken;

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
		ParametrosRuta parametrosRuta = (ParametrosRuta) object;
		this.securityToken = parametrosRuta.getSecurityToken();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.movember.quizz.controller.dto.AbstractDTO#toBusiness(java.lang.Object
	 * )
	 */
	@Override
	public void toBusiness(Object object) throws AppException {
		ParametrosRuta parametrosRuta = (ParametrosRuta) object;

		parametrosRuta.setSecurityToken(this.securityToken);
	}
}