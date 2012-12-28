package com.movember.treasure.model.service;

import java.sql.SQLException;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import com.movember.treasure.model.bean.Hito;
import com.movember.treasure.model.bean.ParametrosRuta;
import com.movember.treasure.model.bean.Ruta;
import com.movember.treasure.model.dao.IRutaDAO;
import com.movember.treasure.model.exception.AppException;

/**
 * The Class EncuestaService.
 */
@Service
class RutaService implements IRutaService {

	/** The ruta dao. */
	@Inject
	private IRutaDAO rutaDAO;

	/** The hito service. */
	@Inject
	private IHitoService hitoService;

	/*
	 * (non-Javadoc)
	 * @see
	 * com.movember.quizz.model.service.IService#insert(com.movember.quizz.model
	 * .bean.AbstractBean)
	 */
	public void insert(Ruta ruta) throws AppException {
		try {
			rutaDAO.insert(ruta);
			if (ruta.getHitos() != null && ruta.getHitos().size() > 0) {
				for (Hito hito : ruta.getHitos()) {
					hito.setId_ruta(ruta.getId());
					this.hitoService.insert(hito);
				}
			}
		}
		catch (SQLException e) {
			throw new AppException("Se ha producido un error al insertar la ruta");
		}
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.movember.quizz.model.service.IService#update(com.movember.quizz.model
	 * .bean.AbstractBean)
	 */
	public void update(Ruta ruta) throws AppException {
		try {
			rutaDAO.update(ruta);
			if (ruta.getHitos() != null && ruta.getHitos().size() > 0) {
				List<Hito> hitosAntiguas = this.hitoService.recuperarDeRuta(ruta.getId());
				for (Hito hito : ruta.getHitos()) {
					hito.setId_ruta(ruta.getId());
					if (hito.getId() != null) {
						this.hitoService.update(hito);
					}
					else {
						this.hitoService.insert(hito);
					}
				}

				// Borramos las que no est�n
				for (Hito hitoOld : hitosAntiguas) {
					if (!ruta.getHitos().contains(hitoOld)) {
						this.hitoService.delete(hitoOld);
					}
				}
			}
		}
		catch (SQLException e) {
			throw new AppException("Se ha producido un error al actualizar la ruta");
		}
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.movember.quizz.model.service.IService#delete(com.movember.quizz.model
	 * .bean.AbstractBean)
	 */
	public void delete(Ruta ruta) throws AppException {
		try {
			List<Hito> listaHitos = this.hitoService.recuperarDeRuta(ruta.getId());
			if (listaHitos != null && listaHitos.size() > 0) {
				for (Hito hito : listaHitos) {
					this.hitoService.delete(hito);
				}
			}
			rutaDAO.delete(ruta.getId());
		}
		catch (SQLException e) {
			throw new AppException("Se ha producido un error al eliminar la ruta");
		}
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.movember.quizz.model.service.IService#retrieve(java.lang.Integer)
	 */
	public Ruta retrieve(Integer id) throws AppException {
		Ruta ruta = null;
		try {
			ruta = rutaDAO.retrieve(id);
			if (ruta != null) {
				ruta.setHitos(this.hitoService.recuperarDeRuta(id));
			}
		}
		catch (SQLException e) {
			throw new AppException("Se ha producido un error al recuperar la ruta");
		}
		return ruta;
	}

	/*
	 * (non-Javadoc)
	 * @see com.movember.quizz.model.service.IService#selectAll()
	 */
	public List<Ruta> selectAll() throws AppException {
		List<Ruta> rutas = null;
		try {
			rutas = rutaDAO.selectAll();
		}
		catch (SQLException e) {
			throw new AppException("Se ha producido un error al recuperar todas las rutas");
		}
		return rutas;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.movember.quizz.model.service.IEncuestaService#find(com.movember.quizz
	 * .model.bean.ParametrosEncuesta)
	 */
	public List<Ruta> find(ParametrosRuta parametrosEncuesta) throws AppException {
		List<Ruta> rutas = null;
		try {
			rutas = rutaDAO.find(parametrosEncuesta);
		}
		catch (SQLException e) {
			throw new AppException("Se ha producido un error al recuperar un listado de rutas");
		}
		return rutas;
	}

	public String encontrarHito(Hito hito, String securityToken) {
		// TODO Auto-generated method stub
		return null;
	}
}