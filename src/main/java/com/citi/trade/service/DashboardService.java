package com.citi.trade.service;

import java.util.List;

import com.citi.trade.model.StockDetails;

import yahoofinance.histquotes.HistoricalQuote;

public interface DashboardService {
	
	public List<StockDetails> getPriceShockers(); //finding price shockers of all companies mention in comapnylist
	public List<StockDetails> getVolumeShokers(); //finding volume shockers of all companies mention in comapnylist
	public List<HistoricalQuote> getTopGainer(); //finding top gainer from past two week data
	

}
