package com.movember.treasure.model.dao;

import java.sql.SQLException;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.movember.treasure.model.bean.Hito;

/**
 * The Class HitoDAO.
 */
@Repository
class HitoDAO extends AbstractDAO implements IHitoDAO {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/*
	 * (non-Javadoc)
	 * @see
	 * com.movember.quizz.model.dao.IRepositoryDAO#insert(com.movember.quizz
	 * .model.bean.AbstractBean)
	 */
	public Integer insert(Hito hito) throws SQLException {
		return (Integer) this.getSqlMapClient().insert("hito.insertReturnId", hito);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.movember.quizz.model.dao.IRepositoryDAO#update(com.movember.quizz
	 * .model.bean.AbstractBean)
	 */
	public void update(Hito hito) throws SQLException {
		this.getSqlMapClient().update("hito.updateByPrimaryKey", hito);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.movember.quizz.model.dao.IRepositoryDAO#delete(java.lang.Integer)
	 */
	public void delete(Integer id) throws SQLException {
		this.getSqlMapClient().delete("hito.deleteByPrimaryKey", id);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.movember.quizz.model.dao.IRepositoryDAO#retrieve(java.lang.Integer)
	 */
	public Hito retrieve(Integer id) throws SQLException {
		return (Hito) this.getSqlMapClient().queryForObject("hito.selectById", id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.movember.quizz.model.dao.IRepositoryDAO#selectAll()
	 */
	public List<Hito> selectAll() throws SQLException {
		return (List<Hito>) this.getSqlMapClient().queryForList("hito.selectAll");
	}

	/*
	 * (non-Javadoc)
	 * @see com.movember.quizz.model.dao.IRepositoryDAO#selectByCriterios()
	 */
	public List<Hito> selectByCriterios() throws SQLException {
		return (List<Hito>) this.getSqlMapClient().queryForList("hito.selectAll");
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.movember.quizz.model.dao.IHitoDAO#recuperarDeRuta(java.lang.Integer )
	 */
	public List<Hito> recuperarDeRuta(Integer idRuta) throws SQLException {
		return (List<Hito>) this.getSqlMapClient().queryForList("hito.recuperarDeRuta", idRuta);
	}

	public Hito selectByCodigo(String codigo) throws SQLException {
		return (Hito) this.getSqlMapClient().queryForObject("hito.selectByCodigo", codigo);
	}
}