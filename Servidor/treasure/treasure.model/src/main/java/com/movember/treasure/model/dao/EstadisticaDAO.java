package com.movember.treasure.model.dao;

import java.util.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.movember.treasure.model.bean.Hito;
import com.movember.treasure.model.bean.ParametrosHito;
import com.movember.treasure.model.bean.Ruta;

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

	public Integer recuperarNumeroTotalesHitos() throws SQLException {
		return (Integer) this.getSqlMapClient().queryForObject(
				"estadistica.selectCountHitos");
	}

	public Integer recuperarNumeroTotalesRutas() throws SQLException {
		return (Integer) this.getSqlMapClient().queryForObject(
				"estadistica.selectCountRutas");
	}

	public List<Hito> recuperarNumeroHitosTerminados(Integer pIdUsuario) throws SQLException {
		return (List<Hito>) this.getSqlMapClient().queryForList(
				"estadistica.selectHitosTerminados", pIdUsuario);
	}

	public List<Ruta> recuperarNumeroRutasTerminadas(Integer pIdUsuario) throws SQLException {
		return (List<Ruta>) this.getSqlMapClient().queryForList(
				"estadistica.selectRutasTerminadas", pIdUsuario);
	}

	public Integer recuperarNumeroUsuariosHanTerminadoRuta(Integer pIdRuta)
			throws SQLException {
		return (Integer) this.getSqlMapClient().queryForObject(
				"estadistica.recuperarNumeroUsuariosHanTerminadoRuta", pIdRuta);//TODO
	}

	public Date recuperarFechaCheckin(ParametrosHito parametrosHito) {
		Date result = null;
		try {
			result = (Date) this.getSqlMapClient().queryForObject(
					"estadistica.recuperarFechaCheckin", parametrosHito);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}
