package com.movember.treasure.controller.control;

import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.movember.treasure.controller.dto.MensajeDTO;
import com.movember.treasure.model.exception.AppException;
import com.movember.treasure.model.service.IHitoService;

// TODO: Auto-generated Javadoc
/**
 * Controlador de códigos QR
 * 
 * *.
 */
@Controller
public class QRController {

	/** Servicio para manejos de hitos en BBDD. */
	@Inject
	private IHitoService hitoService;

	/** Recurso principal del controlador en la peticiones rest *. */
	private static final String recurso = "codigoQR";

	/**
	 * Petición REST para insertar una ruta nueva en base de datos.
	 * 
	 * @param rutaDTO
	 *            ruta a insertar en BD
	 * @return mensaje de confirmación o error de inserción *
	 */
	@RequestMapping(value = "/" + recurso, method = RequestMethod.POST)
	public @ResponseBody
	MensajeDTO insert(@RequestBody String codigoQR) {
		if (codigoQR == null || codigoQR.length() == 2) {
			return new MensajeDTO("Un código QR es requerido", false);
		}
		try {
			codigoQR = codigoQR.replaceAll("\"", "");
			String rutaCodigo = hitoService.generarQR(codigoQR);
			return new MensajeDTO("", true, rutaCodigo);
		}
		catch (AppException e) {
			return new MensajeDTO(e.getMessage(), false);
		}
	}
}
