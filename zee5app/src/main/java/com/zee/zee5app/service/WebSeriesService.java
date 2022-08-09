package com.zee.zee5app.service;

import java.util.List;
import java.util.Optional;

import com.zee.zee5app.dto.WebSeries;

public interface WebSeriesService {

	public WebSeries insertWebSeries(WebSeries webSeries);
	public Optional<WebSeries> updateWebSeries(String webSeriesId,WebSeries webSeries);
	public String deleteWebSeriesById(String webSeriesId);
	public Optional<List<WebSeries>> getAllWebSeries();
	public Optional<WebSeries[]> getAllWebSeriesByGenre(String genre);
	public Optional<WebSeries[]> getAllWebSeriesByName(String webSeriesName);
	public Optional<WebSeries> getWebSeriesById(String webSeriesId);
	public List<WebSeries> findByOrderByWebSeriesNameDsc();
}
