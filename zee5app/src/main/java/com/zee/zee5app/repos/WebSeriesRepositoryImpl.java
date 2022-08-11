package com.zee.zee5app.repos;

import java.sql.Connection;
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
import com.zee.zee5app.dto.WebSeries;
import com.zee.zee5app.enums.Genres;
import com.zee.zee5app.exceptions.InvalidIdException;
import com.zee.zee5app.exceptions.InvalidLengthException;
import com.zee.zee5app.exceptions.NoDataFoundException;
import com.zee.zee5app.exceptions.UnableToGenerateIdException;
import com.zee.zee5app.utils.DBUtils;

@Repository
public class WebSeriesRepositoryImpl implements WebSeriesRepository {

	@Autowired
	DataSource dataSource;
	@Autowired
	private DBUtils dbUtils;
	
	
	
	@Override
	public WebSeries insertWebSeries(WebSeries webSeries) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String insert = "insert into webseries "
				+ "(webseriesid,actors,webseriesname,director,genre,production,languages,webserieslength,trailer) "
				+ "values(?,?,?,?,?,?,?,?,?)";
		String[] actors = webSeries.getActors();
		String actor_name = null;
		actor_name = String.join(",", actors);
		String[] languages = webSeries.getLanguages();
		String lang_name = null;
		lang_name = String.join(",", languages);
		Genres genres = webSeries.getGenre();
		String genre_name = genres.name();
		String webSeriesId;
		
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(insert);
			webSeriesId = dbUtils.webSeriesIdGenerator(webSeries.getWebSeriesName());
			preparedStatement.setString(1, webSeriesId);
			webSeries.setWebSeriesId(webSeriesId);
			preparedStatement.setString(2, actor_name);
			preparedStatement.setString(3, webSeries.getWebSeriesName());
			preparedStatement.setString(4, webSeries.getDirector());
			preparedStatement.setString(5, genre_name);
			preparedStatement.setString(6, webSeries.getProduction());
			preparedStatement.setString(7, lang_name);
			preparedStatement.setFloat(8, webSeries.getWebSeriesLength());
			preparedStatement.setString(9, webSeries.getTrailer1());
			
