package com.movember.treasure.model.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import com.movember.treasure.model.bean.Hito;
import com.movember.treasure.model.bean.ParametrosHito;
import com.movember.treasure.model.bean.Ruta;

public interface IEstadisticaDAO {

	Map<Integer, Integer> recuperarHitosUsuIdent(Integer idRuta) throws SQLException;

	Map<Integer, Integer> recuperarHitosUsuNoIdent(Integer idRuta) throws SQLException;

	Integer recuperarNumeroTotalesHitos() throws SQLException;

	Integer recuperarNumeroTotalesRutas() throws SQLException;

	List<Hito> recuperarNumeroHitosTerminados(Integer pIdUsuario) throws SQLException;

	List<Ruta> recuperarNumeroRutasTerminadas(Integer pIdUsuario) throws SQLException;

	Integer recuperarNumeroUsuariosHanTerminadoRuta(Integer pIdRuta) throws SQLException;

	Date recuperarFechaCheckin(ParametrosHito parametrosHito) throws SQLException;

	Map<Date, Long> recuperarContadorHitoPorDiasIdentificado(Integer idHito) throws SQLException;

	Map<Date, Long> recuperarContadorHitoPorDiasNoIdentificado(Integer idHito) throws SQLException;
}
