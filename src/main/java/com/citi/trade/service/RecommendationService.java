package com.citi.trade.service;

import java.util.List;

import com.citi.trade.model.StockDetails;

public interface RecommendationService {
	//to get the recommendation of each sector according to parameter
	public List<StockDetails> getRecommendation(String sector, String parameter);
}
