package com.movember.treasure.model.dao;

import java.sql.SQLException;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.movember.treasure.model.bean.Mensaje;

@Repository
class ConfiguracionDAO extends AbstractDAO implements IConfiguracionDAO {

	private static final long serialVersionUID = 1L;

	public void insert(Mensaje mensaje) throws SQLException {
		this.getSqlMapClient().insert("configuracion.insert", mensaje);
	}

	public List<Mensaje> selectAll() throws SQLException {
		return (List<Mensaje>) this.getSqlMapClient().queryForList("configuracion.selectAll");
	}

	public void deleteAll() throws SQLException {
		this.getSqlMapClient().delete("configuracion.deleteAll");
	}
}