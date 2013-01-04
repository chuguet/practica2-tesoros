package com.movember.treasure.controller.control;

import java.security.Principal;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.movember.treasure.model.bean.Usuario;

/**
 * Controlador de login
 * 
 * *.
 */
@Controller
public class LoginController {

	/**
	 * Petición REST que recoge los datos del usuario y nos lleva a la pagina
	 * principal *.
	 * 
	 * @param model
	 *            the model
	 * @param principal
	 *            the principal
	 * @param request
	 *            the request
	 * @return the string
	 */
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String printWelcome(ModelMap model, Principal principal,
			HttpServletRequest request) {
		UsernamePasswordAuthenticationToken u = (UsernamePasswordAuthenticationToken) principal;
		if (u != null) {
			Usuario usuario = (Usuario) u.getPrincipal();
			model.addAttribute("nombre", usuario.getNombre());
			model.addAttribute("apellidos", usuario.getApellidos());
			model.addAttribute("id_usuario", usuario.getId());
		} else {
			model.addAttribute("nombre", "Usuario anónimo");
			model.addAttribute("apellidos", null);
			model.addAttribute("id_usuario", null);
		}
		model.addAttribute("ip_usuario", request.getRemoteAddr());
		model.addAttribute("mobile", false);
		if (u != null) {
			if (u.getAuthorities().contains(
					new GrantedAuthorityImpl("ROLE_ADMIN"))
					|| u.getAuthorities().contains(
							new GrantedAuthorityImpl("ROLE_GESTOR"))) {
				return "home";
			}
			model.addAttribute("noAccess", true);
			return "login";
		} else {
			return "login";
		}
	}

	/**
	 * Petición REST para comprobar que el logueo se hace desde un dispositivo
	 * movil.
	 * 
	 * @param model
	 *            the model
	 * @param request
	 *            the request
	 * @return the string
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(ModelMap model, HttpServletRequest request) {
		return "login";
	}

	/**
	 * Petición REST que tras realizar un logueo fallido nos lleva a la pantalla
	 * de login de nuevo.
	 * 
	 * @param model
	 *            the model
	 * @param request
	 *            the request
	 * @return the string
	 */
	@RequestMapping(value = "/loginfailed", method = RequestMethod.GET)
	public String loginerror(ModelMap model, HttpServletRequest request) {
		model.addAttribute("error", "true");
		return "login";
	}

	/**
	 * Peticion REST que nos realiza el logout de la aplicación y nos lleva a la
	 * pagina de login.
	 * 
	 * @param model
	 *            the model
	 * @param request
	 *            the request
	 * @return the string
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(ModelMap model, HttpServletRequest request) {
		return "login";
	}

}