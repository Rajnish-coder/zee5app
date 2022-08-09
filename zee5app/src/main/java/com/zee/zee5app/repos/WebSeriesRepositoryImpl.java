package com.zee.zee5app.repos;

import java.util.List;
import java.util.Optional;

import com.zee.zee5app.dto.WebSeries;

public class WebSeriesRepositoryImpl implements WebSeriesRepository {

	@Override
	public WebSeries insertWebSeries(WebSeries webSeries) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<WebSeries> updateWebSeries(String webSeriesId, WebSeries webSeries) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public String deleteWebSeriesById(String webSeriesId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<List<WebSeries>> getAllWebSeries() {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Optional<WebSeries[]> getAllWebSeriesByGenre(String genre) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Optional<WebSeries[]> getAllWebSeriesByName(String webSeriesName) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Optional<WebSeries> getWebSeriesById(String webSeriesId) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public List<WebSeries> findByOrderByWebSeriesNameDsc() {
		// TODO Auto-generated method stub
		return null;
	}

}
