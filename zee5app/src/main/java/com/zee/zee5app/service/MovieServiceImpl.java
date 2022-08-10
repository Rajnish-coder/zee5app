package com.zee.zee5app.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zee.zee5app.dto.Movie;
import com.zee.zee5app.exceptions.NoDataFoundException;
import com.zee.zee5app.exceptions.UnableToGenerateIdException;
import com.zee.zee5app.repos.MovieRepository;
import com.zee.zee5app.repos.MovieRepositoryImpl;

@Service
public class MovieServiceImpl implements MovieService {

//	private MovieServiceImpl()
//	{
//		
//	}
//	
//	private static MovieService movieService;
//	
//	public static MovieService getInstance()
//	{
//		if(movieService == null)
//		{
//			movieService = new MovieServiceImpl();
//		}
//		
//		return movieService;
//	}
	
	@Autowired
	private MovieRepository movieRepository;
	@Override
	public Movie insertMovie(Movie movie) throws UnableToGenerateIdException, FileNotFoundException {
	
		// trailer file exists or not
		File file = new File(movie.getTrailer1());
		BufferedInputStream bufferedInputStream = null;
		BufferedOutputStream bufferedOutputStream = null;
		System.out.println(file.getName());
		
		try {
			if(movie.getTrailer1() == null || movie.getTrailer1() == "" ||
					file.exists()==false)
			{
				throw new FileNotFoundException("file does not exists");
			}
			else
			{
				bufferedInputStream = new BufferedInputStream(
						new  FileInputStream(file));
                bufferedOutputStream = new BufferedOutputStream(
                		new FileOutputStream("d:\\Zee5App\\Trailer\\" + file.getName()));
                bufferedOutputStream.write(bufferedInputStream.readAllBytes());
				
				System.out.println("file exists");
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				bufferedInputStream.close();
				bufferedOutputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// shift that file to zee5app/trailer folder
		// provide the location to trailer field
		// then take the path and store it in db.(handled by repo)
		
		
		
		return movieRepository.insertMovie(movie);
	}

	@Override
	public Optional<Movie> updateMovie(String movieId, Movie movie) {
		// TODO Auto-generated method stub
		return movieRepository.updateMovie(movieId, movie);
	}

	@Override
	public String deleteMovieByMovieId(String movieId) throws NoDataFoundException {
		// TODO Auto-generated method stub
		
		return movieRepository.deleteMovieByMovieId(movieId);
	}

	@Override
	public Optional<List<Movie>> getAllMovies() {
		// TODO Auto-generated method stub
		return movieRepository.getAllMovies();
	}

	@Override
	public Optional<Movie[]> getAllMoviesByGenre(String genre) {
		// TODO Auto-generated method stub
		return movieRepository.getAllMoviesByGenre(genre);
	}

	@Override
	public Optional<Movie[]> getAllMoviesByName(String movieName) {
		// TODO Auto-generated method stub
		return movieRepository.getAllMoviesByName(movieName);
	}

	@Override
	public Optional<Movie> getMovieByMovieId(String movieId) {
		// TODO Auto-generated method stub
		return movieRepository.getMovieByMovieId(movieId);
	}

	@Override
	public List<Movie> findByOrderByMovieNameDsc() {
		// TODO Auto-generated method stub

		return movieRepository.findByOrderByMovieNameDsc();
	}

}
