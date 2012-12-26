package com.movember.treasure.model.service;

import java.sql.SQLException;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import com.movember.treasure.model.bean.HitoUsuario;
import com.movember.treasure.model.bean.ParametrosRuta;
import com.movember.treasure.model.bean.Pregunta;
import com.movember.treasure.model.bean.Ruta;
import com.movember.treasure.model.dao.IEncuestaDAO;
import com.movember.treasure.model.exception.AppException;


/**
 * The Class EncuestaService.
 */
@Service
class EncuestaService implements IEncuestaService {

	/** The encuesta dao. */
	@Inject
	private IEncuestaDAO encuestaDAO;

	/** The pregunta service. */
	@Inject
	private IPreguntaService preguntaService;

	/** The respuesta service. */
	@Inject
	private IRespuestaService respuestaService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.movember.quizz.model.service.IService#insert(com.movember.quizz.model
	 * .bean.AbstractBean)
	 */
	public void insert(Ruta encuesta) throws AppException {
		try {
			encuestaDAO.insert(encuesta);
			if (encuesta.getPreguntas() != null
					&& encuesta.getPreguntas().size() > 0) {
				for (Pregunta pregunta : encuesta.getPreguntas()) {
					pregunta.setId_encuesta(encuesta.getId());
					this.preguntaService.insert(pregunta);
				}
			}
		} catch (SQLException e) {
			throw new AppException(
					"Se ha producido un error al insertar la encuesta");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.movember.quizz.model.service.IService#update(com.movember.quizz.model
	 * .bean.AbstractBean)
	 */
	public void update(Ruta encuesta) throws AppException {
		try {
			encuestaDAO.update(encuesta);
			if (encuesta.getPreguntas() != null
					&& encuesta.getPreguntas().size() > 0) {
				List<Pregunta> preguntasAntiguas = this.preguntaService
						.recuperarDeEncuesta(encuesta.getId());
				for (Pregunta pregunta : encuesta.getPreguntas()) {
					if (pregunta.getId() != null) {
						this.preguntaService.update(pregunta);
					} else {
						this.preguntaService.insert(pregunta);
					}
				}

				// Borramos las que no est�n
				for (Pregunta preguntaOld : preguntasAntiguas) {
					if (!encuesta.getPreguntas().contains(preguntaOld)) {
						this.preguntaService.delete(preguntaOld);
					}
				}
			}
		} catch (SQLException e) {
			throw new AppException(
					"Se ha producido un error al actualizar la encuesta");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.movember.quizz.model.service.IService#delete(com.movember.quizz.model
	 * .bean.AbstractBean)
	 */
	public void delete(Ruta encuesta) throws AppException {
		try {
			if (encuesta.getPreguntas() != null
					&& encuesta.getPreguntas().size() > 0) {
				for (Pregunta pregunta : encuesta.getPreguntas()) {
					this.preguntaService.delete(pregunta);
				}
			}
			encuestaDAO.delete(encuesta.getId());
		} catch (SQLException e) {
			throw new AppException(
					"Se ha producido un error al eliminar la encuesta");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.movember.quizz.model.service.IService#retrieve(java.lang.Integer)
	 */
	public Ruta retrieve(Integer id) throws AppException {
		Ruta encuesta = null;
		try {
			encuesta = encuestaDAO.retrieve(id);
			if (encuesta != null) {
				encuesta.setPreguntas(this.preguntaService
						.recuperarDeEncuesta(id));
			}
		} catch (SQLException e) {
			throw new AppException(
					"Se ha producido un error al recuperar la encuesta");
		}
		return encuesta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.movember.quizz.model.service.IService#selectAll()
	 */
	public List<Ruta> selectAll() throws AppException {
		List<Ruta> encuestas = null;
		try {
			encuestas = encuestaDAO.selectAll();
		} catch (SQLException e) {
			throw new AppException(
					"Se ha producido un error al recuperar todas las encuestas");
		}
		return encuestas;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.movember.quizz.model.service.IEncuestaService#find(com.movember.quizz
	 * .model.bean.ParametrosEncuesta)
	 */
	public List<Ruta> find(ParametrosRuta parametrosEncuesta)
			throws AppException {
		List<Ruta> encuestas = null;
		try {
			encuestas = encuestaDAO.find(parametrosEncuesta);
		} catch (SQLException e) {
			throw new AppException(
					"Se ha producido un error al recuperar un listado de encuestas");
		}
		return encuestas;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.movember.quizz.model.service.IEncuestaService#contestar(com.movember
	 * .quizz.model.bean.EncuestaContestada)
	 */
	public void contestar(HitoUsuario encuestaContestada)
			throws AppException {
		try {
			encuestaDAO.contestar(encuestaContestada);
			for (Integer idRespuesta : encuestaContestada
					.getIdRespuestasContestadas()) {
				respuestaService.contestar(encuestaContestada.getId(),
						idRespuesta);
			}
		} catch (SQLException e) {
			throw new AppException(
					"Se ha producido un error al contestar una encuesta");
		}
	}
}