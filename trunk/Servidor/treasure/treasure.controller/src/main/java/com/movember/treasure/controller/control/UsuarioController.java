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
import com.movember.treasure.controller.dto.UsuarioDTO;
import com.movember.treasure.model.bean.Usuario;
import com.movember.treasure.model.exception.AppException;
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
public class UsuarioController {

	/** The usuario service. */
	@Inject
	private IUsuarioService usuarioService;

	/** The Constant recurso. */
	private static final String recurso = "usuario";

	/**
	 * Retrieve one User.
	 * 
	 * @param id
	 *            the id
	 * @return the usuario dto
	 */
	@RequestMapping(value = "/" + recurso + "/{id}", method = RequestMethod.GET)
	public @ResponseBody
	UsuarioDTO retrieve(@PathVariable("id") Integer id) {
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		try {
			Usuario usuario = this.usuarioService.retrieve(id);
			usuarioDTO.toRest(usuario);
		}
		catch (AppException e) {

		}
		return usuarioDTO;
	}

	/**
	 * List all users.
	 * 
	 * @return the list
	 */
	@RequestMapping(value = "/" + recurso, method = RequestMethod.GET)
	public @ResponseBody
	List<UsuarioDTO> listAll() {
		List<UsuarioDTO> usuariosDTO = new ArrayList<UsuarioDTO>();

		try {
			List<Usuario> usuarios = this.usuarioService.selectAll();

			for (Usuario usuario : usuarios) {
				UsuarioDTO e = new UsuarioDTO();
				e.toRest(usuario);
				usuariosDTO.add(e);
			}
		}
		catch (AppException e) {

		}
		return usuariosDTO;
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
	 * Insert the user.
	 * 
	 * @param usuarioDTO
	 *            the usuario dto
	 * @return the mensaje dto
	 */
	@RequestMapping(value = "/" + recurso, method = RequestMethod.POST)
	public @ResponseBody
	MensajeDTO insert(@RequestBody UsuarioDTO usuarioDTO) {
		if (usuarioDTO == null) {
			return new MensajeDTO("Un usuario es requerido", false);
		}
		try {
			Usuario usuario = new Usuario();
			usuarioDTO.toBusiness(usuario);
			usuarioService.insert(usuario);
			return new MensajeDTO("Usuario creado correctamente", true);
		}
		catch (AppException e) {
			return new MensajeDTO(e.getMessage(), false);
		}
	}

	/**
	 * Update the user.
	 * 
	 * @param usuarioDTO
	 *            the usuario dto
	 * @return the mensaje dto
	 */
	@RequestMapping(value = "/" + recurso + "/{id}", method = RequestMethod.POST)
	public @ResponseBody
	MensajeDTO update(@RequestBody UsuarioDTO usuarioDTO) {
		if (usuarioDTO == null) {
			return new MensajeDTO("Un usuario es requerido", false);
		}
		try {
			Usuario usuario = new Usuario();
			usuarioDTO.toBusiness(usuario);
			usuarioService.update(usuario);
			return new MensajeDTO("Usuario modificado correctamente", true);
		}
		catch (AppException e) {
			return new MensajeDTO(e.getMessage(), false);
		}
	}

	/**
	 * Removes the user by id.
	 * 
	 * @param id
	 *            the id
	 * @param uiModel
	 *            the ui model
	 * @return the mensaje dto
	 */
	@RequestMapping(value = "/usuario/{id}", method = RequestMethod.DELETE)
	public @ResponseBody
	MensajeDTO remove(@PathVariable Integer id, Model uiModel) {
		if (id == null) {
			return new MensajeDTO("Un usuario es requerido", false);
		}
		try {
			Usuario usuario = new Usuario();
			usuario.setId(id);
			this.usuarioService.delete(usuario);
			return new MensajeDTO("Usuario eliminado correctamente", true);
		}
		catch (AppException e) {
			return new MensajeDTO(e.getMessage(), false);
		}
	}
}
