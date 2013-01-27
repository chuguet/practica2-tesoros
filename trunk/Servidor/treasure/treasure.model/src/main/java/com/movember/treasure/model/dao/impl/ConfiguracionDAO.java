package com.movember.treasure.model.dao.impl;

import java.sql.SQLException;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.movember.treasure.model.bean.ItemConfiguracion;
import com.movember.treasure.model.dao.IConfiguracionDAO;

@Repository
public class ConfiguracionDAO extends AbstractDAO implements IConfiguracionDAO {

	private static final long serialVersionUID = 1L;

	public void insert(ItemConfiguracion itemConfiguracion) throws SQLException {
		this.getSqlMapClient().insert("configuracion.insert", itemConfiguracion);
	}

	public List<ItemConfiguracion> selectAll() throws SQLException {
		return (List<ItemConfiguracion>) this.getSqlMapClient().queryForList("configuracion.selectAll");
	}

	public void update(ItemConfiguracion itemConfiguracion) throws SQLException {
		this.getSqlMapClient().update("configuracion.update", itemConfiguracion);
	}

	public void deleteAll() throws SQLException {
		this.getSqlMapClient().delete("configuracion.deleteAll");
	}

	public ItemConfiguracion selectByClave(String clave) throws SQLException {
		return (ItemConfiguracion) this.getSqlMapClient().queryForObject("configuracion.selectByClave", clave);
	}
}