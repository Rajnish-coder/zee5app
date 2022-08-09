package com.zee.zee5app.repos;

import java.util.List;
import java.util.Optional;

import com.zee.zee5app.dto.Movie;
import com.zee.zee5app.exceptions.NoDataFoundException;
import com.zee.zee5app.exceptions.UnableToGenerateIdException;

public interface MovieRepository {
	
	public Movie insertMovie(Movie movie) throws UnableToGenerateIdException;
	public Optional<Movie> updateMovie(String movieId,Movie movie);
	public String deleteMovieByMovieId(String movieId) throws NoDataFoundException;
	public Optional<List<Movie>> getAllMovies();
	public Optional<Movie[]> getAllMoviesByGenre(String genre);
	public Optional<Movie[]> getAllMoviesByName(String movieName);
	public Optional<Movie> getMovieByMovieId(String movieId);
	public List<Movie> findByOrderByMovieNameDsc();
	
}
