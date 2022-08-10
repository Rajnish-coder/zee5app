package com.zee.zee5app.repos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.StringTokenizer;

import javax.naming.InvalidNameException;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zee.zee5app.dto.Movie;
import com.zee.zee5app.dto.User;
import com.zee.zee5app.enums.Genres;
import com.zee.zee5app.enums.Languages;
import com.zee.zee5app.exceptions.InvalidIdException;
import com.zee.zee5app.exceptions.InvalidLengthException;
import com.zee.zee5app.exceptions.NoDataFoundException;
import com.zee.zee5app.exceptions.UnableToGenerateIdException;
import com.zee.zee5app.utils.DBUtils;

@Repository
public class MovieRepositoryImpl implements MovieRepository {

	@Autowired
	private DBUtils dbUtils;
	
	@Autowired
	DataSource dataSource;
//	private MovieRepositoryImpl() {
//		// TODO Auto-generated constructor stub
//	}
//	
//	private static MovieRepositoryImpl movieRepositoryImpl;
//	
//	public static MovieRepositoryImpl getInstance()
//	{
//		if(movieRepositoryImpl == null)
//			movieRepositoryImpl = new MovieRepositoryImpl();
//		return movieRepositoryImpl;
//	}
	@Override
	public Movie insertMovie(Movie movie) throws UnableToGenerateIdException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String insert = "insert into movies "
				+ "(movieid,actors,moviename,director,genre,production,languages,movielength,trailer) "
				+ "values(?,?,?,?,?,?,?,?,?)";
		String[] actors = movie.getActors();
		String actor_name = null;
		actor_name = String.join(",", actors);
		String[] languages = movie.getLanguages();
		String lang_name = null;
		lang_name = String.join(",", languages);
		Genres genres = movie.getGenre();
		String genre_name = genres.name();
		String movieId;
		
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(insert);
			movieId = dbUtils.movieIdGenerator(movie.getMovieName());
			preparedStatement.setString(1, movieId);
			movie.setMovieId(movieId);
			preparedStatement.setString(2, actor_name);
			preparedStatement.setString(3, movie.getMovieName());
			preparedStatement.setString(4, movie.getDirector());
			preparedStatement.setString(5, genre_name);
			preparedStatement.setString(6, movie.getProduction());
			preparedStatement.setString(7, lang_name);
			preparedStatement.setFloat(8, movie.getMovieLength());
			preparedStatement.setString(9, movie.getTrailer1());
			
