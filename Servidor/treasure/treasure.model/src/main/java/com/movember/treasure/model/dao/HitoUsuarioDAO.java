package com.movember.treasure.model.dao;

import java.sql.SQLException;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.movember.treasure.model.bean.HitoUsuario;

/**
 * The Class HitoDAO.
 */
@Repository
class HitoUsuarioDAO extends AbstractDAO implements IHitoUsuarioDAO {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	public void insert(HitoUsuario hitoUsuario) throws SQLException {
		Integer id = (Integer) this.getSqlMapClient().insert("hitoUsuario.insertReturnId", hitoUsuario);
		hitoUsuario.setId(id);
	}

	public void update(HitoUsuario hitoUsuario) throws SQLException {
		this.getSqlMapClient().update("hitoUsuario.updateByPrimaryKey", hitoUsuario);
	}

	public void delete(Integer id) throws SQLException {
		this.getSqlMapClient().delete("hitoUsuario.deleteByPrimaryKey", id);
	}

	public HitoUsuario retrieve(Integer id) throws SQLException {
		return (HitoUsuario) this.getSqlMapClient().queryForObject("hitoUsuario.selectById", id);
	}

	public List<HitoUsuario> selectAll() throws SQLException {
		return (List<HitoUsuario>) this.getSqlMapClient().queryForList("hitoUsuario.selectAll");
	}

	public List<HitoUsuario> selectByCriterios() throws SQLException {
		return (List<HitoUsuario>) this.getSqlMapClient().queryForList("hitoUsuario.selectAll");
	}

	public void deleteByHito(Integer idHito) throws SQLException {
		this.getSqlMapClient().delete("hitoUsuario.deleteByHito", idHito);
	}
}