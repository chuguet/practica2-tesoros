package com.movember.treasure.model.dao;

import java.sql.SQLException;
import java.util.List;
import com.movember.treasure.model.bean.Mensaje;

public interface IConfiguracionDAO {

	void insert(Mensaje mensaje) throws SQLException;

	List<Mensaje> selectAll() throws SQLException;

	void deleteAll() throws SQLException;
}