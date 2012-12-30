package com.movember.treasure.model.dao;

import java.sql.SQLException;
import com.movember.treasure.model.bean.Dispositivo;

public interface IDispositivoDAO extends IRepositoryDAO<Dispositivo> {

	Dispositivo selectByUUID(String uuid) throws SQLException;

}