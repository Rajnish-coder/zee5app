package com.zee.zee5app;


import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.zee.zee5app.dto.Movie;
import com.zee.zee5app.dto.User;
import com.zee.zee5app.enums.Genres;
import com.zee.zee5app.enums.Languages;
import com.zee.zee5app.exceptions.InvalidIdException;
import com.zee.zee5app.exceptions.InvalidLengthException;
import com.zee.zee5app.exceptions.InvalidNameException;
import com.zee.zee5app.exceptions.NoDataFoundException;
import com.zee.zee5app.exceptions.UnableToGenerateIdException;
import com.zee.zee5app.service.MovieService;
import com.zee.zee5app.service.MovieServiceImpl;
import com.zee.zee5app.service.UserService;
import com.zee.zee5app.service.UserServiceImpl;
import com.zee.zee5app.utils.IdComparator;

public class Exception {

	    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		int a=0;
//		try {
//			a = 10/0;
//		}
//		catch(ArithmeticException e){
//			System.out.println(e.getClass().getName());
//		}
//		catch(RuntimeException e) {
//			System.out.println(e.getClass().getName());
//		}
//		catch(Throwable e) {
//			System.out.println(e.getClass().getName());
//		}
		
//		String[] actors = {"a","b","c"};
//		String[] languages = {Languages.BHOJPURI.name(),Languages.ENGLISH.name(),
//				              Languages.HINDI.name()};
//		
//		Genres genre = Genres.COMEDY;
//		try {
//		  Movie movie = new Movie(actors,"a","a",genre,"a",languages,2.20f,"\"C:\\Users\\rajnish.shonkhia\\Downloads\\BRAHMĀSTRA OFFICIAL TRAILER _ Hindi _ Amitabh _ Ranbir _ Alia _ Ayan _ In Cinemas 9th September.mp4\"");
//		  movi
//			
//		}
//		catch (InvalidIdException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} 
//		catch (InvalidLengthException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (javax.naming.InvalidNameException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} 
//		
//		int a = 5;
//		Integer b = a;   // new Integer(a)
//		int c = b;       // b.intValue()
//		System.out.println(c);

//		UserService userService =  UserServiceImpl.getInstance();
//		
//		try {
//			User user = userService.insertUser(new User("abhi","chivate","raj13@gmail.com",LocalDate.now(),LocalDate.now(),true));
//			System.out.println(user);
//		} catch (UnableToGenerateIdException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		userService.insertUser(new User("ab001","abhi","chivate","@g.com",null,null,true));
//		System.out.println(user);
		
		
//		User u = userService.updateUserById("ab001",new User("ab001","raju123","chivate"
//				,"@g.com",LocalDate.now(),LocalDate.now(),true) );
//		System.out.println(u);
		
//		Optional<User> result = userService.getUserById("ab001");
//		User user = result.get();
//		System.out.println(user);
//
//		Optional<List<User>> result1 = userService.getAllUsers();
//		
//		List<User> u = new ArrayList<>();
//		u = result1.get();
//		
//		for (User user2 : u) {
//			System.out.println(user2);
//		}
		
//		MovieService movieService = MovieServiceImpl.getInstance();
//		Genres genre = Genres.COMEDY;
//		String[] actors = {"a","b","c"};
//		String[] languages = {Languages.BHOJPURI.name(),Languages.ENGLISH.name(),
//	              Languages.HINDI.name()};
//		try {
//			Movie m = movieService.
//					insertMovie(new Movie(actors,"ab",
//							"ab",genre,"ab",languages,2.20f,"C:\\Users\\rajnish.shonkhia\\Downloads\\BRAHMĀSTRA.mp4"));
//			System.out.println(m);
//		} catch (javax.naming.InvalidNameException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (InvalidIdException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (InvalidLengthException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (UnableToGenerateIdException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		Optional<List<Movie>> optional = movieService.getAllMovies();
//		List<Movie> m = optional.get();
//		for (Movie movie : m) {
//			System.out.println(movie);
//		}
		
//		Optional<Movie> op = movieService.getMovieByMovieId("A1002");
//		System.out.println(op.get());
		
//		if(!result.isPresent())
//		{
//			System.out.println("record not found");
//		}
//		else
//		{
//			User user = result.get();
//			System.out.println(user);
//		}
//		
//		Optional<List<User>> result_all = userService.getAllUsers();
//		List<User> users = new ArrayList<User>(result_all.get());
//		
//		for (User user : users) {
//			System.out.println(user);
//			
//		}
//		
//		MovieService movieService = MovieServiceImpl.getInstance();
//		
//		Genres g = Genres.COMEDY;
//		Genres g1 = Genres.HORROR;
//		String[] lang = {"ENGLISH","HINDI"};
//		String[] actors = {"a","b"};
//		
//		try {
//			movieService.insertMovie(new Movie("a10001",actors,"hulk","a",g,"a",lang,5.00f));
//			movieService.insertMovie(new Movie("a10002",actors,"spiderman","a",g1,"a",lang,5.00f));
//			movieService.insertMovie(new Movie("a10003",actors,"Aan","a",g1,"a",lang,5.00f));
//		} catch (javax.naming.InvalidNameException | InvalidIdException | InvalidLengthException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		Optional<Movie[]> movie_all = movieService.getAllMoviesByName("hulk");
//		Movie[] movies = movie_all.get();
//		
//		for (Movie m : movies) {
//			System.out.println(m);
//			
//		}
		
//		Optional<Movie> m = movieService.getMovieByMovieId("A1001");
//		System.out.println(m.get());
		
//		try {
//			Optional<Movie> update = movieService.updateMovie("a10001",new Movie("a10001",actors,"spider-man","a",g,"a",lang,5.00f));
//		} catch (javax.naming.InvalidNameException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (InvalidIdException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (InvalidLengthException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		Optional<List<Movie>> op = null;
//		op = movieService.getAllMovies();
//		
//	    List<Movie> m = op.get();
//		
//		for (Movie movie : m) {
//			System.out.println(movie);
//		}
		
//		try {
//			String deleteResult = movieService.deleteMovieByMovieId("A1001");
//			System.out.println(deleteResult);
//		} catch (NoDataFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//        movie_all = movieService.getAllMovies();
//		
//		 m = movie_all.get();
//		
//		for (Movie movie : m) {
//			System.out.println(movie);
//		}
		
		
//		List<User> u = new ArrayList<>();
//		
//		for (User user : users) {
//			u.add(user);
//		}
//		
//		//Collections.sort(u);
//		Collections.sort(u,new IdComparator());
//		
//		u.forEach(e->System.out.println(e));
//		
//		Comparator<User> comparator = new Comparator<User>() {
//
//			@Override
//			public int compare(User o1, User o2) {
//				// TODO Auto-generated method stub
//				return o1.getUserId().compareTo(o2.getUserId());
//			}
//			
//			
//		};
//		
//		Collections.sort(u,comparator);
//		 u.forEach(e->System.out.println(e));
//		 
//		
//		Comparator<User> comparator2 = (e1,e2)->
//			e1.getUserId().compareTo(e2.getUserId());
//			
//	    
//	    Collections.sort(u,comparator2);
//	    
//	    u.forEach(e->System.out.println(e));
		
		
//		List<Movie> ans = movieService.findByOrderByMovieNameDsc();
//		
//		ans.forEach(e->System.out.println(e));
//		
//        HashMap<Integer,String> hashMap = new HashMap<>();
//		
//		hashMap.put(1, "raj");
//		hashMap.put(5, "abhi");
//		hashMap.put(-10, "abc");
//		
//		System.out.println(hashMap.get(-1000));
//		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
