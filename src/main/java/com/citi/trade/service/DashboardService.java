package com.citi.trade.service;

import java.util.List;

import com.citi.trade.model.StockDetails;

import yahoofinance.histquotes.HistoricalQuote;

public interface DashboardService {
	
	public List<StockDetails> getPriceShockers();
	public List<StockDetails> getVolumeShokers();
	public List<HistoricalQuote> getTopGainer();
	public String getTopGainerCompanyName();

}
