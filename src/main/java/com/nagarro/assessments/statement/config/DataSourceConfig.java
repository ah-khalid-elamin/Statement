package com.nagarro.assessments.statement.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class DataSourceConfig {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	Environment env;

	@Bean
	public DataSource createDataSource() throws PropertyVetoException {
		
		logger.info("Connectin to Database");
		ComboPooledDataSource dataSource = new ComboPooledDataSource();

		dataSource.setJdbcUrl(env.getProperty("jdbc.url"));
		dataSource.setDriverClass(env.getProperty("db.driver"));

		return dataSource;
	}

}
