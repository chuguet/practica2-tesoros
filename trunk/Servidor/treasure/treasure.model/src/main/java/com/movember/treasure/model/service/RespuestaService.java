package com.movember.treasure.model.service;

import java.sql.SQLException;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import com.movember.treasure.model.bean.Respuesta;
import com.movember.treasure.model.bean.RespuestaContestada;
import com.movember.treasure.model.dao.IRespuestaDAO;
import com.movember.treasure.model.exception.AppException;

/**
 * The Class RespuestaService.
 */
@Service
class RespuestaService implements IRespuestaService {

	/** The respuesta dao. */
	@Inject
	private IRespuestaDAO respuestaDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.movember.quizz.model.service.IService#insert(com.movember.quizz.model
	 * .bean.AbstractBean)
	 */
	public void insert(Respuesta respuesta) throws AppException {
		try {
			respuestaDAO.insert(respuesta);
		} catch (SQLException e) {
			throw new AppException(
					"Se ha producido un error al insertar una respuesta");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.movember.quizz.model.service.IService#update(com.movember.quizz.model
	 * .bean.AbstractBean)
	 */
	public void update(Respuesta respuesta) throws AppException {
		try {
			respuestaDAO.update(respuesta);
		} catch (SQLException e) {
			throw new AppException(
					"Se ha producido un error al actualizar una respuesta");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.movember.quizz.model.service.IService#delete(com.movember.quizz.model
	 * .bean.AbstractBean)
	 */
	public void delete(Respuesta respuesta) throws AppException {
		try {
			respuestaDAO.delete(respuesta.getId());
		} catch (SQLException e) {
			throw new AppException(
					"Se ha producido un error al eliminar una respuesta");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.movember.quizz.model.service.IService#retrieve(java.lang.Integer)
	 */
	public Respuesta retrieve(Integer id) throws AppException {
		Respuesta respuesta = null;
		try {
			respuesta = respuestaDAO.retrieve(id);
		} catch (SQLException e) {
			throw new AppException(
					"Se ha producido un error al recuperar una respuesta");
		}
		return respuesta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.movember.quizz.model.service.IService#selectAll()
	 */
	public List<Respuesta> selectAll() throws AppException {
		List<Respuesta> respuestas = null;
		try {
			respuestas = respuestaDAO.selectAll();
		} catch (SQLException e) {
			throw new AppException(
					"Se ha producido un error al recuperar todas las respuestas");
		}
		return respuestas;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.movember.quizz.model.service.IRespuestaService#recuperarDePregunta
	 * (java.lang.Integer)
	 */
	public List<Respuesta> recuperarDePregunta(Integer idPregunta)
			throws AppException {
		List<Respuesta> respuestas = null;
		try {
			respuestas = this.respuestaDAO.recuperarDePregunta(idPregunta);
		} catch (SQLException e) {
			throw new AppException(
					"Se ha producido un error al recuperar todas las respuestas de una pregunta");
		}
		return respuestas;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.movember.quizz.model.service.IRespuestaService#recuperarVecesContestadas
	 * (java.lang.Integer)
	 */
	public Integer recuperarVecesContestadas(Integer idRespuesta)
			throws AppException {
		Integer result = null;
		try {
			result = respuestaDAO.recuperarVecesContestadas(idRespuesta);
		} catch (SQLException e) {
			throw new AppException(
					"Se ha producido un error al recuperar una respuesta");
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.movember.quizz.model.service.IRespuestaService#contestar(java.lang
	 * .Integer, java.lang.Integer)
	 */
	public void contestar(Integer idEncuestaContestada, Integer idRespuesta)
			throws AppException {
		try {
			RespuestaContestada respuestaContestada = new RespuestaContestada();
			respuestaContestada.setId_encuesta_contestada(idEncuestaContestada);
			respuestaContestada.setId_respuesta(idRespuesta);
			respuestaDAO.contestar(respuestaContestada);
		} catch (SQLException e) {
			throw new AppException(
					"Se ha producido un error al contestar una respuesta");
		}
	}
}
