package com.movember.treasure.model.service;

import java.util.Date;
import java.util.List;
import com.movember.treasure.model.bean.HitoDispositivo;
import com.movember.treasure.model.exception.AppException;

public interface IHitoDispositivoService extends IService<HitoDispositivo> {

	void eliminarDeHito(Integer idHito) throws AppException;

	List<HitoDispositivo> selectByCriterios(Integer idHito, Integer idDispositivo, String longitud, String latitud, Integer identificado, Date fecha, Integer id_ruta) throws AppException;

}