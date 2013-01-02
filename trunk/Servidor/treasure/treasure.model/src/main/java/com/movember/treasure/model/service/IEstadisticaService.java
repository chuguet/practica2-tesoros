package com.movember.treasure.model.service;

import com.movember.treasure.model.bean.EstadisticaRuta;
import com.movember.treasure.model.bean.EstadisticaUsuario;
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
	EstadisticaRuta retrieveEstadisticaRuta(Integer pIdEncuesta) throws AppException;
	
	EstadisticaUsuario retrieveEstadisticaUsuario(Integer pIdUsuario) throws AppException;
}
