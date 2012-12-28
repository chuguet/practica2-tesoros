package com.movember.treasure.model.service;

import com.movember.treasure.model.bean.HitoUsuario;
import com.movember.treasure.model.exception.AppException;

public interface IHitoUsuarioService extends IService<HitoUsuario> {

	void eliminarDeHito(Integer idHito) throws AppException;
}
