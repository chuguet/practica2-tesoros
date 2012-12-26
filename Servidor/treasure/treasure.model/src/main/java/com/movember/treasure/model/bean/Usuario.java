package com.movember.treasure.model.bean;

import javax.persistence.Entity;

/**
 * The Class Usuario.
 */
@Entity
public class Usuario extends AbstractBean {

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

	/**
	 * Instantiates a new usuario.
	 */
	public Usuario() {
	}

	/**
	 * Instantiates a new usuario.
	 * 
	 * @param nombre
	 *            the nombre
	 * @param apellidos
	 *            the apellidos
	 * @param email
	 *            the email
	 * @param usuario
	 *            the usuario
	 * @param pwd
	 *            the pwd
	 * @param admin
	 *            the admin
	 */
	public Usuario(String nombre, String apellidos, String email,
			String usuario, String pwd, Integer admin) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.usuario = usuario;
		this.pwd = pwd;
		this.admin = admin;
	}
}