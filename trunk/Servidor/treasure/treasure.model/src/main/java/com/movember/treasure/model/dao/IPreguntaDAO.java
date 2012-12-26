package com.movember.treasure.model.dao;

import java.sql.SQLException;
import java.util.List;

import com.movember.treasure.model.bean.Pregunta;


/**
 * The Interface IPreguntaDAO.
 */
public interface IPreguntaDAO extends IRepositoryDAO<Pregunta> {

	/**
	 * Recuperar de encuesta.
	 * 
	 * @param idEncuesta
	 *            the id encuesta
	 * @return the list
	 * @throws SQLException
	 *             the sQL exception
	 */
	List<Pregunta> recuperarDeEncuesta(Integer idEncuesta) throws SQLException;

}
