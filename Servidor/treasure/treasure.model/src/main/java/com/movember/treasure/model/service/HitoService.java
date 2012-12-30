package com.movember.treasure.model.service;

import java.sql.SQLException;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import com.movember.treasure.model.bean.Hito;
import com.movember.treasure.model.dao.IHitoDAO;
import com.movember.treasure.model.exception.AppException;

/**
 * The Class HitoService.
 */
@Service
class HitoService implements IHitoService {

	/** The hito dao. */
	@Inject
	private IHitoDAO hitoDAO;

	@Inject
	private IHitoUsuarioService hitoUsuarioService;

	public void insert(Hito hito) throws AppException {
		try {
			hitoDAO.insert(hito);
		}
		catch (SQLException e) {
			throw new AppException("Se ha producido un error al insertar un hito");
		}
	}

	public void update(Hito hito) throws AppException {
		try {
			hitoDAO.update(hito);
		}
		catch (SQLException e) {
			throw new AppException("Se ha producido un error al actualizar un hito");
		}
	}

	public void delete(Hito hito) throws AppException {
		try {
			hitoUsuarioService.eliminarDeHito(hito.getId());
			hitoDAO.delete(hito.getId());
		}
		catch (SQLException e) {
			throw new AppException("Se ha producido un error al borrar un hito");
		}
	}

	public Hito retrieve(Integer id) throws AppException {
		try {
			return hitoDAO.retrieve(id);
		}
		catch (SQLException e) {
			throw new AppException("Se ha producido un error al recuperar un hito");
		}
	}

	public List<Hito> selectAll() throws AppException {
		try {
			return hitoDAO.selectAll();
		}
		catch (SQLException e) {
			throw new AppException("Se ha producido un error al recuperar un hito");
		}
	}

	public List<Hito> recuperarDeRuta(Integer idRuta) throws AppException {
		try {
			return this.hitoDAO.recuperarDeRuta(idRuta);
		}
		catch (SQLException e) {
			throw new AppException("Se ha producido un error al recuperar los hitos de una ruta");
		}
	}
}