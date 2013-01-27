package com.movember.treasure.model.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.movember.treasure.model.bean.HitoDispositivo;
import com.movember.treasure.model.bean.HitoDispositivoCriterios;
import com.movember.treasure.model.dao.IHitoDispositivoDAO;

@Repository
class HitoDispositivoDAO extends AbstractDAO implements IHitoDispositivoDAO {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	public Integer insert(HitoDispositivo hitoUsuario) throws SQLException {
		return (Integer) this.getSqlMapClient().insert("hitoUsuario.insertReturnId", hitoUsuario);
	}

	public void update(HitoDispositivo hitoUsuario) throws SQLException {
		this.getSqlMapClient().update("hitoUsuario.updateByPrimaryKey", hitoUsuario);
	}

	public void delete(Integer id) throws SQLException {
		this.getSqlMapClient().delete("hitoUsuario.deleteByPrimaryKey", id);
	}

	public HitoDispositivo retrieve(Integer id) throws SQLException {
		return (HitoDispositivo) this.getSqlMapClient().queryForObject("hitoUsuario.selectById", id);
	}

	public List<HitoDispositivo> selectAll() throws SQLException {
		return (List<HitoDispositivo>) this.getSqlMapClient().queryForList("hitoUsuario.selectAll");
	}

	public List<HitoDispositivo> selectByCriterios() throws SQLException {
		return (List<HitoDispositivo>) this.getSqlMapClient().queryForList("hitoUsuario.selectAll");
	}

	public void deleteByHito(Integer idHito) throws SQLException {
		this.getSqlMapClient().delete("hitoUsuario.deleteByHito", idHito);
	}

	public List<HitoDispositivo> selectByCriterios(HitoDispositivoCriterios criterios) throws SQLException {
		return (List<HitoDispositivo>) this.getSqlMapClient().queryForList("hitoUsuario.selectByCriterios", criterios);
	}
}