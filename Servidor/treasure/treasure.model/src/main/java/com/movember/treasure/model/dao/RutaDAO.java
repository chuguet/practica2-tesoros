package com.movember.treasure.model.dao;

import java.sql.SQLException;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.movember.treasure.model.bean.HitoUsuario;
import com.movember.treasure.model.bean.ParametrosRuta;
import com.movember.treasure.model.bean.Ruta;

/**
 * The Class RutaDAO.
 */
@Repository
class RutaDAO extends AbstractDAO implements IRutaDAO {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/*
	 * (non-Javadoc)
	 * @see
	 * com.movember.quizz.model.dao.IRepositoryDAO#insert(com.movember.quizz
	 * .model.bean.AbstractBean)
	 */
	public void insert(Ruta ruta) throws SQLException {
		Integer id = (Integer) this.getSqlMapClient().insert("ruta.insertReturnId", ruta);
		ruta.setId(id);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.movember.quizz.model.dao.IRepositoryDAO#update(com.movember.quizz
	 * .model.bean.AbstractBean)
	 */
	public void update(Ruta ruta) throws SQLException {
		this.getSqlMapClient().update("ruta.updateByPrimaryKey", ruta);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.movember.quizz.model.dao.IRepositoryDAO#delete(java.lang.Integer)
	 */
	public void delete(Integer id) throws SQLException {
		this.getSqlMapClient().delete("ruta.deleteByPrimaryKey", id);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.movember.quizz.model.dao.IRepositoryDAO#retrieve(java.lang.Integer)
	 */
	public Ruta retrieve(Integer id) throws SQLException {
		return (Ruta) this.getSqlMapClient().queryForObject("ruta.selectById", id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.movember.quizz.model.dao.IRepositoryDAO#selectAll()
	 */
	public List<Ruta> selectAll() throws SQLException {
		return (List<Ruta>) this.getSqlMapClient().queryForList("ruta.selectAll");
	}

	/*
	 * (non-Javadoc)
	 * @see com.movember.quizz.model.dao.IRepositoryDAO#selectByCriterios()
	 */
	public List<Ruta> selectByCriterios() throws SQLException {
		return (List<Ruta>) this.getSqlMapClient().queryForList("ruta.selectAll");
	}

	/*
	 * (non-Javadoc)
	 * @see com.movember.quizz.model.dao.IRutaDAO#find(com.movember.quizz.model
	 * .bean.ParametrosRuta)
	 */
	public List<Ruta> find(ParametrosRuta parametrosRuta) throws SQLException {
		return (List<Ruta>) this.getSqlMapClient().queryForList("ruta.find", parametrosRuta);
	}

	/*
	 * (non-Javadoc)
	 * @see com.movember.quizz.model.dao.IRutaDAO#contestar(com.movember.quizz
	 * .model.bean.RutaContestada)
	 */
	public void contestar(HitoUsuario rutaContestada) throws SQLException {
		Integer id = (Integer) this.getSqlMapClient().insert("ruta.contestar", rutaContestada);
		rutaContestada.setId(id);
	}
}