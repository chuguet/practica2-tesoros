package com.movember.treasure.model.service;

import java.util.List;
import com.movember.treasure.model.bean.Configuracion;
import com.movember.treasure.model.bean.Mensaje;
import com.movember.treasure.model.exception.AppException;

public interface IConfiguracionService {
	void insert(Configuracion configuracion) throws AppException;

	void deleteAll() throws AppException;

	Configuracion retrieve() throws AppException;

	List<Mensaje> recuperarMensajes() throws AppException;
}
