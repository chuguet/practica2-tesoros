package com.movember.treasure.controller.control;

import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.movember.treasure.controller.dto.ConfiguracionDTO;
import com.movember.treasure.controller.dto.MensajeDTO;
import com.movember.treasure.model.bean.Configuracion;
import com.movember.treasure.model.exception.AppException;
import com.movember.treasure.model.service.IConfiguracionService;

@Controller
public class ConfiguracionController {

	@Inject
	private IConfiguracionService configuracionService;

	/**
	 * Recurso principal del controlador en la peticiones rest
	 * **/
	private static final String recurso = "configuracion";

	/**
	 * Petici�n REST que nos devuelve una sola ruta por ID
	 * 
	 * @param id
	 *            es el id de la ruta
	 * @return devuelve la ruta con el id seleccionado
	 * **/
	@RequestMapping(value = "/" + recurso + "/{id}", method = RequestMethod.GET)
	public @ResponseBody
	ConfiguracionDTO retrieve(@PathVariable("id") Integer id) {
		ConfiguracionDTO configuracionDTO = new ConfiguracionDTO();
		try {
			Configuracion configuracion = this.configuracionService.retrieve();
			configuracionDTO.toRest(configuracion);
		}
		catch (AppException e) {

		}
		return configuracionDTO;
	}

	/**
	 * Petici�n REST que nos devuelve en otra petici�n REST que operaci�n vamos
	 * a realizar
	 * 
	 * @param operacion
	 *            campo que indica si va a ser para un listado o una alta o
	 *            edicion
	 * @param uiModel
	 *            objeto que se le pasa a la vista con la informaci�n requerida
	 * @return devuelve la direcci�n de la petici�n rest
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
	 * Petici�n REST para insertar una ruta nueva en base de datos
	 * 
	 * @param rutaDTO
	 *            ruta a insertar en BD
	 * @return mensaje de confirmaci�n o error de inserci�n
	 * **/
	@RequestMapping(value = "/" + recurso, method = RequestMethod.POST)
	public @ResponseBody
	MensajeDTO insert(@RequestBody ConfiguracionDTO configuracionDTO) {
		if (configuracionDTO == null) {
			return new MensajeDTO("Una configuraci�n es requerida", false);
		}

		try {
			Configuracion configuracion = new Configuracion();
			configuracionDTO.toBusiness(configuracion);
			configuracionService.insert(configuracion);
			return new MensajeDTO("Configuraci�n modificada correctamente", true);
		}
		catch (AppException e) {
			return new MensajeDTO(e.getMessage(), false);
		}
	}
}