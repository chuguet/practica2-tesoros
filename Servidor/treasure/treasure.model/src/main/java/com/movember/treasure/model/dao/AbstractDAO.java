package com.movember.treasure.model.dao;

import javax.inject.Inject;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import com.ibatis.sqlmap.client.SqlMapClient;


/**
 * The Class AbstractDAO.
 */
public abstract class AbstractDAO {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The transaction manager. */
	@Inject
	private DataSourceTransactionManager transactionManager;

	/** The sql map client. */
	@Inject
	private SqlMapClient sqlMapClient;

	/**
	 * Sets the transaction manager.
	 * 
	 * @param transactionManager
	 *            the new transaction manager
	 */
	public void setTransactionManager(
			DataSourceTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

	/**
	 * Gets the transaction manager.
	 * 
	 * @return the transaction manager
	 */
	public DataSourceTransactionManager getTransactionManager() {
		return transactionManager;
	}

	/**
	 * Gets the sql map client.
	 * 
	 * @return the sql map client
	 */
	public SqlMapClient getSqlMapClient() {
		return this.sqlMapClient;
	}

	/**
	 * Sets the sql map client.
	 * 
	 * @param sqlMapClient
	 *            the new sql map client
	 */
	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}
}