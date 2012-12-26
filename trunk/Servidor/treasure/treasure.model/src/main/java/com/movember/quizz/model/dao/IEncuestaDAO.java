package com.movember.quizz.model.dao;

import java.sql.SQLException;
import java.util.List;
import com.movember.quizz.model.bean.Encuesta;
import com.movember.quizz.model.bean.EncuestaContestada;
import com.movember.quizz.model.bean.ParametrosEncuesta;


/**
 * The Interface IEncuestaDAO.
 */
public interface IEncuestaDAO extends IRepositoryDAO<Encuesta> {

	/**
	 * Find.
	 * 
	 * @param parametrosEncuesta
	 *            the parametros encuesta
	 * @return the list
	 * @throws SQLException
	 *             the sQL exception
	 */
	List<Encuesta> find(ParametrosEncuesta parametrosEncuesta)
			throws SQLException;

	/**
	 * Contestar.
	 * 
	 * @param encuestaRellenada
	 *            the encuesta rellenada
	 * @throws SQLException
	 *             the sQL exception
	 */
	void contestar(EncuestaContestada encuestaRellenada) throws SQLException;

}