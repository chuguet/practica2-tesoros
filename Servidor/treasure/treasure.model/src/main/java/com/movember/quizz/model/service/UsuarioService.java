package com.movember.quizz.model.service;

import java.sql.SQLException;
import java.util.List;
import javax.inject.Inject;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import com.movember.quizz.model.bean.Usuario;
import com.movember.quizz.model.dao.IUsuarioDAO;

// TODO: Auto-generated Javadoc
/**
 * The Class UsuarioService.
 */
@Service
class UsuarioService implements IUsuarioService {

	/** The usuario dao. */
	@Inject
	private IUsuarioDAO usuarioDAO;

	/** The password encoder. */
	private Md5PasswordEncoder passwordEncoder = new Md5PasswordEncoder();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.movember.quizz.model.service.IService#insert(com.movember.quizz.model
	 * .bean.AbstractBean)
	 */
	public void insert(Usuario usuario) {
		try {
			this.encriptPwd(usuario);
			usuarioDAO.insert(usuario);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.movember.quizz.model.service.IService#update(com.movember.quizz.model
	 * .bean.AbstractBean)
	 */
	public void update(Usuario usuario) {
		try {
			this.encriptPwd(usuario);
			usuarioDAO.update(usuario);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.movember.quizz.model.service.IService#delete(com.movember.quizz.model
	 * .bean.AbstractBean)
	 */
	public void delete(Usuario usuario) {
		try {
			usuarioDAO.delete(usuario.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.movember.quizz.model.service.IService#retrieve(java.lang.Integer)
	 */
	public Usuario retrieve(Integer id) {
		Usuario usuario = null;
		try {
			usuario = usuarioDAO.retrieve(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usuario;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.movember.quizz.model.service.IService#selectAll()
	 */
	public List<Usuario> selectAll() {
		List<Usuario> usuarios = null;
		try {
			usuarios = usuarioDAO.selectAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usuarios;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.movember.quizz.model.service.IUsuarioService#selectByUser(java.lang
	 * .String)
	 */
	public Usuario selectByUser(String usuarioNombre) {
		Usuario usuario = null;
		try {
			usuario = usuarioDAO.selectByUser(usuarioNombre);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usuario;
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

}
