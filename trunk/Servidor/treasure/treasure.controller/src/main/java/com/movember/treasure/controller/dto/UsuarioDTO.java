package com.movember.treasure.controller.dto;

import java.util.List;

import com.movember.treasure.model.bean.Usuario;
import com.movember.treasure.model.exception.AppException;

// TODO: Auto-generated Javadoc
/**
 * The Class UsuarioDTO.
 */
public class UsuarioDTO extends AbstractDTO {

	private String nombre;
	private String apellidos;
	private String email;
	private String usuario;
	private String pwd;
	private Integer admin;
	private String uuid;
	private List<Integer> id_rutas;

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

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getUuid() {
		return uuid;
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
		this.id_rutas = usuario.getRutas_asignadas();
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
		if (this.admin == null) {
			this.admin = 0;
		}
		usuario.setAdmin(this.admin);
		usuario.setRutas_asignadas(this.id_rutas);
	}

	public List<Integer> getId_rutas() {
		return id_rutas;
	}

	public void setId_rutas(List<Integer> id_rutas) {
		this.id_rutas = id_rutas;
	}
}
