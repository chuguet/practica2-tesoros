package com.movember.treasure.model.dao.impl;

import java.sql.SQLException;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.movember.treasure.model.bean.Dispositivo;
import com.movember.treasure.model.dao.IDispositivoDAO;

@Repository
class DispositivoDAO extends AbstractDAO implements IDispositivoDAO {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	public Integer insert(Dispositivo dispositivo) throws SQLException {
		return (Integer) this.getSqlMapClient().insert("dispositivo.insertReturnId", dispositivo);
	}

	public void update(Dispositivo dispositivo) throws SQLException {
		this.getSqlMapClient().update("dispositivo.updateByPrimaryKey", dispositivo);
	}

	public void delete(Integer id) throws SQLException {
		this.getSqlMapClient().delete("dispositivo.deleteByPrimaryKey", id);
	}

	public Dispositivo retrieve(Integer id) throws SQLException {
		return (Dispositivo) this.getSqlMapClient().queryForObject("dispositivo.selectById", id);
	}

	public List<Dispositivo> selectAll() throws SQLException {
		return (List<Dispositivo>) this.getSqlMapClient().queryForList("dispositivo.selectAll");
	}

	public List<Dispositivo> selectByCriterios() throws SQLException {
		return (List<Dispositivo>) this.getSqlMapClient().queryForList("dispositivo.selectAll");
	}

	public Dispositivo selectByUUID(String uuid) throws SQLException {
		return (Dispositivo) this.getSqlMapClient().queryForObject("dispositivo.selectByUUID", uuid);
	}
}