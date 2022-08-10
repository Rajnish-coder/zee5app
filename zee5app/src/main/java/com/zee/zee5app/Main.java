package com.zee.zee5app;

import java.time.LocalDate;
import java.util.Properties;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.zee.zee5app.config.Config;
import com.zee.zee5app.dto.User;
import com.zee.zee5app.exceptions.UnableToGenerateIdException;
import com.zee.zee5app.repos.UserRepo;
import com.zee.zee5app.service.UserService;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// for creating the pre-requisite objects. It will start reading from the
		// Config class.
		
		ApplicationContext applicationContext = 
				new AnnotationConfigApplicationContext(Config.class);
		
		UserService userService = applicationContext.getBean(UserService.class);
		
//		Properties properties = applicationContext.getBean("properties",Properties.class);
		
	
		
		
		
		try {
			User user = userService.insertUser(
					new User("raj","sho","rajnish1@gmail.com",LocalDate.now(),LocalDate.now(),true));
			System.out.println(user);
		} catch (UnableToGenerateIdException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(userService!=null);
		
		UserRepo userRepo = applicationContext.getBean(UserRepo.class);
		System.out.println(userRepo!=null);

	}

}
