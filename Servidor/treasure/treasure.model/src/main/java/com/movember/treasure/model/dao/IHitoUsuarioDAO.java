package com.movember.treasure.model.dao;

import java.sql.SQLException;
import com.movember.treasure.model.bean.HitoUsuario;

/**
 * The Interface IHitoDAO.
 */
public interface IHitoUsuarioDAO extends IRepositoryDAO<HitoUsuario> {

	void deleteByHito(Integer idHito) throws SQLException;
}
