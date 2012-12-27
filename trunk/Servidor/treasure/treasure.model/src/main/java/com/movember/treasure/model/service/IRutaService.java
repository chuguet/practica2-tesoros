package com.movember.treasure.model.service;

import java.util.List;
import com.movember.treasure.model.bean.Hito;
import com.movember.treasure.model.bean.ParametrosRuta;
import com.movember.treasure.model.bean.Ruta;
import com.movember.treasure.model.exception.AppException;

/**
 * The Interface IRutaService.
 */
public interface IRutaService extends IService<Ruta> {

	/**
	 * Find.
	 * 
	 * @param parametrosRuta
	 *            - parameters to find quizzs
	 * @return the list
	 * @throws AppException
	 *             the app exception
	 */
	List<Ruta> find(ParametrosRuta parametrosRuta) throws AppException;

	String encontrarHito(Hito hito, String securityToken);
}