			int result = preparedStatement.executeUpdate();
			if(result>0)
				return webSeries;
			else
				return null;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnableToGenerateIdException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// we should get the result and based on that we will return the result
		return null;
	}

	@Override
	public Optional<WebSeries> updateWebSeries(String webSeriesId,WebSeries webSeries) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String query = "update webseries set webseriesid=?,actors=?,webseriesname=?,director=?,"
				+ "genre=?,production=?,languages=?,webserieslength=?,trailer=? where"
				+ " webseriesid=?";
		String[] actors = webSeries.getActors();
		String actor_name = null;
		actor_name = String.join(",", actors);
		String[] languages = webSeries.getLanguages();
		String lang_name = null;
		lang_name = String.join(",", languages);
		Genres genres = webSeries.getGenre();
		String genre_name = genres.name();
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, webSeriesId);
			webSeries.setWebSeriesId(webSeriesId);
			preparedStatement.setString(2, actor_name);
			preparedStatement.setString(3, webSeries.getWebSeriesName());
			preparedStatement.setString(4, webSeries.getDirector());
			preparedStatement.setString(5, genre_name);
			preparedStatement.setString(6, webSeries.getProduction());
			preparedStatement.setString(7, lang_name);
			preparedStatement.setFloat(8, webSeries.getWebSeriesLength());
			preparedStatement.setString(9, webSeries.getTrailer1());
			preparedStatement.setString(10, webSeriesId);
			
			int result = preparedStatement.executeUpdate();
			if(result>0)
				return Optional.of(webSeries);
			else
				return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Optional.empty();
	}

	@Override
	public String deleteWebSeriesById(String webSeriesId) throws NoDataFoundException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String query = "delete from webseries where webseriesid=?";
		
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, webSeriesId);
			int result = preparedStatement.executeUpdate();
			if(result>0)
			{
				return "success";
			}
			else
			{
				throw new NoDataFoundException("webseriesid not found");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public Optional<List<WebSeries>> getAllWebSeries() {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String query = "select * from webSeries";
		ResultSet resultSet = null;;
		
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			 resultSet = preparedStatement.executeQuery();
			 List<WebSeries> webseries1 = new ArrayList<>();
			while(resultSet.next())
			{
				WebSeries m = new WebSeries();
				m.setWebSeriesId(resultSet.getString("webseriesid"));
				List<String> s = new ArrayList<>();
				StringTokenizer st = new StringTokenizer(resultSet.getString("actors"),",");
				while(st.hasMoreElements())
				{
					s.add(st.nextToken());
				}
				
				String[] actors = new String[s.size()];

				actors = s.toArray(actors);
				m.setActors(actors);
				m.setWebSeriesName(resultSet.getString("webseriesname"));
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
				m.setWebSeriesLength(resultSet.getFloat("webserieslength"));
				m.setTrailer1(resultSet.getString("trailer"));
				webseries1.add(m);
			}
			return Optional.of(webseries1);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Optional.empty();
	}

	@Override
	public Optional<WebSeries[]> getAllWebSeriesByGenre(String genre) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String query = "select * from webseries where genre=?";
		ResultSet resultSet = null;;
		
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, genre);
			 resultSet = preparedStatement.executeQuery();
			 List<WebSeries> webseries1 = new ArrayList<>();
			while(resultSet.next())
			{
				WebSeries m = new WebSeries();
				m.setWebSeriesId(resultSet.getString("webseriesid"));
				List<String> s = new ArrayList<>();
				StringTokenizer st = new StringTokenizer(resultSet.getString("actors"),",");
				while(st.hasMoreElements())
				{
					s.add(st.nextToken());
				}
				
				String[] actors = new String[s.size()];

				actors = s.toArray(actors);
				m.setActors(actors);
				m.setWebSeriesName(resultSet.getString("webseriesname"));
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
				m.setWebSeriesLength(resultSet.getFloat("webserieslength"));
				m.setTrailer1(resultSet.getString("trailer"));
				webseries1.add(m);
			}
			WebSeries[] w = new WebSeries[webseries1.size()];
			w = webseries1.toArray(w);
			return Optional.of(w);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Optional.empty();
	}

	@Override
	public Optional<WebSeries[]> getAllWebSeriesByName(String webSeriesName) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String query = "select * from webseries where webseriesname=?";
		ResultSet resultSet = null;;
		
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, webSeriesName);
			 resultSet = preparedStatement.executeQuery();
			 List<WebSeries> webseries1 = new ArrayList<>();
			while(resultSet.next())
			{
				WebSeries m = new WebSeries();
				m.setWebSeriesId(resultSet.getString("webseriesid"));
				List<String> s = new ArrayList<>();
				StringTokenizer st = new StringTokenizer(resultSet.getString("actors"),",");
				while(st.hasMoreElements())
				{
					s.add(st.nextToken());
				}
				
				String[] actors = new String[s.size()];

				actors = s.toArray(actors);
				m.setActors(actors);
				m.setWebSeriesName(resultSet.getString("webseriesname"));
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
				m.setWebSeriesLength(resultSet.getFloat("webserieslength"));
				m.setTrailer1(resultSet.getString("trailer"));
				webseries1.add(m);
			}
			WebSeries[] w = new WebSeries[webseries1.size()];
			w = webseries1.toArray(w);
			return Optional.of(w);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Optional.empty();
	}

	@Override
	public Optional<WebSeries> getWebSeriesById(String webSeriesId) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String query = "select * from webseries where webseriesid=?";
		ResultSet resultSet = null;;
		
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, webSeriesId);
			 resultSet = preparedStatement.executeQuery();
			if(resultSet.next())
			{
				WebSeries m = new WebSeries();
				m.setWebSeriesId(resultSet.getString("webseriesid"));
				List<String> s = new ArrayList<>();
				StringTokenizer st = new StringTokenizer(resultSet.getString("actors"),",");
				while(st.hasMoreElements())
				{
					s.add(st.nextToken());
				}
				
				String[] actors = new String[s.size()];

				actors = s.toArray(actors);
				m.setActors(actors);
				m.setWebSeriesName(resultSet.getString("webseriesname"));
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
				m.setWebSeriesLength(resultSet.getFloat("webserieslength"));
				m.setTrailer1(resultSet.getString("trailer"));
				
				return Optional.of(m);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Optional.empty();
	}

	@Override
	public List<WebSeries> findByOrderByWebSeriesNameDsc() {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String query = "select * from webseries order by webseriesname desc";
		ResultSet resultSet = null;;
		
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			 resultSet = preparedStatement.executeQuery();
			 List<WebSeries> webseries1 = new ArrayList<>();
			while(resultSet.next())
			{
				WebSeries m = new WebSeries();
				m.setWebSeriesId(resultSet.getString("webseriesid"));
				List<String> s = new ArrayList<>();
				StringTokenizer st = new StringTokenizer(resultSet.getString("actors"),",");
				while(st.hasMoreElements())
				{
					s.add(st.nextToken());
				}
				
				String[] actors = new String[s.size()];

				actors = s.toArray(actors);
				m.setActors(actors);
				m.setWebSeriesName(resultSet.getString("webseriesname"));
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
				m.setWebSeriesLength(resultSet.getFloat("webserieslength"));
				m.setTrailer1(resultSet.getString("trailer"));
				webseries1.add(m);
			}
			return webseries1;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
