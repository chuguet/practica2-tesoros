package com.movember.quizz.model.service;

import com.movember.quizz.model.bean.Usuario;
import com.movember.quizz.model.exception.AppException;


/**
 * The Interface IUsuarioService.
 */
public interface IUsuarioService extends IService<Usuario> {

	/**
	 * Select by user.
	 * 
	 * @param name
	 *            the name
	 * @return the usuario
	 * @throws AppException
	 *             the app exception
	 */
	Usuario selectByUser(String name) throws AppException;

}