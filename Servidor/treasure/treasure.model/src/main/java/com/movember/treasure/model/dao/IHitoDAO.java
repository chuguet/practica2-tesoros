package com.movember.treasure.model.dao;

import java.sql.SQLException;
import java.util.List;
import com.movember.treasure.model.bean.Hito;

/**
 * The Interface IHitoDAO.
 */
public interface IHitoDAO extends IRepositoryDAO<Hito> {

	/**
	 * Recuperar de ruta.
	 * 
	 * @param idRuta
	 *            the id ruta
	 * @return the list
	 * @throws SQLException
	 *             the sQL exception
	 */
	List<Hito> recuperarDeRuta(Integer idRuta) throws SQLException;

	Hito selectByCodigo(String codigo) throws SQLException;

}
