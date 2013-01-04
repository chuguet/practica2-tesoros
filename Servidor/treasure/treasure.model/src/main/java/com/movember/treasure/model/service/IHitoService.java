package com.movember.treasure.model.service;

import java.util.List;
import com.movember.treasure.model.bean.Hito;
import com.movember.treasure.model.exception.AppException;

/**
 * The Interface IHitoService.
 */
public interface IHitoService extends IService<Hito> {

	/**
	 * Recuperar de ruta.
	 * 
	 * @param idRuta
	 *            the id ruta
	 * @return the list
	 * @throws AppException
	 *             the app exception
	 */
	List<Hito> recuperarDeRuta(Integer idRuta) throws AppException;

	String checkHito(Hito hito, String uuid) throws AppException;

	Hito recuperarPorCodigo(String codigo) throws AppException;
}
