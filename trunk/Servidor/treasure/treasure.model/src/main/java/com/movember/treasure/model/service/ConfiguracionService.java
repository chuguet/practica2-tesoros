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

/**
 * The Class EncuestaService.
 */
@Service
class ConfiguracionService {

	/** The configuracion dao. */
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
			}
		}
		catch (SQLException e) {
			throw new AppException("Se ha producido un error al insertar la configuracion");
		}
	}

	public void deleteAll() throws AppException {
		try {
			configuracionDAO.deleteAll();
		}
		catch (SQLException e) {
			throw new AppException("Se ha producido un error al eliminar la configuracion");
		}
	}

	public Configuracion retrieve() throws AppException {
		try {
			List<Mensaje> mensajes = this.configuracionDAO.selectAll();
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
		catch (SQLException e) {
			throw new AppException("Se ha producido un error al recuperar la configuracion");
		}
	}
}