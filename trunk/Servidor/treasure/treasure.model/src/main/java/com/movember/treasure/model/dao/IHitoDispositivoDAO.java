package com.movember.treasure.model.dao;

import java.sql.SQLException;
import java.util.List;
import com.movember.treasure.model.bean.HitoDispositivo;
import com.movember.treasure.model.bean.HitoDispositivoCriterios;

public interface IHitoDispositivoDAO extends IRepositoryDAO<HitoDispositivo> {

	void deleteByHito(Integer idHito) throws SQLException;

	List<HitoDispositivo> selectByCriterios(HitoDispositivoCriterios criterios) throws SQLException;
}