package com.movember.treasure.controller.control;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.movember.treasure.controller.dto.RutaDTO;
import com.movember.treasure.model.bean.Ruta;
import com.movember.treasure.model.exception.AppException;
import com.movember.treasure.model.service.IGestorRutaService;
import com.movember.treasure.model.service.IRutaService;

/**
 * 
 * Controlador de rutas
 * 
 * **/
@Controller
public class GestorController {

	@Inject
	private IRutaService rutaService;

	@Inject
	private IGestorRutaService gestorRutaService;

	/**
	 * Recurso principal del controlador en la peticiones rest
	 * **/
	private static final String recurso = "gestor";

	/**
	 * Petición REST que nos devuelve todas las rutas de base de datos
	 * 
	 * @return devuelve la lista de rutas
	 * **/
	@RequestMapping(value = "/" + recurso + "/{id}", method = RequestMethod.GET)
	public @ResponseBody
	List<RutaDTO> listAll(@PathVariable("id")Integer pIdGestor) {
		List<RutaDTO> rutasDTO = new ArrayList<RutaDTO>();
		try {
			List<Integer> id_rutas = this.gestorRutaService
					.retrieveByIdGestor(pIdGestor);
			List<Ruta> rutas = new ArrayList<Ruta>();
			
			for (Integer id_ruta : id_rutas) {
				rutas.add(rutaService.retrieve(id_ruta));
			}
			for (Ruta ruta : rutas) {
				RutaDTO e = new RutaDTO();
				e.toRest(ruta);
				rutasDTO.add(e);
			}

		} catch (AppException e) {

		}
		return rutasDTO;
	}

	/**
	 * Petición REST que nos devuelve en otra petición REST que operación vamos
	 * a realizar
	 * 
	 * @param operacion
	 *            campo que indica si va a ser para un listado o una alta o
	 *            edicion
	 * @param uiModel
	 *            objeto que se le pasa a la vista con la información requerida
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

}
