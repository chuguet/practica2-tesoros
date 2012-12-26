package com.movember.quizz.controller.control;

import java.util.ArrayList;

import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.movember.quizz.controller.dto.EncuestaDTO;
import com.movember.quizz.controller.dto.EncuestaContestadaDTO;
import com.movember.quizz.controller.dto.MensajeDTO;
import com.movember.quizz.controller.dto.ParametrosEncuestaDTO;
import com.movember.quizz.model.bean.Encuesta;
import com.movember.quizz.model.bean.EncuestaContestada;
import com.movember.quizz.model.bean.ParametrosEncuesta;
import com.movember.quizz.model.exception.AppException;
import com.movember.quizz.model.service.IEncuestaService;


/**
 * Controlador de encuestas
 * 
 * *.
 */
@Controller
public class RellenarEncuestaController {
	
	/** Servicio para manejos de encuestas en BBDD. */
	@Inject
	private IEncuestaService encuestaService;

	/** Recurso principal del controlador en la peticiones rest *. */
	private static final String recurso = "rellenarEncuesta";

	/**
	 * Retrieve the Encuesta.
	 *
	 * @param id the id
	 * @return the encuesta dto
	 */
	@RequestMapping(value = "/" + recurso + "/{id}", method = RequestMethod.GET)
	public @ResponseBody
	EncuestaDTO retrieve(@PathVariable("id") Integer id) {

		EncuestaDTO encuestaDTO = new EncuestaDTO();
		try {
			Encuesta encuesta = this.encuestaService.retrieve(id);
			encuestaDTO.toRest(encuesta);
		}
		catch (AppException e) {

		}
		return encuestaDTO;
	}

	/**
	 * Find the encuesta from user by Id.
	 *
	 * @param request the request
	 * @return the list
	 */
	@RequestMapping(value = "/" + recurso, method = RequestMethod.GET)
	public @ResponseBody
	List<EncuestaDTO> find(HttpServletRequest request) {
		List<EncuestaDTO> encuestasDTO = new ArrayList<EncuestaDTO>();
		try {
			ParametrosEncuestaDTO pDTO = new ParametrosEncuestaDTO();
			pDTO.setId_usuario(request.getParameter("id_usuario"));
			pDTO.setIp_usuario(request.getParameter("ip_usuario"));

			ParametrosEncuesta parametrosEncuesta = new ParametrosEncuesta();
			pDTO.toBusiness(parametrosEncuesta);
			List<Encuesta> encuestas = this.encuestaService.find(parametrosEncuesta);
			for (Encuesta encuesta : encuestas) {
				EncuestaDTO e = new EncuestaDTO();
				e.toRest(encuesta);
				encuestasDTO.add(e);
			}
		}
		catch (AppException e) {

		}
		return encuestasDTO;
	}

	/**
	 * Creates the form.
	 *
	 * @param operacion the operacion
	 * @param uiModel the ui model
	 * @return the string
	 */
	@RequestMapping(value = "/" + recurso + "/form/{operacion}", method = RequestMethod.GET, produces = "text/html")
	public String createForm(@PathVariable("operacion") String operacion, final Model uiModel) {
		uiModel.addAttribute("operacion", operacion);
		if (!operacion.equals("list")) {
			operacion = "form";
		}
		return recurso + "/" + operacion;
	}

	/**
	 * Insert EncuestaContestada.
	 *
	 * @param encuestaRellenadaDTO the encuesta rellenada dto
	 * @return the mensaje dto
	 */
	@RequestMapping(value = "/" + recurso, method = RequestMethod.POST)
	public @ResponseBody
	MensajeDTO insert(@RequestBody EncuestaContestadaDTO encuestaRellenadaDTO) {
		MensajeDTO mensaje = new MensajeDTO();
		if (encuestaRellenadaDTO == null) {
			mensaje.setMensaje("Debe rellenar la encuesta");
			mensaje.setCorrecto(false);
			return mensaje;
		}
		try {
			EncuestaContestada encuestaRellenada = new EncuestaContestada();
			encuestaRellenadaDTO.toBusiness(encuestaRellenada);
			encuestaService.contestar(encuestaRellenada);
			mensaje.setMensaje("Encuesta rellenada correctamente");
		}
		catch (AppException e) {
			mensaje.setMensaje(e.getMessage());
		}
		return mensaje;
	}

	/**
	 * Update the EncuestaDTO.
	 *
	 * @param encuestaDTO the encuesta dto
	 * @return the mensaje dto
	 */
	@RequestMapping(value = "/" + recurso + "/{id}", method = RequestMethod.POST)
	public @ResponseBody
	MensajeDTO update(@RequestBody EncuestaDTO encuestaDTO) {
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
		}
		catch (AppException e) {
			mensaje.setMensaje(e.getMessage());
		}
		return mensaje;
	}

	/**
	 * Removes the.
	 *
	 * @param id the id
	 * @param uiModel the ui model
	 * @return the mensaje dto
	 */
	@RequestMapping(value = "/" + recurso + "/{id}", method = RequestMethod.DELETE)
	public MensajeDTO remove(@PathVariable Integer id, Model uiModel) {

		MensajeDTO mensaje = new MensajeDTO();
		try {
			Encuesta encuesta = new Encuesta();
			encuesta.setId(id);
			this.encuestaService.delete(encuesta);
			mensaje.setMensaje("Encuesta eliminada correctamente");
		}
		catch (AppException e) {
			mensaje.setMensaje(e.getMessage());
		}
		return mensaje;
	}
}
