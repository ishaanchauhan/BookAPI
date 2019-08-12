package com.bcs.configuration;

import java.util.Properties;
import javax.sql.DataSource;

import org.hibernate.ejb.HibernatePersistence;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SuppressWarnings("deprecation")
@Configuration
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
public class DataConfiguration {

	@Value("${spring.datasource.url}")
	private String DbURL;
	@Value("${spring.datasource.username}")
	private String DbUserName;
	@Value("${spring.datasource.password}")
	private String DbPassword;
	@Value("${spring.datasource.driver-class-name}")
	private String DbDriver;
	
	@Value("${spring.jpa.properties.hibernate.dialect}")
	private String DIALECT;
	@Value("${spring.jpa.show-sql}")
	private String SHOW_SQL;
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String HBM2DDL_AUTO;
	
	@Bean
	public DataSource dataSource()
	{
		DriverManagerDataSource objDB = new DriverManagerDataSource();
		objDB.setUrl(DbURL);
		objDB.setDriverClassName(DbDriver);
		objDB.setUsername(DbUserName);
		objDB.setPassword(DbPassword);
		return objDB;
	}
	
	@Bean
	LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean lfb = new LocalContainerEntityManagerFactoryBean();
		lfb.setDataSource(dataSource());
		lfb.setPersistenceProviderClass(HibernatePersistence.class);
		lfb.setPackagesToScan("com.bcs.model");
		lfb.setJpaProperties(hibernateProps());
		return lfb;
	}
	
	Properties hibernateProps() {
		Properties properties = new Properties();
		properties.setProperty("spring.jpa.properties.hibernate.dialec", DIALECT);
		properties.setProperty("spring.jpa.show-sql", SHOW_SQL);
		properties.setProperty("spring.jpa.hibernate.ddl-auto", HBM2DDL_AUTO);
		return properties;
	}
	
	@Bean
	public JpaTransactionManager transactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
		return transactionManager;
	}
}
