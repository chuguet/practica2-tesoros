package com.movember.treasure.model.bean;

import java.util.ArrayList;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class EncuestaContestada.
 */
public class HitoUsuario extends AbstractBean {

	/** The id_encuesta. */
	private Integer id_hito;

	/** The id_usuario. */
	private Integer id_usuario;

	/** The ip_usuario. */
	private String ip_usuario;

	/** The id respuestas contestadas. */
	private List<Integer> idHitosCheckIn;

	/**
	 * Instantiates a new hito usuario.
	 */
	public HitoUsuario() {
		idHitosCheckIn = new ArrayList<Integer>();
	}

	/**
	 * Gets the id_hito.
	 *
	 * @return the id_hito
	 */
	public Integer getId_hito() {
		return id_hito;
	}

	/**
	 * Sets the id_hito.
	 *
	 * @param id_hito the new id_hito
	 */
	public void setId_hito(Integer id_hito) {
		this.id_hito = id_hito;
	}

	/**
	 * Gets the id_usuario.
	 *
	 * @return the id_usuario
	 */
	public Integer getId_usuario() {
		return id_usuario;
	}

	/**
	 * Sets the id_usuario.
	 *
	 * @param id_usuario the new id_usuario
	 */
	public void setId_usuario(Integer id_usuario) {
		this.id_usuario = id_usuario;
	}

	/**
	 * Gets the ip_usuario.
	 *
	 * @return the ip_usuario
	 */
	public String getIp_usuario() {
		return ip_usuario;
	}

	/**
	 * Sets the ip_usuario.
	 *
	 * @param ip_usuario the new ip_usuario
	 */
	public void setIp_usuario(String ip_usuario) {
		this.ip_usuario = ip_usuario;
	}

	/**
	 * Gets the id hitos check in.
	 *
	 * @return the id hitos check in
	 */
	public List<Integer> getIdHitosCheckIn() {
		return idHitosCheckIn;
	}

	/**
	 * Sets the id hitos check in.
	 *
	 * @param idHitosCheckIn the new id hitos check in
	 */
	public void setIdHitosCheckIn(List<Integer> idHitosCheckIn) {
		this.idHitosCheckIn = idHitosCheckIn;
	}
}