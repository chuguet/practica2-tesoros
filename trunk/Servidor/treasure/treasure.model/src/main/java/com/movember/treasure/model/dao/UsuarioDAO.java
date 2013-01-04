package com.movember.treasure.model.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;
import com.movember.treasure.model.bean.Usuario;

/**
 * The Class UsuarioDAO.
 */
@Repository
class UsuarioDAO extends AbstractDAO implements IUsuarioDAO {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/*
	 * (non-Javadoc)
	 * @see
	 * com.movember.quizz.model.dao.IRepositoryDAO#insert(com.movember.quizz
	 * .model.bean.AbstractBean)
	 */
	public Integer insert(Usuario usuario) throws SQLException {
		return Integer id = (Integer) this.getSqlMapClient().insert("usuario.insertReturnId", usuario);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.movember.quizz.model.dao.IRepositoryDAO#update(com.movember.quizz
	 * .model.bean.AbstractBean)
	 */
	public void update(Usuario usuario) throws SQLException {
		this.getSqlMapClient().update("usuario.updateByPrimaryKey", usuario);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.movember.quizz.model.dao.IRepositoryDAO#delete(java.lang.Integer)
	 */
	public void delete(Integer id) throws SQLException {
		this.getSqlMapClient().delete("usuario.deleteByPrimaryKey", id);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.movember.quizz.model.dao.IRepositoryDAO#retrieve(java.lang.Integer)
	 */
	public Usuario retrieve(Integer id) throws SQLException {
		return (Usuario) this.getSqlMapClient().queryForObject("usuario.selectById", id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.movember.quizz.model.dao.IRepositoryDAO#selectAll()
	 */
	public List<Usuario> selectAll() throws SQLException {
		return (List<Usuario>) this.getSqlMapClient().queryForList("usuario.selectAll");
	}

	/*
	 * (non-Javadoc)
	 * @see com.movember.quizz.model.dao.IRepositoryDAO#selectByCriterios()
	 */
	public List<Usuario> selectByCriterios() throws SQLException {
		return (List<Usuario>) this.getSqlMapClient().queryForList("usuario.selectAll");
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.movember.quizz.model.dao.IUsuarioDAO#selectByUser(java.lang.String)
	 */
	public Usuario selectByUser(String usuarioNombre) throws SQLException {
		return (Usuario) this.getSqlMapClient().queryForObject("usuario.selectByUser", usuarioNombre);
	}

	public Usuario selectByIdDispositivo(Integer idDispositivo) throws SQLException {
		return (Usuario) this.getSqlMapClient().queryForObject("usuario.selectByIdDispositivo", idDispositivo);
	}
}