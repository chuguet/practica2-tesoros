package com.movember.treasure.model.service;

import java.util.List;
import com.movember.treasure.model.bean.HitoUsuario;
import com.movember.treasure.model.bean.ParametrosRuta;
import com.movember.treasure.model.bean.Ruta;
import com.movember.treasure.model.exception.AppException;


/**
 * The Interface IEncuestaService.
 */
public interface IEncuestaService extends IService<Ruta> {

	/**
	 * Find.
	 * 
	 * @param parametrosEncuesta
	 *            the parametros encuesta
	 * @return the list
	 * @throws AppException
	 *             the app exception
	 */
	List<Ruta> find(ParametrosRuta parametrosEncuesta)
			throws AppException;

	/**
	 * Contestar.
	 * 
	 * @param encuestaContestada
	 *            the encuesta contestada
	 * @throws AppException
	 *             the app exception
	 */
	void contestar(HitoUsuario encuestaContestada) throws AppException;

}
