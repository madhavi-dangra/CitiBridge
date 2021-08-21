package com.citi.trade.service;

import java.util.List;

import com.citi.trade.model.StockDetails;

public interface RecommendationService {
	public List<StockDetails> getRecommendation(String sector, String parameter);
}
