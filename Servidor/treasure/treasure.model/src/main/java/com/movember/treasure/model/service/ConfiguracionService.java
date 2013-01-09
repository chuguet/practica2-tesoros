package com.movember.treasure.model.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import com.movember.treasure.model.bean.Configuracion;
import com.movember.treasure.model.bean.Mensaje;
import com.movember.treasure.model.dao.IConfiguracionDAO;
import com.movember.treasure.model.exception.AppException;

@Service
class ConfiguracionService implements IConfiguracionService {

	@Inject
	private IConfiguracionDAO configuracionDAO;

	public void insert(Configuracion configuracion) throws AppException {
		try {
			this.deleteAll();
			Integer numero = configuracion.getId();
			Integer count = 1;
			for (String message : configuracion.getMensajes()) {
				Mensaje mensaje = new Mensaje();
				mensaje.setId(numero * count);
				mensaje.setMensaje(message);
				configuracionDAO.insert(mensaje);
				count++;
			}
		}
		catch (SQLException e) {
			throw new AppException("Se ha producido un error al cambiar la configuración");
		}
	}

	public void deleteAll() throws AppException {
		try {
			configuracionDAO.deleteAll();
		}
		catch (SQLException e) {
			throw new AppException("Se ha producido un error al eliminar la configuración");
		}
	}

	public Configuracion retrieve() throws AppException {
		List<Mensaje> mensajes = this.recuperarMensajes();
		List<String> messages = new ArrayList<String>();
		for (Mensaje mensaje : mensajes) {
			messages.add(mensaje.getMensaje());
		}
		Configuracion configuracion = new Configuracion();
		if (mensajes.size() > 0) {
			configuracion.setId(mensajes.get(0).getId());
			configuracion.setMensajes(messages);
		}
		return configuracion;

	}

	public List<Mensaje> recuperarMensajes() throws AppException {
		try {
			return this.configuracionDAO.selectAll();
		}
		catch (SQLException e) {
			throw new AppException("Se ha producido un error al recuperar los mensajes de la configuración");
		}
	}
}