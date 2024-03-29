package com.movember.treasure.controller.control;

import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.movember.treasure.controller.dto.HitoEncontradoDTO;
import com.movember.treasure.controller.dto.MensajeDTO;
import com.movember.treasure.controller.dto.UsuarioDTO;
import com.movember.treasure.model.bean.Dispositivo;
import com.movember.treasure.model.bean.Hito;
import com.movember.treasure.model.bean.Usuario;
import com.movember.treasure.model.exception.AppException;
import com.movember.treasure.model.service.IDispositivoService;
import com.movember.treasure.model.service.IHitoService;
import com.movember.treasure.model.service.IUsuarioService;

/**
 * The Class UsuarioController.
 * 
 * @author Llamaradax
 */
/**
 * @author Huguet
 * 
 */
@Controller
public class RegistroController {

	@Inject
	private IUsuarioService usuarioService;

	@Inject
	private IDispositivoService dispositivoService;

	@Inject
	private IHitoService hitoService;

	@RequestMapping(value = "/registroUsuario", method = RequestMethod.POST)
	public @ResponseBody
	MensajeDTO insertUsuario(@RequestBody UsuarioDTO usuarioDTO) {
		if (usuarioDTO == null) {
			return new MensajeDTO("Un usuario es requerido", false);
		}
		try {
			Usuario usuario = new Usuario();
			usuarioDTO.toBusiness(usuario);
			usuarioService.insertWithDevice(usuario, usuarioDTO.getUuid());
			return new MensajeDTO("Usuario registrado correctamente", true);
		}
		catch (AppException e) {
			return new MensajeDTO(e.getMessage(), false);
		}
	}

	@RequestMapping(value = "/registroDispositivo", method = RequestMethod.POST)
	public @ResponseBody
	MensajeDTO insertDevice(@RequestBody UsuarioDTO usuarioDTO) {
		if (usuarioDTO == null) {
			return new MensajeDTO("Un usuario es requerido", false);
		}
		try {
			Dispositivo dispositivo = new Dispositivo();
			dispositivo.setUuid(usuarioDTO.getUuid());
			dispositivoService.insert(dispositivo);
			return new MensajeDTO("Dispositivo registrado correctamente", true, usuarioDTO.getUuid());
		}
		catch (AppException e) {
			return new MensajeDTO(e.getMessage(), false);
		}
	}

	@RequestMapping(value = "/registroHito", method = RequestMethod.POST)
	public @ResponseBody
	MensajeDTO insertHito(@RequestBody HitoEncontradoDTO hitoEncontradoDTO) {
		if (hitoEncontradoDTO == null) {
			return new MensajeDTO("Un hito es requerido", false);
		}
		try {
			Hito hito = new Hito();
			hitoEncontradoDTO.toBusiness(hito);
			List<String> mensajes = hitoService.checkHito(hito, hitoEncontradoDTO.getUuid());
			return new MensajeDTO("Usuario registrado correctamente", true, mensajes);
		}
		catch (AppException e) {
			return new MensajeDTO(e.getMessage(), false);
		}
	}
}
