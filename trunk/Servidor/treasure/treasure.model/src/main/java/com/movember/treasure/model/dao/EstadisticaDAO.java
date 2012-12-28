package com.movember.treasure.model.dao;

import java.sql.SQLException;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public class EstadisticaDAO extends AbstractDAO implements IEstadisticaDAO {

	public Map<Integer, Integer> recuperarHitosUsuIdent(Integer idRuta)
			throws SQLException {
		return (Map<Integer, Integer>) this.getSqlMapClient().queryForMap(
				"estadistica.selectCountHitoIdent", idRuta, "key", "value");
	}

	public Map<Integer, Integer> recuperarHitosUsuNoIdent(Integer idRuta)
			throws SQLException {
		return (Map<Integer, Integer>) this.getSqlMapClient().queryForMap(
				"estadistica.selectCountHitoNotIdent", idRuta, "key", "value");
	}
}
