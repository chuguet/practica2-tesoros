package com.movember.treasure.model.service;

import com.movember.treasure.model.bean.Usuario;
import com.movember.treasure.model.exception.AppException;

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

	void insertWithDevice(Usuario usuario, String uuid) throws AppException;

}