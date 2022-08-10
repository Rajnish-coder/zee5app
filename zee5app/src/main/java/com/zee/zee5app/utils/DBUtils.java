package com.zee.zee5app.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.springframework.stereotype.Component;

import com.zee.zee5app.exceptions.UnableToGenerateIdException;

@Component
public class DBUtils {

//	private DBUtils() {
//		// TODO Auto-generated constructor stub
//	}
//
//	private static DBUtils dbUtils;
//
//	public static DBUtils getInstance() {
//		if (dbUtils == null) {
//			dbUtils = new DBUtils();
//		}
//
//		return dbUtils;
//
//	}
	
	public Connection getConnection()
	{
		// to provide the connection
		Properties properties = loadProperties();
		
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(properties.getProperty("db.url"),
					properties.getProperty("db.username"),
					properties.getProperty("db.password"));
			return connection;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void closeConnection(Connection connection)
	{
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private Properties loadProperties()
	{
		Properties properties = new Properties();
		InputStream inputStream = null;
		
		try {
			inputStream = DBUtils.class
					.getClassLoader().getResourceAsStream("application.properties");
			System.out.println(inputStream!=null);
			properties.load(inputStream);
			return properties;
		} catch(IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				inputStream.close();
			}catch(IOException e)
			{
				e.printStackTrace();
			}
		}
		
		return properties;
	}
	
	public String userIdGenerator(String firstname,String lastname) throws UnableToGenerateIdException
	{
		// it is responsible to generate userid for user entity
		// first retrieve the value(db stored value from idgen table)
		Connection connection = null;
		connection = this.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int id = 0,temp;
		String query = "select id from useridgenerator";
		String updateIdStatement = "update useridgenerator set id=?";
		String newId = null;
		String zeros = "0000000000";
		int updateResult = 0;
		int length = 0,tempLength;
		try {
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next())
			{
				id = resultSet.getInt("id");
				// then increment the number(id which is retrieved from db)
				// then take first char from firstname and lastname
				++id;
				temp=id;
				tempLength = 0;
				while(temp>0)
				{
					temp/=10;
					tempLength++;
				}
				if(tempLength>length)
				{
					length = tempLength;
					zeros = zeros.substring(0,zeros.length()-String.valueOf(id).length());
				}
				newId = firstname.charAt(0)+ "" + lastname.charAt(0) +id;
				System.out.println(newId);
				preparedStatement = connection.prepareStatement(updateIdStatement);
				preparedStatement.setInt(1, id);
				updateResult = preparedStatement.executeUpdate();
				if(updateResult == 0)
				{
					throw new UnableToGenerateIdException("unable to generate id");
				}
				
				
				return newId;
			}
			
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new UnableToGenerateIdException("unable to generate id" + e.getMessage());
			
		}
		finally {
			this.closeConnection(connection);
		}
		
		return null;
		// then concat and return
		
	}
	
	public String movieIdGenerator(String movieName) throws UnableToGenerateIdException
	{
		// it is responsible to generate userid for user entity
		// first retrieve the value(db stored value from idgen table)
		Connection connection = null;
		connection = this.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int id = 0;
		String query = "select id from movieidgenerator";
		String updateIdStatement = "update movieidgenerator set id=?";
		String newId = null;
		int updateResult = 0;
		try {
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next())
			{
				id = resultSet.getInt("id");
				// then increment the number(id which is retrieved from db)
				// then take first char from firstname and lastname
				++id;
				newId = movieName.charAt(0)+ "" + movieName.charAt(1) + "" + id;
				System.out.println(newId);
				preparedStatement = connection.prepareStatement(updateIdStatement);
				preparedStatement.setInt(1, id);
				updateResult = preparedStatement.executeUpdate();
				if(updateResult == 0)
				{
					throw new UnableToGenerateIdException("unable to generate id");
				}
				
				
				return newId;
			}
			
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new UnableToGenerateIdException("unable to generate id" + e.getMessage());
			
		}
		finally {
			this.closeConnection(connection);
		}
		
		return null;
		// then concat and return
		
	}
	
	
	public String webSeriesIdGenerator(String webSeriesName) throws UnableToGenerateIdException
	{
		// it is responsible to generate userid for user entity
		// first retrieve the value(db stored value from idgen table)
		Connection connection = null;
		connection = this.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int id = 0;
		String query = "select id from webseriesidgenerator";
		String updateIdStatement = "update webseriesidgenerator set id=?";
		String newId = null;
		int updateResult = 0;
		try {
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next())
			{
				id = resultSet.getInt("id");
				// then increment the number(id which is retrieved from db)
				// then take first char from firstname and lastname
				++id;
				newId = webSeriesName.charAt(0)+ "" + webSeriesName.charAt(1) + "" + id;
				System.out.println(newId);
				preparedStatement = connection.prepareStatement(updateIdStatement);
				preparedStatement.setInt(1, id);
				updateResult = preparedStatement.executeUpdate();
				if(updateResult == 0)
				{
					throw new UnableToGenerateIdException("unable to generate id");
				}
				
				
				return newId;
			}
			
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new UnableToGenerateIdException("unable to generate id" + e.getMessage());
			
		}
		finally {
			this.closeConnection(connection);
		}
		
		return null;
		// then concat and return
		
	}
	
	public static void main(String[] args)
	{
//		String result = null;
//		try {
//			result = DBUtils.getInstance().userIdGenerator("rajnish","shonkhia");
//		} catch (UnableToGenerateIdException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println(result);
	}
	
	
}
