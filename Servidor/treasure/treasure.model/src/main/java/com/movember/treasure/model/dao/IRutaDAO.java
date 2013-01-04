package com.movember.treasure.model.dao;

import java.sql.SQLException;
import java.util.List;
import com.movember.treasure.model.bean.HitoDispositivo;
import com.movember.treasure.model.bean.ParametrosRuta;
import com.movember.treasure.model.bean.Ruta;

public interface IRutaDAO extends IRepositoryDAO<Ruta> {

	List<Ruta> find(ParametrosRuta parametrosEncuesta) throws SQLException;

	void contestar(HitoDispositivo encuestaRellenada) throws SQLException;
}