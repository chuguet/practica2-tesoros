package com.movember.treasure.model.service;

import com.movember.treasure.model.bean.Estadistica;
import com.movember.treasure.model.exception.AppException;


/**
 * The Interface IEstadisticaService.
 */
public interface IEstadisticaService {

	/**
	 * Retrieve.
	 * 
	 * @param pIdEncuesta
	 *            the id encuesta
	 * @return the estadistica
	 * @throws AppException
	 *             the app exception
	 */
	Estadistica retrieve(Integer pIdEncuesta) throws AppException;
}
