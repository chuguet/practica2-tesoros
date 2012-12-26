package com.movember.quizz.model.dao;

import java.sql.SQLException;
import com.movember.quizz.model.bean.Usuario;


/**
 * The Interface IUsuarioDAO.
 */
public interface IUsuarioDAO extends IRepositoryDAO<Usuario> {

	/**
	 * Select by user.
	 * 
	 * @param usuarioNombre
	 *            the usuario nombre
	 * @return the usuario
	 * @throws SQLException
	 *             the sQL exception
	 */
	Usuario selectByUser(String usuarioNombre) throws SQLException;

}
