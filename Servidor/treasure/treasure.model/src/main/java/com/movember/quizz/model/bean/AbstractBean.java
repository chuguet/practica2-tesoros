package com.movember.quizz.model.bean;

import org.hibernate.annotations.Entity;


/**
 * The Class AbstractBean.
 */
@Entity
public abstract class AbstractBean {

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
}