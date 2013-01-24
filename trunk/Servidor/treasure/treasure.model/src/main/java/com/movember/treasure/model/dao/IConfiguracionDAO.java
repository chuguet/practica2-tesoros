package com.movember.treasure.model.dao;

import java.sql.SQLException;
import java.util.List;
import com.movember.treasure.model.bean.ItemConfiguracion;

public interface IConfiguracionDAO {

	void insert(ItemConfiguracion itemConfiguracion) throws SQLException;

	List<ItemConfiguracion> selectAll() throws SQLException;

	void update(ItemConfiguracion itemConfiguracion) throws SQLException;

	void deleteAll() throws SQLException;

	ItemConfiguracion selectByClave(String clave) throws SQLException;
}