			int result = preparedStatement.executeUpdate();
			if(result>0)
				return movie;
			else
				return null;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidIdException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// we should get the result and based on that we will return the result
		return null;
	}

	@Override
	public Optional<Movie> updateMovie(String movieId, Movie movie) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String query = "update movie set movieid=?,actors=?,moviename=?,director=?,"
				+ "genre=?,production=?,languages=?,movielength=?,trailer=? where"
				+ "movieid=?";
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, movie.getMovieId());
			String actors = String.join(",",movie.getActors());
			preparedStatement.setString(2, actors);
			preparedStatement.setString(3, movie.getMovieName());
			preparedStatement.setString(4, movie.getDirector());
			Genres genres = movie.getGenre();
			String genre_name = genres.name();
			preparedStatement.setString(5, genre_name);
			preparedStatement.setString(6, movie.getProduction());
			String[] languages = movie.getLanguages();
			String lang_name = null;
			lang_name = String.join(",", languages);
			preparedStatement.setString(7, lang_name);
			preparedStatement.setFloat(8, movie.getMovieLength());
			preparedStatement.setString(9, movie.getTrailer1());
			int result = preparedStatement.executeUpdate();
			if(result>0)
				return Optional.of(movie);
			else
				return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Optional.empty();
	}

	@Override
	public String deleteMovieByMovieId(String movieId) throws NoDataFoundException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String query = "delete from movies where movieid=?";
		
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, movieId);
			int result = preparedStatement.executeUpdate();
			if(result>0)
			{
				return "success";
			}
			else
			{
				throw new NoDataFoundException("movieid not found");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public Optional<List<Movie>> getAllMovies() {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String query = "select * from movies";
		ResultSet resultSet = null;;
		
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			 resultSet = preparedStatement.executeQuery();
			 List<Movie> movies1 = new ArrayList<>();
			while(resultSet.next())
			{
				Movie m = new Movie();
				try {
					m.setMovieId(resultSet.getString("movieid"));
					List<String> s = new ArrayList<>();
					StringTokenizer st = new StringTokenizer(resultSet.getString("actors"),",");
					while(st.hasMoreElements())
					{
						s.add(st.nextToken());
					}
					
					String[] actors = new String[s.size()];
	
					actors = s.toArray(actors);
					m.setActors(actors);
					m.setMovieName(resultSet.getString("moviename"));
					m.setDirector(resultSet.getString("director"));
					m.setGenre(Genres.valueOf(resultSet.getString("genre")));
					m.setProduction(resultSet.getString("production"));
					st = new StringTokenizer(resultSet.getString("languages"),",");
					s.clear();
					while(st.hasMoreElements())
					{
						s.add(st.nextToken());
					}
					String[] lang = new String[s.size()];
					lang = s.toArray(lang);
					m.setLanguages(lang);
					m.setMovieLength(resultSet.getFloat("movielength"));
					m.setTrailer1(resultSet.getString("trailer"));
					movies1.add(m);
				} catch (InvalidIdException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvalidNameException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvalidLengthException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return Optional.of(movies1);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Optional.empty();
	}

	@Override
	public Optional<Movie[]> getAllMoviesByGenre(String genre) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String query = "select * from movies where genre=?";
		ResultSet resultSet = null;
		List<Movie> movies1 = new ArrayList<>();
		
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, genre);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next())
			{
				Movie m = new Movie();
				try {
					m.setMovieId(resultSet.getString("movieid"));
					List<String> s = new ArrayList<>();
					StringTokenizer st = new StringTokenizer(resultSet.getString("actors"),",");
					while(st.hasMoreElements())
					{
						s.add(st.nextToken());
					}
					
					String[] actors = new String[s.size()];
					actors = s.toArray(actors);
					m.setActors(actors);
					m.setMovieName(resultSet.getString("moviename"));
					m.setDirector(resultSet.getString("director"));
					m.setGenre(Genres.valueOf(resultSet.getString("genre")));
					m.setProduction(resultSet.getString("production"));
					st = new StringTokenizer(resultSet.getString("languages"),",");
					s.clear();
					while(st.hasMoreElements())
					{
						s.add(st.nextToken());
					}
					String[] lang = new String[s.size()];
					lang = s.toArray(lang);
					m.setLanguages(lang);
					m.setMovieLength(resultSet.getFloat("movielength"));
					m.setTrailer1(resultSet.getString("trailer"));
					movies1.add(m);
				} catch (InvalidIdException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvalidNameException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvalidLengthException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			Movie[] m1 = new Movie[movies1.size()];
			m1 = movies1.toArray(m1);
			return Optional.of(m1);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Optional.empty();
	}

	@Override
	public Optional<Movie[]> getAllMoviesByName(String movieName) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String query = "select * from movies where movieName=?";
		ResultSet resultSet = null;
		List<Movie> movies1 = new ArrayList<>();
		
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, movieName);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next())
			{
				Movie m = new Movie();
				try {
					m.setMovieId(resultSet.getString("movieid"));
					List<String> s = new ArrayList<>();
					StringTokenizer st = new StringTokenizer(resultSet.getString("actors"),",");
					while(st.hasMoreElements())
					{
						s.add(st.nextToken());
					}
					
					String[] actors = new String[s.size()];
					actors = s.toArray(actors);
					m.setActors(actors);
					m.setMovieName(resultSet.getString("moviename"));
					m.setDirector(resultSet.getString("director"));
					m.setGenre(Genres.valueOf(resultSet.getString("genre")));
					m.setProduction(resultSet.getString("production"));
					st = new StringTokenizer(resultSet.getString("languages"),",");
					s.clear();
					while(st.hasMoreElements())
					{
						s.add(st.nextToken());
					}
					String[] lang = new String[s.size()];
					lang = s.toArray(lang);
					m.setLanguages(lang);
					m.setMovieLength(resultSet.getFloat("movielength"));
					m.setTrailer1(resultSet.getString("trailer"));
					movies1.add(m);
				} catch (InvalidIdException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvalidNameException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvalidLengthException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			Movie[] m1 = new Movie[movies1.size()];
			m1 = movies1.toArray(m1);
			return Optional.of(m1);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Optional.empty();
	}

	@Override
	public Optional<Movie> getMovieByMovieId(String movieId) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String query = "select * from movies where movieid=?";
		ResultSet resultSet = null;
		
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, movieId);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next())
			{
				// record exists inside RESULTSET object;
				// retrieve User object from RESULTSET;
				Movie m = new Movie();
				try {
					m.setMovieId(movieId);
					StringTokenizer st = new StringTokenizer(resultSet.getString("actors"),",");
					List<String> l = new ArrayList<>();
					while(st.hasMoreElements())
					{
						l.add(st.nextToken());
					}
					String[] actors = new String[l.size()];
					actors = l.toArray(actors);
					m.setActors(actors);
					m.setMovieName(resultSet.getString("moviename"));
					m.setDirector(resultSet.getString("director"));
					m.setGenre(Genres.valueOf(resultSet.getString("genre")));
					m.setProduction(resultSet.getString("production"));
					st = new StringTokenizer(resultSet.getString("languages"),",");
					l.clear();
					while(st.hasMoreElements())
					{
						l.add(st.nextToken());
					}
					String[] lang = new String[l.size()];
					lang = l.toArray(lang);
					m.setLanguages(lang);
					m.setMovieLength(resultSet.getFloat("movielength"));
					m.setTrailer1(resultSet.getString("trailer"));
					
				} catch (InvalidIdException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvalidNameException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvalidLengthException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return Optional.of(m);
				
			}
			else {
				return Optional.empty();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return Optional.empty();
	}

	@Override
	public List<Movie> findByOrderByMovieNameDsc() {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String query = "select * from movies order by moviename desc";
		ResultSet resultSet = null;
        List<Movie> movies1 = new ArrayList<>();
		
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next())
			{
				Movie m = new Movie();
				try {
					m.setMovieId(resultSet.getString("movieid"));
					List<String> s = new ArrayList<>();
					StringTokenizer st = new StringTokenizer(resultSet.getString("actors"),",");
					while(st.hasMoreElements())
					{
						s.add(st.nextToken());
					}
					
					String[] actors = new String[s.size()];
					actors = s.toArray(actors);
					m.setActors(actors);
					m.setMovieName(resultSet.getString("moviename"));
					m.setDirector(resultSet.getString("director"));
					m.setGenre(Genres.valueOf(resultSet.getString("genre")));
					m.setProduction(resultSet.getString("production"));
					st = new StringTokenizer(resultSet.getString("languages"),",");
					s.clear();
					while(st.hasMoreElements())
					{
						s.add(st.nextToken());
					}
					String[] lang = new String[s.size()];
					lang = s.toArray(lang);
					m.setLanguages(lang);
					m.setMovieLength(resultSet.getFloat("movielength"));
					m.setTrailer1(resultSet.getString("trailer"));
					movies1.add(m);
				} catch (InvalidIdException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvalidNameException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvalidLengthException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			return movies1;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
