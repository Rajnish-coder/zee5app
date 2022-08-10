package com.zee.zee5app.repos;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.zee.zee5app.dto.WebSeries;
import com.zee.zee5app.exceptions.NoDataFoundException;

@Repository
public interface WebSeriesRepository {

	public WebSeries insertWebSeries(WebSeries webSeries);
	public Optional<WebSeries> updateWebSeries(String webSeriesId,WebSeries webSeries);
	public String deleteWebSeriesById(String webSeriesId) throws NoDataFoundException;
	public Optional<List<WebSeries>> getAllWebSeries();
	public Optional<WebSeries[]> getAllWebSeriesByGenre(String genre);
	public Optional<WebSeries[]> getAllWebSeriesByName(String webSeriesName);
	public Optional<WebSeries> getWebSeriesById(String webSeriesId);
	public List<WebSeries> findByOrderByWebSeriesNameDsc();
}
