package com.movember.treasure.controller.control;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.movember.quizz.controller.dto.RutaDTO;
import com.movember.quizz.controller.dto.MensajeDTO;
import com.movember.quizz.model.bean.Encuesta;
import com.movember.quizz.model.exception.AppException;
import com.movember.quizz.model.service.IEncuestaService;

/**
 * 
 * Controlador de encuestas
 * 
 * **/
@Controller
public class EncuestaController {

	/**
	 * Servicio para manejos de encuestas en BBDD
	 * */
	@Inject
	private IEncuestaService encuestaService;

	/**
	 * Recurso principal del controlador en la peticiones rest
	 * **/
	private static final String recurso = "encuesta";

	/**
	 * Petición REST que nos devuelve una sola encuesta por ID
	 * @param id es el id de la encuesta
	 * @return devuelve la encuesta con el id seleccionado
	 * **/
	@RequestMapping(value = "/" + recurso + "/{id}", method = RequestMethod.GET)
	public @ResponseBody
	RutaDTO retrieve(@PathVariable("id") Integer id) {
		RutaDTO encuestaDTO = new RutaDTO();
		try {
			Encuesta encuesta = this.encuestaService.retrieve(id);
			// Comversion a DTO
			encuestaDTO.toRest(encuesta);
		} catch (AppException e) {

		}
		return encuestaDTO;
	}

	/**
	 * Petición REST que nos devuelve todas las encuestas de base de datos
	 * @return devuelve la lista de encuestas
	 * **/
	@RequestMapping(value = "/" + recurso, method = RequestMethod.GET)
	public @ResponseBody
	List<RutaDTO> listAll() {
		List<RutaDTO> encuestasDTO = new ArrayList<RutaDTO>();
		try {
			List<Encuesta> encuestas = this.encuestaService.selectAll();

			for (Encuesta encuesta : encuestas) {
				RutaDTO e = new RutaDTO();
				e.toRest(encuesta);
				encuestasDTO.add(e);
			}

		} catch (AppException e) {

		}
		return encuestasDTO;
	}

	/**
	 * Petición REST que nos devuelve en otra petición REST que operación vamos
	 * a realizar
	 * @param operacion campo que indica si va a ser para un listado o una alta o edicion
	 * @param uiModel objeto que se le pasa a la vista con la información requerida
	 * @return devuelve la dirección de la petición rest
	 */
	@RequestMapping(value = "/" + recurso + "/form/{operacion}", method = RequestMethod.GET, produces = "text/html")
	public String createForm(@PathVariable("operacion") String operacion,
			final Model uiModel) {
		uiModel.addAttribute("operacion", operacion);
		if (!operacion.equals("list")) {
			operacion = "form";
		}
		return recurso + "/" + operacion;
	}

	/**
	 * Petición REST para insertar una encuesta nueva en base de datos
	 * @param encuestaDTO encuesta a insertar en BD
	 * @return mensaje de confirmación o error de inserción
	 * **/
	@RequestMapping(value = "/" + recurso, method = RequestMethod.POST)
	public @ResponseBody
	MensajeDTO insert(@RequestBody RutaDTO encuestaDTO) {
		MensajeDTO mensaje = new MensajeDTO();
		if (encuestaDTO == null) {
			mensaje.setMensaje("Una encuesta es requerida");
			mensaje.setCorrecto(false);
			return mensaje;
		}

		try {
			Encuesta encuesta = new Encuesta();
			encuestaDTO.toBusiness(encuesta);
			encuestaService.insert(encuesta);
			mensaje.setMensaje("Encuesta creada correctamente");
		} catch (AppException e) {
			mensaje.setMensaje(e.getMessage());
		}
		return mensaje;
	}

	/**
	 * Petición REST para modificar una encuesta de base de datos
	 * @param encuestaDTO encuesta a modificar en BD
	 * @return mensaje de confirmación o error de inserción
	 * **/
	@RequestMapping(value = "/" + recurso + "/{id}", method = RequestMethod.POST)
	public @ResponseBody
	MensajeDTO update(@RequestBody RutaDTO encuestaDTO) {
		MensajeDTO mensaje = new MensajeDTO();
		if (encuestaDTO == null) {
			mensaje.setMensaje("Una encuesta es requerida");
			mensaje.setCorrecto(false);
			return mensaje;
		}

		try {
			Encuesta encuesta = new Encuesta();
			encuestaDTO.toBusiness(encuesta);
			encuestaService.update(encuesta);
			mensaje.setMensaje("Encuesta modificada correctamente");
		} catch (AppException e) {
			mensaje.setMensaje(e.getMessage());
		}
		return mensaje;
	}
	
	/**
	 * Petición REST para eliminar una encuesta de base de datos
	 * @param id id de encuesta a eliminar en BD
	 * @return mensaje de confirmación o error de eliminación
	 * **/
	@RequestMapping(value = "/" + recurso + "/{id}", method = RequestMethod.DELETE)
	public MensajeDTO remove(@PathVariable Integer id, Model uiModel) {
		MensajeDTO mensaje = new MensajeDTO();

		try {
			Encuesta encuesta = new Encuesta();
			encuesta.setId(id);
			this.encuestaService.delete(encuesta);
			mensaje.setMensaje("Encuesta eliminada correctamente");
		} catch (AppException e) {
			mensaje.setMensaje(e.getMessage());
		}
		return mensaje;
	}
}
