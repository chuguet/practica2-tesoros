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
import com.movember.treasure.controller.dto.MensajeDTO;
import com.movember.treasure.controller.dto.RutaDTO;
import com.movember.treasure.model.bean.Ruta;
import com.movember.treasure.model.exception.AppException;
import com.movember.treasure.model.service.IRutaService;

/**
 * 
 * Controlador de rutas
 * 
 * **/
@Controller
public class RutaController {

	/**
	 * Servicio para manejos de rutas en BBDD
	 * */
	@Inject
	private IRutaService rutaService;

	/**
	 * Recurso principal del controlador en la peticiones rest
	 * **/
	private static final String recurso = "ruta";

	/**
	 * Petici�n REST que nos devuelve una sola ruta por ID
	 * 
	 * @param id
	 *            es el id de la ruta
	 * @return devuelve la ruta con el id seleccionado
	 * **/
	@RequestMapping(value = "/" + recurso + "/{id}", method = RequestMethod.GET)
	public @ResponseBody
	RutaDTO retrieve(@PathVariable("id") Integer id) {
		RutaDTO rutaDTO = new RutaDTO();
		try {
			Ruta ruta = this.rutaService.retrieve(id);
			// Comversion a DTO
			rutaDTO.toRest(ruta);
		}
		catch (AppException e) {

		}
		return rutaDTO;
	}

	/**
	 * Petici�n REST que nos devuelve todas las rutas de base de datos
	 * 
	 * @return devuelve la lista de rutas
	 * **/
	@RequestMapping(value = "/" + recurso, method = RequestMethod.GET)
	public @ResponseBody
	List<RutaDTO> listAll() {
		List<RutaDTO> rutasDTO = new ArrayList<RutaDTO>();
		try {
			List<Ruta> rutas = this.rutaService.selectAll();

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
	MensajeDTO insert(@RequestBody RutaDTO rutaDTO) {
		if (rutaDTO == null) {
			return new MensajeDTO("Una ruta es requerida", false);
		}

		try {
			Ruta ruta = new Ruta();
			rutaDTO.toBusiness(ruta);
			rutaService.insert(ruta);
			return new MensajeDTO("Ruta creada correctamente", true);
		}
		catch (AppException e) {
			return new MensajeDTO(e.getMessage(), false);
		}
	}

	/**
	 * Petici�n REST para modificar una ruta de base de datos
	 * 
	 * @param rutaDTO
	 *            ruta a modificar en BD
	 * @return mensaje de confirmaci�n o error de inserci�n
	 * **/
	@RequestMapping(value = "/" + recurso + "/{id}", method = RequestMethod.POST)
	public @ResponseBody
	MensajeDTO update(@RequestBody RutaDTO rutaDTO) {
		if (rutaDTO == null) {
			return new MensajeDTO("Una ruta es requerida", false);
		}

		try {
			Ruta ruta = new Ruta();
			rutaDTO.toBusiness(ruta);
			rutaService.update(ruta);
			return new MensajeDTO("Ruta modificada correctamente", true);
		}
		catch (AppException e) {
			return new MensajeDTO(e.getMessage(), false);
		}
	}

	/**
	 * Petici�n REST para eliminar una ruta de base de datos
	 * 
	 * @param id
	 *            id de ruta a eliminar en BD
	 * @return mensaje de confirmaci�n o error de eliminaci�n
	 * **/
	@RequestMapping(value = "/" + recurso + "/{id}", method = RequestMethod.DELETE)
	public @ResponseBody
	MensajeDTO remove(@PathVariable Integer id, Model uiModel) {
		if (id == null) {
			return new MensajeDTO("Una ruta es requerida", false);
		}

		try {
			Ruta ruta = new Ruta();
			ruta.setId(id);
			this.rutaService.delete(ruta);
			return new MensajeDTO("Ruta eliminada correctamente", true);
		}
		catch (AppException e) {
			return new MensajeDTO(e.getMessage(), false);
		}
	}
}
