package com.movember.treasure.controller.dto;

import com.movember.treasure.model.bean.Usuario;
import com.movember.treasure.model.exception.AppException;

// TODO: Auto-generated Javadoc
/**
 * The Class UsuarioDTO.
 */
public class UsuarioDTO extends AbstractDTO {

	/** The nombre. */
	private String nombre;

	/** The apellidos. */
	private String apellidos;

	/** The email. */
	private String email;

	/** The usuario. */
	private String usuario;

	/** The pwd. */
	private String pwd;

	/** The admin. */
	private Integer admin;

	/**
	 * Gets the nombre.
	 * 
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Sets the nombre.
	 * 
	 * @param nombre
	 *            the new nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Gets the apellidos.
	 * 
	 * @return the apellidos
	 */
	public String getApellidos() {
		return apellidos;
	}

	/**
	 * Sets the apellidos.
	 * 
	 * @param apellidos
	 *            the new apellidos
	 */
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	/**
	 * Gets the email.
	 * 
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 * 
	 * @param email
	 *            the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the usuario.
	 * 
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * Sets the usuario.
	 * 
	 * @param usuario
	 *            the new usuario
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * Gets the pwd.
	 * 
	 * @return the pwd
	 */
	public String getPwd() {
		return pwd;
	}

	/**
	 * Sets the pwd.
	 * 
	 * @param pwd
	 *            the new pwd
	 */
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	/**
	 * Gets the admin.
	 * 
	 * @return the admin
	 */
	public Integer getAdmin() {
		return admin;
	}

	/**
	 * Sets the admin.
	 * 
	 * @param admin
	 *            the new admin
	 */
	public void setAdmin(Integer admin) {
		this.admin = admin;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.movember.quizz.controller.dto.AbstractDTO#toRest(java.lang.Object)
	 */
	@Override
	public void toRest(Object object) throws AppException {
		Usuario usuario = (Usuario) object;
		this.setId(usuario.getId());
		this.nombre = usuario.getNombre();
		this.apellidos = usuario.getApellidos();
		this.email = usuario.getEmail();
		this.usuario = usuario.getUsuario();
		this.pwd = usuario.getPwd();
		this.admin = usuario.getAdmin();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.movember.quizz.controller.dto.AbstractDTO#toBusiness(java.lang.Object
	 * )
	 */
	@Override
	public void toBusiness(Object object) throws AppException {
		Usuario usuario = (Usuario) object;
		usuario.setId(this.getId());
		usuario.setNombre(this.nombre);
		usuario.setApellidos(this.apellidos);
		usuario.setEmail(this.email);
		usuario.setUsuario(this.usuario);
		usuario.setPwd(this.pwd);
		usuario.setAdmin(this.admin);
	}
}
