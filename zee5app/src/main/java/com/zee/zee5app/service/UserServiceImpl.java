package com.zee.zee5app.service;

import java.util.List;
import java.util.Optional;

import com.zee.zee5app.dto.User;
import com.zee.zee5app.exceptions.NoDataFoundException;
import com.zee.zee5app.exceptions.UnableToGenerateIdException;
import com.zee.zee5app.repos.UserRepo;
import com.zee.zee5app.repos.UserRepoImpl;
import com.zee.zee5app.repos.UserRepoImpl;

public class UserServiceImpl implements UserService {
	
//	private UserServiceImpl()
//	{
//		
//	}
//	
//	private static UserServiceImpl userService;
//	
//	public static UserServiceImpl getInstance()
//	{
//		if(userService == null)
//		{
//			userService = new UserServiceImpl();
//		}
//		
//		return userService;
//	}
	
	private UserRepo repo;
	
	public User insertUser(User user) throws UnableToGenerateIdException
	{
		return repo.insertUser(user);
	}
	
	public Optional<List<User>> getAllUsers()
	{
		return repo.getAllUsers();
	}
	
	public Optional<User> getUserById(String userId)
	{
		return repo.getUserById(userId);
	}
	
	public String deleteUserById(String userId) throws NoDataFoundException
	{
		return repo.deleteUserById(userId);
	}
	
	public User updateUserById(String userId,User user)
	{
		return repo.updateUserById(userId, user);
	}

}
