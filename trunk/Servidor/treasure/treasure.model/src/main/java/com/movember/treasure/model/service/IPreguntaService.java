package com.movember.treasure.model.service;

import java.util.List;
import com.movember.treasure.model.bean.Pregunta;
import com.movember.treasure.model.exception.AppException;


/**
 * The Interface IPreguntaService.
 */
public interface IPreguntaService extends IService<Pregunta> {

	/**
	 * Recuperar de encuesta.
	 * 
	 * @param idEncuesta
	 *            the id encuesta
	 * @return the list
	 * @throws AppException
	 *             the app exception
	 */
	List<Pregunta> recuperarDeEncuesta(Integer idEncuesta) throws AppException;
}
