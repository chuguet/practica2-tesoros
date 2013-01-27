package com.movember.treasure.model.service.impl;

import java.sql.SQLException;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import com.movember.treasure.model.bean.Dispositivo;
import com.movember.treasure.model.dao.IDispositivoDAO;
import com.movember.treasure.model.exception.AppException;
import com.movember.treasure.model.service.IDispositivoService;

/**
 * The Class DispositivoService.
 */
@Service
class DispositivoService implements IDispositivoService {

	@Inject
	private IDispositivoDAO dispositivoDAO;

	public void insert(Dispositivo dispositivo) throws AppException {
		try {
			Dispositivo dispositivoOld = this.selectByUUID(dispositivo.getUuid());
			if (dispositivoOld != null) {
				throw new AppException("Se está intentando registrar un dispositivo que ya está registrado");
			}
			dispositivoDAO.insert(dispositivo);
		}
		catch (SQLException e) {
			throw new AppException("Se ha producido un error al insertar un dispositivo");
		}
	}

	public void update(Dispositivo dispositivo) throws AppException {
		try {
			dispositivoDAO.update(dispositivo);
		}
		catch (SQLException e) {
			throw new AppException("Se ha producido un error al actualizar un dispositivo");
		}
	}

	public void delete(Dispositivo dispositivo) throws AppException {
		try {
			dispositivoDAO.delete(dispositivo.getId());
		}
		catch (SQLException e) {
			throw new AppException("Se ha producido un error al borrar un dispositivo");
		}
	}

	public Dispositivo retrieve(Integer id) throws AppException {
		try {
			return dispositivoDAO.retrieve(id);
		}
		catch (SQLException e) {
			throw new AppException("Se ha producido un error al recuperar un dispositivo");
		}
	}

	public List<Dispositivo> selectAll() throws AppException {
		try {
			return dispositivoDAO.selectAll();
		}
		catch (SQLException e) {
			throw new AppException("Se ha producido un error al recuperar un dispositivo");
		}
	}

	public Dispositivo selectByUUID(String uuid) throws AppException {
		try {
			return dispositivoDAO.selectByUUID(uuid);
		}
		catch (SQLException e) {
			throw new AppException("Se ha producido un error al recuperar un dispositivo por su uuid");
		}
	}
}