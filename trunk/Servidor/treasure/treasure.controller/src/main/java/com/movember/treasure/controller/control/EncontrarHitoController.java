package com.movember.treasure.controller.control;

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
import com.movember.treasure.controller.dto.HitoEncontradoDTO;
import com.movember.treasure.controller.dto.MensajeDTO;
import com.movember.treasure.controller.dto.ParametrosRutaDTO;
import com.movember.treasure.controller.dto.RutaDTO;
import com.movember.treasure.model.bean.Hito;
import com.movember.treasure.model.bean.ParametrosRuta;
import com.movember.treasure.model.bean.Ruta;
import com.movember.treasure.model.exception.AppException;
import com.movember.treasure.model.service.IRutaService;

/**
 * Controlador de rutas
 * 
 * *.
 */
@Controller
public class EncontrarHitoController {

	/** Servicio para manejos de rutas en BBDD. */
	@Inject
	private IRutaService rutaService;

	/** Recurso principal del controlador en la peticiones rest *. */
	private static final String recurso = "rellenarRuta";

	/**
	 * Retrieve the Ruta.
	 * 
	 * @param id
	 *            the id
	 * @return the ruta dto
	 */
	@RequestMapping(value = "/" + recurso + "/{id}", method = RequestMethod.GET)
	public @ResponseBody
	RutaDTO retrieve(@PathVariable("id") Integer id) {

		RutaDTO rutaDTO = new RutaDTO();
		try {
			Ruta ruta = this.rutaService.retrieve(id);
			rutaDTO.toRest(ruta);
		}
		catch (AppException e) {

		}
		return rutaDTO;
	}

	/**
	 * Find the ruta from user by Id.
	 * 
	 * @param request
	 *            the request
	 * @return the list
	 */
	@RequestMapping(value = "/" + recurso, method = RequestMethod.GET)
	public @ResponseBody
	List<RutaDTO> find(HttpServletRequest request) {
		List<RutaDTO> rutasDTO = new ArrayList<RutaDTO>();
		try {
			ParametrosRutaDTO pDTO = new ParametrosRutaDTO();
			pDTO.setSecurityToken(request.getParameter("securityToken"));

			ParametrosRuta parametrosRuta = new ParametrosRuta();
			pDTO.toBusiness(parametrosRuta);
			List<Ruta> rutas = this.rutaService.find(parametrosRuta);
			for (Ruta ruta : rutas) {
				RutaDTO e = new RutaDTO();
				e.toRest(ruta);
				rutasDTO.add(e);
			}
		}
		catch (AppException e) {

		}
		return rutasDTO;
	}

	/**
	 * Creates the form.
	 * 
	 * @param operacion
	 *            the operacion
	 * @param uiModel
	 *            the ui model
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
	 * Insert RutaContestada.
	 * 
	 * @param rutaRellenadaDTO
	 *            the ruta rellenada dto
	 * @return the mensaje dto
	 */
	@RequestMapping(value = "/" + recurso, method = RequestMethod.POST)
	public @ResponseBody
	MensajeDTO insert(@RequestBody HitoEncontradoDTO hitoEncontradoDTO) {
		MensajeDTO mensaje = new MensajeDTO();
		if (hitoEncontradoDTO == null) {
			mensaje.setMensaje("Debe encontrar un hito");
			mensaje.setCorrecto(false);
			return mensaje;
		}
		try {
			Hito hito = new Hito();
			hitoEncontradoDTO.toBusiness(hito);
			String securityToken = hitoEncontradoDTO.getSecurityToken();
			String message = rutaService.encontrarHito(hito, securityToken);
			mensaje.setMensaje(message);
		}
		catch (AppException e) {
			mensaje.setMensaje(e.getMessage());
		}
		return mensaje;
	}

	/**
	 * Update the RutaDTO.
	 * 
	 * @param rutaDTO
	 *            the ruta dto
	 * @return the mensaje dto
	 */
	@RequestMapping(value = "/" + recurso + "/{id}", method = RequestMethod.POST)
	public @ResponseBody
	MensajeDTO update(@RequestBody RutaDTO rutaDTO) {
		MensajeDTO mensaje = new MensajeDTO();
		if (rutaDTO == null) {
			mensaje.setMensaje("Una ruta es requerida");
			mensaje.setCorrecto(false);
			return mensaje;
		}
		try {
			Ruta ruta = new Ruta();
			rutaDTO.toBusiness(ruta);
			rutaService.update(ruta);
			mensaje.setMensaje("Ruta modificada correctamente");
		}
		catch (AppException e) {
			mensaje.setMensaje(e.getMessage());
		}
		return mensaje;
	}

	/**
	 * Removes the.
	 * 
	 * @param id
	 *            the id
	 * @param uiModel
	 *            the ui model
	 * @return the mensaje dto
	 */
	@RequestMapping(value = "/" + recurso + "/{id}", method = RequestMethod.DELETE)
	public MensajeDTO remove(@PathVariable Integer id, Model uiModel) {

		MensajeDTO mensaje = new MensajeDTO();
		try {
			Ruta ruta = new Ruta();
			ruta.setId(id);
			this.rutaService.delete(ruta);
			mensaje.setMensaje("Ruta eliminada correctamente");
		}
		catch (AppException e) {
			mensaje.setMensaje(e.getMessage());
		}
		return mensaje;
	}
}
