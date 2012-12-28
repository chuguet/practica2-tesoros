package com.movember.treasure.model.dao;

import java.sql.SQLException;
import java.util.List;

import com.movember.treasure.model.bean.HitoEstadistica;
import com.movember.treasure.model.bean.ParametrosRuta;
import com.movember.treasure.model.bean.Ruta;


/**
 * The Interface IEncuestaDAO.
 */
public interface IRutaDAO extends IRepositoryDAO<Ruta> {

	/**
	 * Find.
	 * 
	 * @param parametrosEncuesta
	 *            the parametros encuesta
	 * @return the list
	 * @throws SQLException
	 *             the sQL exception
	 */
	List<Ruta> find(ParametrosRuta parametrosEncuesta)
			throws SQLException;

	/**
	 * Contestar.
	 * 
	 * @param encuestaRellenada
	 *            the encuesta rellenada
	 * @throws SQLException
	 *             the sQL exception
	 */
	void contestar(HitoEstadistica encuestaRellenada) throws SQLException;

}