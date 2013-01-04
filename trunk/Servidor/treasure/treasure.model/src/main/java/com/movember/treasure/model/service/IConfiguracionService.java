package com.movember.treasure.model.service;

import com.movember.treasure.model.bean.Configuracion;
import com.movember.treasure.model.exception.AppException;

public interface IConfiguracionService {
	void insert(Configuracion configuracion) throws AppException;

	void deleteAll() throws AppException;

	Configuracion retrieve() throws AppException;
}
