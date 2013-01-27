package com.movember.treasure.controller.control;

import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.movember.treasure.controller.dto.EstadisticaHitoDTO;
import com.movember.treasure.controller.dto.EstadisticaRutaDTO;
import com.movember.treasure.controller.dto.EstadisticaUsuarioDTO;
import com.movember.treasure.model.bean.EstadisticaHito;
import com.movember.treasure.model.bean.EstadisticaRuta;
import com.movember.treasure.model.bean.EstadisticaUsuario;
import com.movember.treasure.model.exception.AppException;
import com.movember.treasure.model.service.IEstadisticaService;

/**
 * 
 * Controlador de estadisticas
 * 
 * **/
@Controller
public class EstadisticaController {

	/**
	 * Servicio para manejos de estadisticas en BBDD
	 * */
	@Inject
	private IEstadisticaService estadisticaService;

	/**
	 * Petición REST que nos devuelve una sola estadistica por ID
	 * 
	 * @param id
	 *            es el id de la estadistica
	 * @return devuelve la estadistica con el id seleccionado
	 * **/
	@RequestMapping(value = "/estadisticaRuta/{id}", method = RequestMethod.GET)
	public @ResponseBody
	EstadisticaRutaDTO retrieveRuta(@PathVariable("id") Integer id) {
		EstadisticaRutaDTO estadisticaDTO = new EstadisticaRutaDTO();
		try {
			EstadisticaRuta estadistica = this.estadisticaService.retrieveEstadisticaRuta(id);
			// conversion a dto
			estadisticaDTO.toRest(estadistica);
		}
		catch (AppException e) {
			e.printStackTrace();
		}
		return estadisticaDTO;
	}

	@RequestMapping(value = "/estadisticaUsuario/{id}", method = RequestMethod.GET)
	public @ResponseBody
	EstadisticaUsuarioDTO retrieveUsuario(@PathVariable("id") Integer id) {
		EstadisticaUsuarioDTO estadisticaDTO = new EstadisticaUsuarioDTO();
		try {
			EstadisticaUsuario estadistica = this.estadisticaService.retrieveEstadisticaUsuario(id);
			// conversion a dto
			estadisticaDTO.toRest(estadistica);
		}
		catch (AppException e) {
			e.printStackTrace();
		}
		return estadisticaDTO;
	}

	/**
	 * Petición REST que nos devuelve una sola estadistica por ID
	 * 
	 * @param id
	 *            es el id del hito
	 * @return devuelve la estadistica con el id seleccionado
	 * **/
	@RequestMapping(value = "/estadisticaHito/{id}", method = RequestMethod.GET)
	public @ResponseBody
	EstadisticaHitoDTO retrieveHito(@PathVariable("id") Integer id) {
		EstadisticaHitoDTO estadisticaHitoDTO = new EstadisticaHitoDTO();
		try {
			EstadisticaHito estadisticaHito = this.estadisticaService.recuperarContadorHitoPorDias(id);
			estadisticaHitoDTO.toRest(estadisticaHito);
		}
		catch (AppException e) {
			e.printStackTrace();
		}
		return estadisticaHitoDTO;
	}

	/**
	 * Petición REST que nos devuelve en otra petición REST que operación vamos
	 * a realizar
	 * 
	 * @param operación
	 *            es el identificador para saber si vamos a listar
	 * @return devuelve la nueva petición REST
	 * **/
	@RequestMapping(value = "/estadisticaRuta/form/{operacion}", method = RequestMethod.GET, produces = "text/html")
	public String createEstadisticaRuta(@PathVariable("operacion") String operacion, final Model uiModel) {
		return "ruta/estadistica";
	}

	@RequestMapping(value = "/estadisticaUsuario/form/{operacion}", method = RequestMethod.GET, produces = "text/html")
	public String createEstadisticaUsuario(@PathVariable("operacion") String operacion, final Model uiModel) {
		return "usuario/estadistica";
	}
}
