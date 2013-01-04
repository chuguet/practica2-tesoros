package com.movember.treasure.model.dao;

import java.util.List;

import com.movember.treasure.model.bean.GestorRuta;

public interface IGestorRutaDAO {

	void insert(GestorRuta gestorRuta);

	void deleteAllByIdGestor(Integer pIdGestor);

	List<Integer> retrieveByIdGestor(Integer pIdGestor);
}
