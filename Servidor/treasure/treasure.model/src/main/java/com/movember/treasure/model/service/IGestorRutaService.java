package com.movember.treasure.model.service;

import java.util.List;

import com.movember.treasure.model.bean.GestorRuta;

public interface IGestorRutaService {

	void insert(GestorRuta gestorRuta);

	void deleteAllByIdGestor(Integer pIdGestor);

	void deleteAllByIdRuta(Integer pIdRuta);

	List<Integer> retrieveByIdGestor(Integer pIdGestor);
}
