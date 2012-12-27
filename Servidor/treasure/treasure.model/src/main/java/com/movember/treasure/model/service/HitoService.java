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

	/*
	 * (non-Javadoc)
	 * @see
	 * com.movember.quizz.model.service.IService#insert(com.movember.quizz.model
	 * .bean.AbstractBean)
	 */
	public void insert(Hito hito) throws AppException {
		try {
			hitoDAO.insert(hito);
		}
		catch (SQLException e) {
			throw new AppException("Se ha producido un error al insertar una hito");
		}
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.movember.quizz.model.service.IService#update(com.movember.quizz.model
	 * .bean.AbstractBean)
	 */
	public void update(Hito hito) throws AppException {
		try {
			hitoDAO.update(hito);
		}
		catch (SQLException e) {
			throw new AppException("Se ha producido un error al actualizar una hito");
		}
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.movember.quizz.model.service.IService#delete(com.movember.quizz.model
	 * .bean.AbstractBean)
	 */
	public void delete(Hito hito) throws AppException {
		try {
			hitoDAO.delete(hito.getId());
		}
		catch (SQLException e) {
			throw new AppException("Se ha producido un error al borrar una hito");
		}
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.movember.quizz.model.service.IService#retrieve(java.lang.Integer)
	 */
	public Hito retrieve(Integer id) throws AppException {
		try {
			return hitoDAO.retrieve(id);
		}
		catch (SQLException e) {
			throw new AppException("Se ha producido un error al recuperar una hito");
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.movember.quizz.model.service.IService#selectAll()
	 */
	public List<Hito> selectAll() throws AppException {
		try {
			return hitoDAO.selectAll();
		}
		catch (SQLException e) {
			throw new AppException("Se ha producido un error al recuperar una hito");
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.movember.quizz.model.service.IHitoService#recuperarDeRuta
	 * (java.lang.Integer)
	 */
	public List<Hito> recuperarDeRuta(Integer idRuta) throws AppException {
		try {
			List<Hito> hitos = this.hitoDAO.recuperarDeRuta(idRuta);
			return hitos;
		}
		catch (SQLException e) {
			throw new AppException("Se ha producido un error al recuperar las hitos de una ruta");
		}
	}
}
