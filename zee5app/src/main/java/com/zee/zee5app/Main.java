package com.zee.zee5app;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.zee.zee5app.config.Config;
import com.zee.zee5app.config.Config2;
import com.zee.zee5app.dto.Movie;
import com.zee.zee5app.dto.User;
import com.zee.zee5app.dto.WebSeries;
import com.zee.zee5app.enums.Genres;
import com.zee.zee5app.exceptions.NoDataFoundException;
import com.zee.zee5app.exceptions.UnableToGenerateIdException;
import com.zee.zee5app.repos.UserRepo;
import com.zee.zee5app.service.MovieService;
import com.zee.zee5app.service.UserService;
import com.zee.zee5app.service.WebSeriesService;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// for creating the pre-requisite objects. It will start reading from the
		// Config class.
		
		ApplicationContext applicationContext = 
				new AnnotationConfigApplicationContext(Config.class);
		
		DataSource dataSource = applicationContext.getBean("dataSource2",DataSource.class);
		System.out.println(dataSource!=null);
	
		
		UserService userService = applicationContext.getBean(UserService.class);
		
		MovieService movieService = applicationContext.getBean(MovieService.class);
		
//		Properties properties = applicationContext.getBean("properties",Properties.class);
		
	
		WebSeriesService webSeriesService = applicationContext.getBean(WebSeriesService.class);

//--------------------------- test for insert webseries ---------------------------------		
		
//		String[] actors = {"a","b","c"};
//		
//		Genres g = Genres.DRAMA;
//		String[] languages = {"a","b","c"};
//		
//		WebSeries w = webSeriesService.insertWebSeries(
//				new WebSeries(actors,"under the shadows","a",g,"a",languages,2.02f,"abc"));
		
// --------------------------- updatewebseriesbyid() -----------------------------------
		
//		Optional<WebSeries> ow = webSeriesService.updateWebSeries(
//				"st1",new WebSeries(actors,"stranger things 2","a",g,"a",languages,2.02f,"abc"));
//		System.out.println(ow);
		
// ----------------------------- test for deleteWebSeriesById()--------------------------	
//		String result = null;
//		try {
//			result = webSeriesService.deleteWebSeriesById("st1");
//		} catch (NoDataFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println(result);
		
// ----------------------------- test for getallwebseries()------------------------------
		
//		Optional<List<WebSeries>> res = webSeriesService.getAllWebSeries();
//		for (WebSeries w : res.get()) {
//			System.out.println(w);
//		}
		
// ----------------------------- getallwebseriesbygenre()--------------------------------
		
//		String gen = "COMED";
//		Optional<WebSeries[]> op = webSeriesService.getAllWebSeriesByGenre(gen);
//		for (WebSeries w : op.get()) {
//			System.out.println(w);
//		}
		
// ----------------------------- getallwebseriesbyname() -------------------------------
		
//		Optional<WebSeries[]> op = webSeriesService.getAllWebSeriesByName("stranger "
//				+ "things");
//		for (WebSeries w : op.get()) {
//			System.out.println(w);
//		}
		
// ----------------------------- getwebseriesbyid() ------------------------------------
		
//		Optional<WebSeries> op = webSeriesService.getWebSeriesById("st3");
//		System.out.println(op.get());
		
// ----------------------------- getbyorderbydesc() ------------------------------------
		
//		List<WebSeries> l = webSeriesService.findByOrderByWebSeriesNameDsc();
//		
//		for (WebSeries webSeries : l) {
//			System.out.println(webSeries);
//		}
		
// -------------------------------------------------------------------------------------
		
//		try {
//			User user = userService.insertUser(
//					new User("raj","sho","rajnish1@gmail.com",LocalDate.now(),LocalDate.now(),true));
//			System.out.println(user);
//		} catch (UnableToGenerateIdException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		System.out.println(userService!=null);
//		
//		UserRepo userRepo = applicationContext.getBean(UserRepo.class);
//		System.out.println(userRepo!=null);

	}

}
