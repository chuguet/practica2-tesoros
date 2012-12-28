package com.movember.treasure.model.dao;

import java.sql.SQLException;
import java.util.Map;

public interface IEstadisticaDAO {

	Map<Integer,Integer> recuperarHitosUsuIdent(Integer idRuta) throws SQLException;

	Map<Integer,Integer> recuperarHitosUsuNoIdent(Integer idRuta) throws SQLException;
}
