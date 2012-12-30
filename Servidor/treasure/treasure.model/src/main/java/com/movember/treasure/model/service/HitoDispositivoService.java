package com.movember.treasure.model.service;

import java.sql.SQLException;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import com.movember.treasure.model.bean.HitoDispositivo;
import com.movember.treasure.model.dao.IHitoDispositivoDAO;
import com.movember.treasure.model.exception.AppException;

@Service
class HitoDispositivoService implements IHitoDispositivoService {

	@Inject
	private IHitoDispositivoDAO hitoDispositivoDAO;

	public void insert(HitoDispositivo hitoDispositivo) throws AppException {
		try {
			hitoDispositivoDAO.insert(hitoDispositivo);
		}
		catch (SQLException e) {
			throw new AppException("Se ha producido un error al insertar un chequeo de hito");
		}
	}

	public void update(HitoDispositivo hitoDispositivo) throws AppException {
		try {
			hitoDispositivoDAO.update(hitoDispositivo);
		}
		catch (SQLException e) {
			throw new AppException("Se ha producido un error al actualizar un chequeo de hito");
		}
	}

	public void delete(HitoDispositivo hitoDispositivo) throws AppException {
		try {
			hitoDispositivoDAO.delete(hitoDispositivo.getId());
		}
		catch (SQLException e) {
			throw new AppException("Se ha producido un error al borrar un chequeo de hito");
		}
	}

	public HitoDispositivo retrieve(Integer id) throws AppException {
		try {
			return hitoDispositivoDAO.retrieve(id);
		}
		catch (SQLException e) {
			throw new AppException("Se ha producido un error al recuperar un chequeo de hito");
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.movember.quizz.model.service.IService#selectAll()
	 */
	public List<HitoDispositivo> selectAll() throws AppException {
		try {
			return hitoDispositivoDAO.selectAll();
		}
		catch (SQLException e) {
			throw new AppException("Se ha producido un error al recuperar todos los chequeos de hitos");
		}
	}

	public void eliminarDeHito(Integer idHito) throws AppException {
		try {
			hitoDispositivoDAO.deleteByHito(idHito);
		}
		catch (SQLException e) {
			throw new AppException("Se ha producido un error al eliminar todos los chequeos de un hito");
		}
	}
}
