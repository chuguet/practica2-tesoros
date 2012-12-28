package com.movember.treasure.model.dao;

import java.sql.SQLException;

public interface IEstadisticaDAO {

	Integer recuperarHitosUsuIdent(Integer idRuta) throws SQLException;

	Integer recuperarHitosUsuNoIdent(Integer idRuta) throws SQLException;
}
