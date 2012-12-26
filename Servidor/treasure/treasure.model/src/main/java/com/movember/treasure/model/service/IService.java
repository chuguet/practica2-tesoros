package com.movember.treasure.model.service;

import java.util.List;
import com.movember.treasure.model.bean.AbstractBean;
import com.movember.treasure.model.exception.AppException;


/**
 * The Interface IService.
 * 
 * @param <T>
 *            the generic type
 */
public interface IService<T extends AbstractBean> {

	/**
	 * Insert.
	 * 
	 * @param t
	 *            the t
	 * @throws AppException
	 *             the app exception
	 */
	void insert(T t) throws AppException;

	/**
	 * Update.
	 * 
	 * @param t
	 *            the t
	 * @throws AppException
	 *             the app exception
	 */
	void update(T t) throws AppException;

	/**
	 * Delete.
	 * 
	 * @param t
	 *            the t
	 * @throws AppException
	 *             the app exception
	 */
	void delete(T t) throws AppException;

	/**
	 * Retrieve.
	 * 
	 * @param id
	 *            the id
	 * @return the t
	 * @throws AppException
	 *             the app exception
	 */
	T retrieve(Integer id) throws AppException;

	/**
	 * Select all.
	 * 
	 * @return the list
	 * @throws AppException
	 *             the app exception
	 */
	List<T> selectAll() throws AppException;
}
