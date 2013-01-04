package com.movember.treasure.model.dao;

import java.sql.SQLException;
import java.util.List;

import com.movember.treasure.model.bean.AbstractBean;


/**
 * The Interface IRepositoryDAO.
 * 
 * @param <T>
 *            the generic type
 */
public interface IRepositoryDAO<T extends AbstractBean> {

	/**
	 * Insert.
	 * 
	 * @param t
	 *            the t
	 * @throws SQLException
	 *             the sQL exception
	 */
	Integer insert(T t) throws SQLException;

	/**
	 * Update.
	 * 
	 * @param t
	 *            the t
	 * @throws SQLException
	 *             the sQL exception
	 */
	void update(T t) throws SQLException;

	/**
	 * Delete.
	 * 
	 * @param id
	 *            the id
	 * @throws SQLException
	 *             the sQL exception
	 */
	void delete(Integer id) throws SQLException;

	/**
	 * Retrieve.
	 * 
	 * @param id
	 *            the id
	 * @return the t
	 * @throws SQLException
	 *             the sQL exception
	 */
	T retrieve(Integer id) throws SQLException;

	/**
	 * Select all.
	 * 
	 * @return the list
	 * @throws SQLException
	 *             the sQL exception
	 */
	List<T> selectAll() throws SQLException;

	/**
	 * Select by criterios.
	 * 
	 * @return the list
	 * @throws SQLException
	 *             the sQL exception
	 */
	List<T> selectByCriterios() throws SQLException;
}
