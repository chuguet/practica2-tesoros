package com.movember.quizz.controller.dto;

import com.movember.quizz.model.exception.AppException;


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
	public abstract void toRest(Object object) throws AppException;

	/**
	 * To business.
	 * 
	 * @param object
	 *            the object
	 * @throws AppException
	 *             the app exception
	 */
	public abstract void toBusiness(Object object) throws AppException;
}
