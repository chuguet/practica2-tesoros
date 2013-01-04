package com.movember.treasure.model.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;

import com.movember.treasure.model.bean.Dispositivo;
import com.movember.treasure.model.bean.GestorRuta;
import com.movember.treasure.model.bean.Usuario;
import com.movember.treasure.model.dao.IGestorRutaDAO;
import com.movember.treasure.model.dao.IUsuarioDAO;
import com.movember.treasure.model.exception.AppException;

// TODO: Auto-generated Javadoc
/**
 * The Class UsuarioService.
 */
@Service
class UsuarioService implements IUsuarioService {

	/** The usuario dao. */
	@Inject
	private IUsuarioDAO usuarioDAO;

	@Inject
	private IDispositivoService dispositivoService;

	@Inject
	private IGestorRutaDAO gestorRutaDAO;

	/** The password encoder. */
	private Md5PasswordEncoder passwordEncoder = new Md5PasswordEncoder();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.movember.quizz.model.service.IService#insert(com.movember.quizz.model
	 * .bean.AbstractBean)
	 */
	public void insert(Usuario usuario) throws AppException {
		try {
			this.encriptPwd(usuario);
			Usuario usuarioExistente = this.usuarioDAO.selectByUser(usuario
					.getUsuario());
			if (usuarioExistente == null) {
				Integer idUsuario = usuarioDAO.insert(usuario);
				if (usuario.getRutas_asignadas().size() > 0) {
					List<GestorRuta> gestorRutaList = crearListaGestorRuta(
							usuario.getRutas_asignadas(), idUsuario);
					gestorRutaDAO.deleteAllByIdGestor(idUsuario);
					for (GestorRuta gestorRuta : gestorRutaList) {
						gestorRutaDAO.insert(gestorRuta);
					}
				}
			} else {
				throw new AppException(
						"El usuario ya existe en la BBDD. Seleccione uno distinto");
			}
		} catch (SQLException e) {
			throw new AppException(
					"Se ha producido un error de BBDD al insertar el usuario");
		}
	}

	public void insertWithDevice(Usuario usuario, String uuid)
			throws AppException {
		try {
			this.encriptPwd(usuario);
			Usuario usuarioExistente = this.usuarioDAO.selectByUser(usuario
					.getUsuario());
			if (usuarioExistente == null) {
				Dispositivo dispositivo = this.dispositivoService
						.selectByUUID(uuid);
				if (dispositivo == null) {
					dispositivo = new Dispositivo();
					dispositivo.setUuid(uuid);
					this.dispositivoService.insert(dispositivo);
				}
				usuario.setId_dispositivo(dispositivo.getId());
				usuarioDAO.insert(usuario);
			} else {
				throw new AppException(
						"El usuario ya existe en la BBDD. Seleccione uno distinto");
			}
		} catch (SQLException e) {
			throw new AppException(
					"Se ha producido un error de BBDD al insertar el usuario");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.movember.quizz.model.service.IService#update(com.movember.quizz.model
	 * .bean.AbstractBean)
	 */
	public void update(Usuario usuario) throws AppException {
		try {
			this.encriptPwd(usuario);
			if (usuario.getRutas_asignadas().size() > 0) {
				List<GestorRuta> gestorRutaList = crearListaGestorRuta(
						usuario.getRutas_asignadas(), usuario.getId());
				gestorRutaDAO.deleteAllByIdGestor(usuario.getId());
				for (GestorRuta gestorRuta : gestorRutaList) {
					gestorRutaDAO.insert(gestorRuta);
				}
			}
			usuarioDAO.update(usuario);
		} catch (SQLException e) {
			throw new AppException(
					"Se ha producido un error de BBDD al actualizar el usuario");
		}
	}

	private List<GestorRuta> crearListaGestorRuta(
			List<Integer> rutas_asignadas, Integer pIdUsuario) {
		List<GestorRuta> result = new ArrayList<GestorRuta>();
		GestorRuta gestorRuta;
		for (Integer idRuta : rutas_asignadas) {
			gestorRuta = new GestorRuta();
			gestorRuta.setId_ruta(idRuta);
			gestorRuta.setId_usuario(pIdUsuario);
			result.add(gestorRuta);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.movember.quizz.model.service.IService#delete(com.movember.quizz.model
	 * .bean.AbstractBean)
	 */
	public void delete(Usuario usuario) throws AppException {
		try {
			gestorRutaDAO.deleteAllByIdGestor(usuario.getId());
			usuarioDAO.delete(usuario.getId());
		} catch (SQLException e) {
			throw new AppException(
					"Se ha producido un error de BBDD al eliminar el usuario");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.movember.quizz.model.service.IService#retrieve(java.lang.Integer)
	 */
	public Usuario retrieve(Integer id) throws AppException {
		try {
			return usuarioDAO.retrieve(id);
		} catch (SQLException e) {
			throw new AppException(
					"Se ha producido un error de BBDD al recuperar el usuario");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.movember.quizz.model.service.IService#selectAll()
	 */
	public List<Usuario> selectAll() throws AppException {
		try {
			return usuarioDAO.selectAll();
		} catch (SQLException e) {
			throw new AppException(
					"Se ha producido un error de BBDD al recuperar todos los usuarios");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.movember.quizz.model.service.IUsuarioService#selectByUser(java.lang
	 * .String)
	 */
	public Usuario selectByUser(String usuarioNombre) throws AppException {
		try {
			return usuarioDAO.selectByUser(usuarioNombre);
		} catch (SQLException e) {
			throw new AppException(
					"Se ha producido un error de BBDD al recuperar un usuario por su identificador");
		}
	}

	/**
	 * Encript pwd.
	 * 
	 * @param usuario
	 *            the usuario
	 */
	private void encriptPwd(Usuario usuario) {
		String pwd = passwordEncoder.encodePassword(usuario.getPwd(), null);
		usuario.setPwd(pwd);
	}

	public Usuario selectByIdDispositivo(Integer idDispositivo) throws AppException {
		try {
			return usuarioDAO.selectByIdDispositivo(idDispositivo);
		}
		catch (SQLException e) {
			throw new AppException("Se ha producido un error de BBDD al recuperar un usuario por su identificador de dispositivo");
		}
	}
}