package com.movember.quizz.model.service;

import com.movember.quizz.model.bean.Estadistica;
import com.movember.quizz.model.exception.AppException;


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
