package com.movember.treasure.model.dao;

import java.sql.SQLException;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.movember.treasure.model.bean.HitoDispositivo;

/**
 * The Class HitoDAO.
 */
@Repository
class HitoDispositivoDAO extends AbstractDAO implements IHitoDispositivoDAO {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	public void insert(HitoDispositivo hitoUsuario) throws SQLException {
		Integer id = (Integer) this.getSqlMapClient().insert("hitoUsuario.insertReturnId", hitoUsuario);
		hitoUsuario.setId(id);
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
}