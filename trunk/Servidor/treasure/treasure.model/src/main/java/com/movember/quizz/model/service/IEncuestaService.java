package com.movember.quizz.model.service;

import java.util.List;
import com.movember.quizz.model.bean.Encuesta;
import com.movember.quizz.model.bean.EncuestaContestada;
import com.movember.quizz.model.bean.ParametrosEncuesta;
import com.movember.quizz.model.exception.AppException;


/**
 * The Interface IEncuestaService.
 */
public interface IEncuestaService extends IService<Encuesta> {

	/**
	 * Find.
	 * 
	 * @param parametrosEncuesta
	 *            the parametros encuesta
	 * @return the list
	 * @throws AppException
	 *             the app exception
	 */
	List<Encuesta> find(ParametrosEncuesta parametrosEncuesta)
			throws AppException;

	/**
	 * Contestar.
	 * 
	 * @param encuestaContestada
	 *            the encuesta contestada
	 * @throws AppException
	 *             the app exception
	 */
	void contestar(EncuestaContestada encuestaContestada) throws AppException;

}
