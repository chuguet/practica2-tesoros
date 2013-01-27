package com.movember.treasure.model.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import com.movember.treasure.model.bean.Configuracion;
import com.movember.treasure.model.bean.ItemConfiguracion;
import com.movember.treasure.model.bean.Mensaje;
import com.movember.treasure.model.dao.IConfiguracionDAO;
import com.movember.treasure.model.dao.ILogroDAO;
import com.movember.treasure.model.exception.AppException;
import com.movember.treasure.model.service.IConfiguracionService;

@Service
class ConfiguracionService implements IConfiguracionService {

	@Inject
	private ILogroDAO logroDAO;

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
				logroDAO.insert(mensaje);
				count++;
			}

			for (ItemConfiguracion itemConfiguracion : configuracion.getItemsConfiguracion()) {
				configuracionDAO.insert(itemConfiguracion);
			}

		}
		catch (SQLException e) {
			throw new AppException("Se ha producido un error al cambiar la configuración");
		}
	}

	public void deleteAll() throws AppException {
		try {
			this.logroDAO.deleteAll();
			this.configuracionDAO.deleteAll();
		}
		catch (SQLException e) {
			throw new AppException("Se ha producido un error al eliminar la configuración");
		}
	}

	public Configuracion retrieve() throws AppException {
		try {
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
			configuracion.setItemsConfiguracion(this.configuracionDAO.selectAll());
			return configuracion;
		}
		catch (SQLException e) {
			throw new AppException("Se ha producido un error al recuperar la configuración del sistema");
		}
	}

	public List<Mensaje> recuperarMensajes() throws AppException {
		try {
			return this.logroDAO.selectAll();
		}
		catch (SQLException e) {
			throw new AppException("Se ha producido un error al recuperar los mensajes de la configuración");
		}
	}

	public ItemConfiguracion recuperarItemConfiguracion(String clave) throws AppException {
		try {
			return this.configuracionDAO.selectByClave(clave);
		}
		catch (SQLException e) {
			throw new AppException("Se ha producido un error al recuperar el item de configuración " + clave);
		}
	}
}