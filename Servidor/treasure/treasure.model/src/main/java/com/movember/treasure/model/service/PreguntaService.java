package com.movember.treasure.model.service;

import java.sql.SQLException;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import com.movember.treasure.model.bean.Pregunta;
import com.movember.treasure.model.bean.Respuesta;
import com.movember.treasure.model.dao.IPreguntaDAO;
import com.movember.treasure.model.exception.AppException;


/**
 * The Class PreguntaService.
 */
@Service
class PreguntaService implements IPreguntaService {

	/** The pregunta dao. */
	@Inject
	private IPreguntaDAO preguntaDAO;

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
	public void insert(Pregunta pregunta) throws AppException {
		try {
			preguntaDAO.insert(pregunta);
			if (pregunta.getRespuestas() != null
					&& pregunta.getRespuestas().size() > 0) {
				for (Respuesta respuesta : pregunta.getRespuestas()) {
					respuesta.setId_pregunta(pregunta.getId());
					this.respuestaService.insert(respuesta);
				}
			}
		} catch (SQLException e) {
			throw new AppException(
					"Se ha producido un error al insertar una pregunta");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.movember.quizz.model.service.IService#update(com.movember.quizz.model
	 * .bean.AbstractBean)
	 */
	public void update(Pregunta pregunta) throws AppException {
		try {
			preguntaDAO.update(pregunta);
			if (pregunta.getRespuestas() != null
					&& pregunta.getRespuestas().size() > 0) {
				List<Respuesta> respuestasAntiguas = this.respuestaService
						.recuperarDePregunta(pregunta.getId());
				for (Respuesta respuesta : pregunta.getRespuestas()) {
					if (respuesta.getId() != null) {
						this.respuestaService.update(respuesta);
					} else {
						this.respuestaService.insert(respuesta);
					}
				}

				// Borramos las que no estén
				for (Respuesta respuestaOld : respuestasAntiguas) {
					if (!pregunta.getRespuestas().contains(respuestaOld)) {
						this.respuestaService.delete(respuestaOld);
					}
				}
			}
		} catch (SQLException e) {
			throw new AppException(
					"Se ha producido un error al actualizar una pregunta");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.movember.quizz.model.service.IService#delete(com.movember.quizz.model
	 * .bean.AbstractBean)
	 */
	public void delete(Pregunta pregunta) throws AppException {
		try {
			if (pregunta.getRespuestas() != null
					&& pregunta.getRespuestas().size() > 0) {
				for (Respuesta respuesta : pregunta.getRespuestas()) {
					this.respuestaService.delete(respuesta);
				}
			}
			preguntaDAO.delete(pregunta.getId());
		} catch (SQLException e) {
			throw new AppException(
					"Se ha producido un error al borrar una pregunta");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.movember.quizz.model.service.IService#retrieve(java.lang.Integer)
	 */
	public Pregunta retrieve(Integer id) throws AppException {
		try {
			return preguntaDAO.retrieve(id);
		} catch (SQLException e) {
			throw new AppException(
					"Se ha producido un error al recuperar una pregunta");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.movember.quizz.model.service.IService#selectAll()
	 */
	public List<Pregunta> selectAll() throws AppException {
		try {
			return preguntaDAO.selectAll();
		} catch (SQLException e) {
			throw new AppException(
					"Se ha producido un error al recuperar una pregunta");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.movember.quizz.model.service.IPreguntaService#recuperarDeEncuesta
	 * (java.lang.Integer)
	 */
	public List<Pregunta> recuperarDeEncuesta(Integer idEncuesta)
			throws AppException {
		try {
			List<Pregunta> preguntas = this.preguntaDAO
					.recuperarDeEncuesta(idEncuesta);
			for (Pregunta pregunta : preguntas) {
				pregunta.setRespuestas(this.respuestaService
						.recuperarDePregunta(pregunta.getId()));
			}
			return preguntas;
		} catch (SQLException e) {
			throw new AppException(
					"Se ha producido un error al recuperar las preguntas de una encuesta");
		}
	}
}
