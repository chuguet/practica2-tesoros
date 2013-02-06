package com.movember.treasure.model.service;

import java.util.List;
import com.movember.treasure.model.bean.Hito;
import com.movember.treasure.model.bean.HitoDispositivo;
import com.movember.treasure.model.exception.AppException;

/**
 * The Interface IHitoService.
 */
public interface IHitoService extends IService<Hito> {

	List<Hito> recuperarDeRuta(Integer idRuta) throws AppException;

	List<String> checkHito(Hito hito, String uuid) throws AppException;

	Hito recuperarPorCodigo(String codigo) throws AppException;

	String generarQR(String codigo) throws AppException;

	Integer recuperarNumeroHitosDistintos(List<HitoDispositivo> hitosCheckeados);
}
