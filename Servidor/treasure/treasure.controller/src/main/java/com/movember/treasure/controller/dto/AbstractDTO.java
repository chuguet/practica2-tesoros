package com.movember.treasure.controller.dto;

import com.movember.treasure.model.bean.AbstractBean;
import com.movember.treasure.model.exception.AppException;

/**
 * The Class AbstractDTO.
 */
public abstract class AbstractDTO {

	/** The id. */
	private Integer id;

	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Sets the id.
	 * 
	 * @param id
	 *            the new id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * To rest.
	 * 
	 * @param object
	 *            the object
	 * @throws AppException
	 *             the app exception
	 */
	public void toRest(Object object) throws AppException {
		AbstractBean abstractBean = (AbstractBean) object;
		this.setId(abstractBean.getId());
	}

	/**
	 * To business.
	 * 
	 * @param object
	 *            the object
	 * @throws AppException
	 *             the app exception
	 */
	public void toBusiness(Object object) throws AppException {
		AbstractBean abstractBean = (AbstractBean) object;
		abstractBean.setId(this.getId());
	}
}
