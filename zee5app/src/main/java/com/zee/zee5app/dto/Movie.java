package com.zee.zee5app.dto;

import javax.naming.InvalidNameException;

import com.zee.zee5app.enums.Genres;
import com.zee.zee5app.enums.Languages;
import com.zee.zee5app.exceptions.InvalidIdException;
import com.zee.zee5app.exceptions.InvalidLengthException;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class Movie {
	private String movieId;
    private String actors[];
    private String movieName;
    private String director;
    private Genres genre;
    private String production;
    private String languages[];
    private float movieLength;
    private String trailer1;
    //private byte[] trailer2;
    
    
    
    
	public void setMovieId(String movieId) throws InvalidIdException {
		
		// movieId should be min 5 and max 7 characters
		int length = movieId.length();
		if(length>=5 && length<=7) {
		this.movieId = movieId;
		}
		else {
			// raise the exception
			// data is not validated
			throw new InvalidIdException("Invalid movie id");
		}
	}
	
	
	public void setActors(String[] actors) throws InvalidNameException {
		if(actors.length == 0)
		{
			throw new InvalidNameException("Invalid actors");
		}
		else
			this.actors = actors;
	}
	
	
	public void setMovieName(String movieName) throws InvalidNameException {
		
		int length = movieName.length();
		if(movieName=="" || movieName==null || length<1)
		{
			throw new InvalidNameException("Invalid movie name");
		}
		else
		{
			this.movieName = movieName;
		}
	}
	
	
	public void setDirector(String director) throws InvalidNameException {
		
		if(director=="" || director==null)
		{
			throw new InvalidNameException("Invalid director name");
		}
		else
			this.director = director;
	}
	
	
	public void setGenre(Genres genre) throws InvalidNameException {
		
		boolean flag=false;
		for (Genres value : Genres.values()) {
			if(value == genre)
			{
				this.genre = genre;
				flag=true;
				break;
			}
		}
		if(!flag)
		{
			throw new InvalidNameException("Invalid genre name");
		}
	}
	
	
	public void setProduction(String production) throws InvalidNameException {
	     
		if(production=="" || production==null)
		{
			throw new InvalidNameException("Invalid production name");
		}
		else
			this.production = production;
		
	}
	
	
	public void setLanguages(String[] languages) throws InvalidNameException {
		
		for (String string : languages) {
			//System.out.println(string);
			if(Languages.valueOf(string)==null) // converts String to Enums
			{
				throw new InvalidNameException("Invalid language name");
			}
//			for (Languages string2 : Languages.values()) {
//				if(string.equals(string2))
//				{
//					
//				}
//			}
		}
		this.languages = languages;
	}
	
	
	public void setMovieLength(float movieLength) throws InvalidLengthException {
		
		if(movieLength <=0.00f)
		{
			throw new InvalidLengthException("Invalid movie length");
		}
		else
			this.movieLength = movieLength;
	}
	
	public void setTrailer1(String trailer1)
	{
		this.trailer1 = trailer1;
	}
	
	public Movie() {
		// TODO Auto-generated constructor stub
	}
	
	public Movie(String[] actors, String movieName, String director, Genres genre, String production,
			String[] languages, float movieLength,String trailer1) throws InvalidIdException, InvalidNameException, InvalidLengthException {
		super();
		this.setActors(actors);
		this.setMovieName(movieName);
		this.setDirector(director);
		this.setGenre(genre);
		this.setProduction(production);
		this.setLanguages(languages);
		this.setMovieLength(movieLength);
		this.setTrailer1(trailer1);
	}
    
    
}
