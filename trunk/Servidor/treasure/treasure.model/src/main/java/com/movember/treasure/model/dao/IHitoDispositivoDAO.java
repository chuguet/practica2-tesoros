package com.movember.treasure.model.dao;

import java.sql.SQLException;
import com.movember.treasure.model.bean.HitoDispositivo;

/**
 * The Interface IHitoDAO.
 */
public interface IHitoDispositivoDAO extends IRepositoryDAO<HitoDispositivo> {

	void deleteByHito(Integer idHito) throws SQLException;
}