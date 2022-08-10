package com.zee.zee5app.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;

@Configuration
// The Config file will hold the common useful information. So we need only one copy
// of all objects inside this file. Hence, use @Bean
// scanning right from the base package and finds out all annotations.
@ComponentScan("com.zee.zee5app")

// spring will read the content of below file
@PropertySource(value = "application.properties")
public class Config {

	// it will help to retrieve the .property file content
	@Autowired
	Environment environment;
	
//	@Bean(name = "properties")
//	@Scope("prototype")
//	public Properties getProperties()
//	{
//		Properties properties = new Properties();
//		properties.setProperty("username", environment.getProperty("db.username"));
//		properties.setProperty("password", environment.getProperty("db.password"));
//		properties.setProperty("url", environment.getProperty("db.url"));
//		
//		return properties;
//	}
	
	// datasource -----> get the connection object
	
	@Bean(name = "dataSource")
	public DataSource getDataSource()
	{
		BasicDataSource basicDataSource = new BasicDataSource();
		basicDataSource.setUsername(environment.getProperty("db.username"));
		basicDataSource.setUrl(environment.getProperty("db.url"));
		basicDataSource.setPassword(environment.getProperty("db.password"));
		
		return basicDataSource;
	}
}
