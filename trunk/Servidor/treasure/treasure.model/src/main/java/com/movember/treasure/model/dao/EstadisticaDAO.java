package com.movember.treasure.model.dao;

import java.sql.SQLException;

import org.springframework.stereotype.Repository;

@Repository
public class EstadisticaDAO extends AbstractDAO implements IEstadisticaDAO {

	public Integer recuperarHitosUsuIdent(Integer idRuta) throws SQLException {
		return (Integer) this.getSqlMapClient().queryForObject(
				"estadistica.selectCountHitoIdent", idRuta);
	}

	public Integer recuperarHitosUsuNoIdent(Integer idRuta) throws SQLException {
		return (Integer) this.getSqlMapClient().queryForObject(
				"estadistica.selectCountHitoNotIdent", idRuta);
	}

}
