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
	private static final String recurso = "encontrarHito";

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
		if (hitoEncontradoDTO == null) {
			return new MensajeDTO("Debe encontrar un hito", false);
		}
		try {
			Hito hito = new Hito();
			hitoEncontradoDTO.toBusiness(hito);
			String securityToken = hitoEncontradoDTO.getSecurityToken();
			String message = rutaService.encontrarHito(hito, securityToken);
			return new MensajeDTO(message, true);
		}
		catch (AppException e) {
			return new MensajeDTO(e.getMessage(), false);
		}
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
		if (rutaDTO == null) {
			return new MensajeDTO("Debe encontrar un hito para poderlo actualizar", false);
		}
		try {
			Ruta ruta = new Ruta();
			rutaDTO.toBusiness(ruta);
			rutaService.update(ruta);
			return new MensajeDTO("El hito encontrado se ha actualizado correctamente", true);
		}
		catch (AppException e) {
			return new MensajeDTO(e.getMessage(), false);
		}
	}
}
