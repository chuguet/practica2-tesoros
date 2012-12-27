package com.movember.treasure.model.service;

import javax.inject.Inject;
import org.springframework.stereotype.Service;

/**
 * The Class EstadisticaService.
 */
@Service
class EstadisticaService implements IEstadisticaService {

	/** The encuesta service. */
	@Inject
	private IRutaService rutaService;

	// /*
	// * (non-Javadoc)
	// * @see
	// * com.movember.quizz.model.service.IEstadisticaService#retrieve(java.lang
	// * .Integer)
	// */
	// public Estadistica retrieve(Integer pId) throws AppException {
	// Estadistica estadistica = new Estadistica();
	//
	// Ruta encuesta = encuestaService.retrieve(pId);
	// estadistica.setEncuesta(encuesta.getNombre());
	// estadistica.setIdEncuesta(encuesta.getId());
	// estadistica.setPreguntas(getPreguntasEstadistica(encuesta.getPreguntas(),
	// encuesta.getId()));
	//
	// return estadistica;
	// }
	//
	// /**
	// * Gets the preguntas estadistica.
	// *
	// * @param preguntas
	// * the preguntas
	// * @param idEncuesta
	// * the id encuesta
	// * @return the preguntas estadistica
	// */
	// private List<PreguntaEstadistica> getPreguntasEstadistica(List<Pregunta>
	// preguntas, Integer idEncuesta) {
	// List<PreguntaEstadistica> result = new ArrayList<PreguntaEstadistica>();
	// PreguntaEstadistica preguntaEstadistica;
	//
	// for (Pregunta pregunta : preguntas) {
	// preguntaEstadistica = new PreguntaEstadistica();
	// preguntaEstadistica.setIdEncuesta(idEncuesta);
	// preguntaEstadistica.setPregunta(pregunta.getPregunta());
	// preguntaEstadistica.setRespuestas(getRespuestasEstadistica(pregunta.getRespuestas(),
	// pregunta.getId()));
	// result.add(preguntaEstadistica);
	// }
	//
	// return result;
	// }
	//
	// /**
	// * Gets the respuestas estadistica.
	// *
	// * @param respuestas
	// * the respuestas
	// * @param idPregunta
	// * the id pregunta
	// * @return the respuestas estadistica
	// */
	// private List<HitoEstadistica> getRespuestasEstadistica(List<Respuesta>
	// respuestas, Integer idPregunta) {
	// List<HitoEstadistica> result = null;
	// try {
	// result = new ArrayList<HitoEstadistica>();
	// HitoEstadistica respuestaEstadistica;
	//
	// for (Respuesta respuesta : respuestas) {
	// respuestaEstadistica = new HitoEstadistica();
	// respuestaEstadistica.setIdPregunta(idPregunta);
	// respuestaEstadistica.setRespuesta(respuesta.getRespuesta());
	// respuestaEstadistica.setVecesContestada(respuestaService.recuperarVecesContestadas(respuesta.getId()));
	// result.add(respuestaEstadistica);
	// }
	//
	// }
	// catch (AppException e) {
	// e.printStackTrace();
	// }
	// return result;
	// }
}
