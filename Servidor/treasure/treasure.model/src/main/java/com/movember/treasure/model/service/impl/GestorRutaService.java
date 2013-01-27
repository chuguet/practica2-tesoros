package com.movember.treasure.model.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.movember.treasure.model.bean.GestorRuta;
import com.movember.treasure.model.dao.IGestorRutaDAO;
import com.movember.treasure.model.service.IGestorRutaService;

@Service
public class GestorRutaService implements IGestorRutaService{

	@Inject
	private IGestorRutaDAO gestorRutaDAO;
	
	public void insert(GestorRuta gestorRuta) {
		gestorRutaDAO.insert(gestorRuta);
	}

	public void deleteAllByIdGestor(Integer pIdGestor) {
		gestorRutaDAO.deleteAllByIdGestor(pIdGestor);
	}

	public List<Integer> retrieveByIdGestor(Integer pIdGestor) {
		return gestorRutaDAO.retrieveByIdGestor(pIdGestor);
	}

	public void deleteAllByIdRuta(Integer pIdRuta) {
		gestorRutaDAO.deleteAllByIdRuta(pIdRuta);
	}

}
