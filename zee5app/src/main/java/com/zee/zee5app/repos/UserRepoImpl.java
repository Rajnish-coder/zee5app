package com.zee.zee5app.repos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.zee.zee5app.dto.User;
import com.zee.zee5app.exceptions.NoDataFoundException;
import com.zee.zee5app.exceptions.UnableToGenerateIdException;
import com.zee.zee5app.utils.DBUtils;

public class UserRepoImpl implements UserRepo {

	private DBUtils dbUtils = DBUtils.getInstance();
	
	private UserRepoImpl()
	{
		
	}
	private static UserRepoImpl userRepoImpl;
	
	public static UserRepoImpl getInstance()
	{
		if(userRepoImpl == null)
			userRepoImpl = new UserRepoImpl();
		return userRepoImpl;
	}
	@Override
	public User insertUser(User user) throws UnableToGenerateIdException {
		// connection object
		Connection connection = null;
		connection = dbUtils.getConnection();
		String insertStatement = "insert into user_table "
				+ "(userid,firstname,lastname,email,doj,dob,active)"
				+ " values(?,?,?,?,?,?,?)";
		// statement object(prepared)
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(insertStatement);
			preparedStatement.setString(1, dbUtils.userIdGenerator(user.getFirstName(), user.getLastName()));
			preparedStatement.setString(2, user.getFirstName());
			preparedStatement.setString(3, user.getLastName());
			preparedStatement.setString(4, user.getEmail());
			preparedStatement.setDate(5, Date.valueOf(user.getDoj()));
			preparedStatement.setDate(6, Date.valueOf(user.getDob()));
			preparedStatement.setBoolean(7, user.isActive());
			
			int result = preparedStatement.executeUpdate();
			if(result>0)
				return user;
			else
				return null;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			dbUtils.closeConnection(connection);
		}
		// we should get the result and based on that we will return the result
		return null;
	}

	@Override
	public User updateUserById(String userId, User user) {
		// TODO Auto-generated method stub
		Connection connection = null;
		connection = dbUtils.getConnection();
		PreparedStatement preparedStatement = null;
		String query = "update user_table set userid=?,firstname=?,lastname=?,email=?,"
				+ "doj=?,dob=?,active=? where userid=?";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, user.getUserId());
			preparedStatement.setString(2, user.getFirstName());
			preparedStatement.setString(3, user.getLastName());
			preparedStatement.setString(4, user.getEmail());
			preparedStatement.setDate(5, Date.valueOf(user.getDoj()));
			preparedStatement.setDate(6, Date.valueOf(user.getDob()));
			preparedStatement.setBoolean(7, user.isActive());
			preparedStatement.setString(8, userId);
			int result = preparedStatement.executeUpdate();
			if(result>0)
				return user;
			else
				return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public String deleteUserById(String userId) throws NoDataFoundException {
		// TODO Auto-generated method stub
		Connection connection = null;
		connection = dbUtils.getConnection();
		PreparedStatement preparedStatement = null;
		String deleteQuery = "delete from user_table where userid=?";
		
		try {
			preparedStatement = connection.prepareStatement(deleteQuery);
			preparedStatement.setString(1, userId);
			int result = preparedStatement.executeUpdate();
			
			if(result>0) {
				return "success";
			}
			else {
				throw new NoDataFoundException("userid not present");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			dbUtils.closeConnection(connection);
		}
		return null;
		
	}

	@Override
	public Optional<List<User>> getAllUsers() {
		// TODO Auto-generated method stub
		Connection connection = null;
		connection = dbUtils.getConnection();
		PreparedStatement preparedStatement = null;
		String query = "select * from user_table";
		ResultSet resultSet = null;
		List<User> users = new ArrayList<>();
		
		try {
			preparedStatement = connection.prepareStatement(query);
	
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next())
			{
				// record exists inside RESULTSET object;
				// retrieve User object from RESULTSET;
				User user = new User();
				user.setUserId(resultSet.getString("userid"));
				user.setFirstName(resultSet.getString("firstname"));
				user.setLastName(resultSet.getString("lastname"));
				user.setEmail(resultSet.getString("email"));
				user.setDoj(resultSet.getDate("doj").toLocalDate());
				user.setDob(resultSet.getDate("dob").toLocalDate());
				user.setActive(resultSet.getBoolean("active"));
				users.add(user);
			}
			return Optional.of(users);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			dbUtils.closeConnection(connection);
		}
		
		return Optional.empty();
	}

	@Override
	public Optional<User> getUserById(String userId) {
		// TODO Auto-generated method stub
		Connection connection = null;
		connection = dbUtils.getConnection();
		PreparedStatement preparedStatement = null;
		String query = "select * from user_table where userid=?";
		ResultSet resultSet = null;
		
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, userId);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next())
			{
				// record exists inside RESULTSET object;
				// retrieve User object from RESULTSET;
				User user = new User();
				user.setUserId(userId);
				user.setFirstName(resultSet.getString("firstname"));
				user.setLastName(resultSet.getString("lastname"));
				user.setEmail(resultSet.getString("email"));
				user.setDoj(resultSet.getDate("doj").toLocalDate());
				user.setDob(resultSet.getDate("dob").toLocalDate());
				user.setActive(resultSet.getBoolean("active"));
				return Optional.of(user);
			}
			else {
				return Optional.empty();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			dbUtils.closeConnection(connection);
		}
		
		return Optional.empty();
	}

}
