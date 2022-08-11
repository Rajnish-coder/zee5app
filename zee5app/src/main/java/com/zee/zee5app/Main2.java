package com.zee.zee5app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.zee.zee5app.config.Config;
import com.zee.zee5app.config.Config2;

public class Main2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext applicationContext = 
				new AnnotationConfigApplicationContext(Config.class);
		

	}

}
