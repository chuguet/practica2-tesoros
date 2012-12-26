package com.movember.treasure.model.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.ibatis.SqlMapClientFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * The Class SpringModelConfiguration.
 */
@Configuration
@ComponentScan({ "com.movember.treasure.model" })
@PropertySource({ "classpath:/application.properties" })
@EnableTransactionManagement
public class SpringModelConfiguration {

	// Bean para importar el properties
	/**
	 * Property sources placeholder configurer.
	 * 
	 * @return the property sources placeholder configurer
	 */
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	// JDBC CONFIGURATION DATASOURCE
	/** The driver class name. */
	@Value("${jdbc.driverClassName}")
	private String driverClassName;

	/** The url. */
	@Value("${jdbc.url}")
	private String url;

	/** The username. */
	@Value("${jdbc.username}")
	private String username;

	/** The password. */
	@Value("${jdbc.password}")
	private String password;

	/**
	 * Gets the data source.
	 * 
	 * @return the data source
	 */
	@Bean
	public BasicDataSource getDataSource() {
		BasicDataSource result = new BasicDataSource();
		result.setDriverClassName(driverClassName);
		result.setUrl(url);
		result.setUsername(username);
		result.setPassword(password);
		return result;
	}

	/**
	 * Sql map client.
	 * 
	 * @return the sql map client factory bean
	 */
	@Bean
	public SqlMapClientFactoryBean sqlMapClient() {
		SqlMapClientFactoryBean result = new SqlMapClientFactoryBean();
		result.setConfigLocation(new ClassPathResource("ibatis_conf/sql_map_config.xml"));
		result.setDataSource(this.getDataSource());
		return result;
	}

	/**
	 * Transaction manager.
	 * 
	 * @return the data source transaction manager
	 */
	@Bean
	public DataSourceTransactionManager transactionManager() {
		DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
		transactionManager.setDataSource(this.getDataSource());
		return transactionManager;
	}
}