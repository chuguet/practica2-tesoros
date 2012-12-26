package com.movember.treasure.model.dao;

import java.sql.SQLException;
import java.util.List;
import org.springframework.stereotype.Repository;

import com.movember.treasure.model.bean.Pregunta;


/**
 * The Class PreguntaDAO.
 */
@Repository
class PreguntaDAO extends AbstractDAO implements IPreguntaDAO {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see com.movember.quizz.model.dao.IRepositoryDAO#insert(com.movember.quizz.model.bean.AbstractBean)
	 */
	public void insert(Pregunta pregunta) throws SQLException {
		Integer id = (Integer) this.getSqlMapClient().insert("pregunta.insertReturnId", pregunta);
		pregunta.setId(id);
	}

	/* (non-Javadoc)
	 * @see com.movember.quizz.model.dao.IRepositoryDAO#update(com.movember.quizz.model.bean.AbstractBean)
	 */
	public void update(Pregunta pregunta) throws SQLException {
		this.getSqlMapClient().update("pregunta.updateByPrimaryKey", pregunta);
	}

	/* (non-Javadoc)
	 * @see com.movember.quizz.model.dao.IRepositoryDAO#delete(java.lang.Integer)
	 */
	public void delete(Integer id) throws SQLException {
		this.getSqlMapClient().delete("pregunta.deleteByPrimaryKey", id);
	}

	/* (non-Javadoc)
	 * @see com.movember.quizz.model.dao.IRepositoryDAO#retrieve(java.lang.Integer)
	 */
	public Pregunta retrieve(Integer id) throws SQLException {
		return (Pregunta) this.getSqlMapClient().queryForObject("pregunta.selectById", id);
	}

	/* (non-Javadoc)
	 * @see com.movember.quizz.model.dao.IRepositoryDAO#selectAll()
	 */
	public List<Pregunta> selectAll() throws SQLException {
		return (List<Pregunta>) this.getSqlMapClient().queryForList("pregunta.selectAll");
	}

	/* (non-Javadoc)
	 * @see com.movember.quizz.model.dao.IRepositoryDAO#selectByCriterios()
	 */
	public List<Pregunta> selectByCriterios() throws SQLException {
		return (List<Pregunta>) this.getSqlMapClient().queryForList("pregunta.selectAll");
	}

	/* (non-Javadoc)
	 * @see com.movember.quizz.model.dao.IPreguntaDAO#recuperarDeEncuesta(java.lang.Integer)
	 */
	public List<Pregunta> recuperarDeEncuesta(Integer idEncuesta) throws SQLException {
		return (List<Pregunta>) this.getSqlMapClient().queryForList("pregunta.recuperarDeEncuesta", idEncuesta);
	}
}