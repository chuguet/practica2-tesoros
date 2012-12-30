package com.movember.treasure.model.service;

import com.movember.treasure.model.bean.Dispositivo;
import com.movember.treasure.model.exception.AppException;

public interface IDispositivoService extends IService<Dispositivo> {

	Dispositivo selectByUUID(String uuid) throws AppException;

}
