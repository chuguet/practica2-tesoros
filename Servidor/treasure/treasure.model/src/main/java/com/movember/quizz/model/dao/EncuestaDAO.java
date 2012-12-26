package com.movember.quizz.model.dao;

import java.sql.SQLException;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.movember.quizz.model.bean.Encuesta;
import com.movember.quizz.model.bean.EncuestaContestada;
import com.movember.quizz.model.bean.ParametrosEncuesta;


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
	public void insert(Encuesta encuesta) throws SQLException {
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
	public void update(Encuesta encuesta) throws SQLException {
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
	public Encuesta retrieve(Integer id) throws SQLException {
		return (Encuesta) this.getSqlMapClient().queryForObject(
				"encuesta.selectById", id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.movember.quizz.model.dao.IRepositoryDAO#selectAll()
	 */
	public List<Encuesta> selectAll() throws SQLException {
		return (List<Encuesta>) this.getSqlMapClient().queryForList(
				"encuesta.selectAll");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.movember.quizz.model.dao.IRepositoryDAO#selectByCriterios()
	 */
	public List<Encuesta> selectByCriterios() throws SQLException {
		return (List<Encuesta>) this.getSqlMapClient().queryForList(
				"encuesta.selectAll");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.movember.quizz.model.dao.IEncuestaDAO#find(com.movember.quizz.model
	 * .bean.ParametrosEncuesta)
	 */
	public List<Encuesta> find(ParametrosEncuesta parametrosEncuesta)
			throws SQLException {
		return (List<Encuesta>) this.getSqlMapClient().queryForList(
				"encuesta.find", parametrosEncuesta);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.movember.quizz.model.dao.IEncuestaDAO#contestar(com.movember.quizz
	 * .model.bean.EncuestaContestada)
	 */
	public void contestar(EncuestaContestada encuestaContestada)
			throws SQLException {
		Integer id = (Integer) this.getSqlMapClient().insert(
				"encuesta.contestar", encuestaContestada);
		encuestaContestada.setId(id);
	}
}