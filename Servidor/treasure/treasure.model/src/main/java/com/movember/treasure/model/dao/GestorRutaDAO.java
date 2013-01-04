package com.movember.treasure.model.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.movember.treasure.model.bean.GestorRuta;

@Repository
public class GestorRutaDAO extends AbstractDAO implements IGestorRutaDAO{

	public void insert(GestorRuta gestorRuta) {
		try {
			this.getSqlMapClient().insert("gestor_ruta.insert", gestorRuta);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteAllByIdGestor(Integer pIdGestor) {
		try {
			this.getSqlMapClient().delete("gestor_ruta.deleteAllByIdGestor", pIdGestor);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Integer> retrieveByIdGestor(Integer pIdGestor) {
		List<Integer> result = new ArrayList<Integer>();
		try {
			result = (List<Integer>) this.getSqlMapClient().queryForList("gestor_ruta.retrieveByIdGestor", pIdGestor);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
