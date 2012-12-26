package com.movember.treasure.model.dao;

import java.sql.SQLException;
import java.util.List;
import org.springframework.stereotype.Repository;

import com.movember.treasure.model.bean.HitoUsuario;
import com.movember.treasure.model.bean.ParametrosRuta;
import com.movember.treasure.model.bean.Ruta;


/**
 * The Class EncuestaDAO.
 */
@Repository
class EncuestaDAO extends AbstractDAO implements IEncuestaDAO {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.movember.quizz.model.dao.IRepositoryDAO#insert(com.movember.quizz
	 * .model.bean.AbstractBean)
	 */
	public void insert(Ruta encuesta) throws SQLException {
		Integer id = (Integer) this.getSqlMapClient().insert(
				"encuesta.insertReturnId", encuesta);
		encuesta.setId(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.movember.quizz.model.dao.IRepositoryDAO#update(com.movember.quizz
	 * .model.bean.AbstractBean)
	 */
	public void update(Ruta encuesta) throws SQLException {
		this.getSqlMapClient().update("encuesta.updateByPrimaryKey", encuesta);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.movember.quizz.model.dao.IRepositoryDAO#delete(java.lang.Integer)
	 */
	public void delete(Integer id) throws SQLException {
		this.getSqlMapClient().delete("encuesta.deleteByPrimaryKey", id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.movember.quizz.model.dao.IRepositoryDAO#retrieve(java.lang.Integer)
	 */
	public Ruta retrieve(Integer id) throws SQLException {
		return (Ruta) this.getSqlMapClient().queryForObject(
				"encuesta.selectById", id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.movember.quizz.model.dao.IRepositoryDAO#selectAll()
	 */
	public List<Ruta> selectAll() throws SQLException {
		return (List<Ruta>) this.getSqlMapClient().queryForList(
				"encuesta.selectAll");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.movember.quizz.model.dao.IRepositoryDAO#selectByCriterios()
	 */
	public List<Ruta> selectByCriterios() throws SQLException {
		return (List<Ruta>) this.getSqlMapClient().queryForList(
				"encuesta.selectAll");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.movember.quizz.model.dao.IEncuestaDAO#find(com.movember.quizz.model
	 * .bean.ParametrosEncuesta)
	 */
	public List<Ruta> find(ParametrosRuta parametrosEncuesta)
			throws SQLException {
		return (List<Ruta>) this.getSqlMapClient().queryForList(
				"encuesta.find", parametrosEncuesta);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.movember.quizz.model.dao.IEncuestaDAO#contestar(com.movember.quizz
	 * .model.bean.EncuestaContestada)
	 */
	public void contestar(HitoUsuario encuestaContestada)
			throws SQLException {
		Integer id = (Integer) this.getSqlMapClient().insert(
				"encuesta.contestar", encuestaContestada);
		encuestaContestada.setId(id);
	}
}