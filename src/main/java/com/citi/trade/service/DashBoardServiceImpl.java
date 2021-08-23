package com.citi.trade.service;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import com.citi.trade.model.StockDetails;
import com.citi.trade.util.SectorCompanyList;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;

@Service
public class DashBoardServiceImpl implements DashboardService{


	Map<String, List<String>> sectorWiseCompanies = SectorCompanyList.sectorWiseCompany();
	public List<StockDetails> getPriceShockers() {
		List<StockDetails> priceShockers = getStockData();
		if (!priceShockers.isEmpty()) priceShockers.sort((s1, s2) -> {
			if (s1 != null && s2 != null && s1.getPercentageChange() != null && s2.getPercentageChange() != null)
				return s2.getPercentageChange().compareTo(s1.getPercentageChange());
			else
				return 0;
		});
		return priceShockers;
	}

	public List<StockDetails> getVolumeShokers() {
		List<StockDetails> volumeShockers = getStockData();
		if (!volumeShockers.isEmpty()) volumeShockers.sort((s1, s2) -> {
			if (s1 != null && s2 != null && s1.getVolume() != null && s2.getVolume() != null)
				return s2.getVolume().compareTo(s1.getVolume());
			else
				return 0;
		});
		return volumeShockers;
	}

	public List<HistoricalQuote> getTopGainer() {
		List<StockDetails> topGainer = getStockData();
		System.out.println(topGainer.size());
		int noofWeeks = -2; // two weeks
		Calendar from = Calendar.getInstance();
		Calendar to = Calendar.getInstance();
		from.add(Calendar.WEEK_OF_MONTH, noofWeeks);

		List<HistoricalQuote> historyOfTopGainer = new ArrayList<>();
		if (!topGainer.isEmpty()) {
			topGainer.sort((s1, s2) -> {
				if (s1 != null && s2 != null && s1.getCurrentPrice() != null && s2.getCurrentPrice() != null)
					return s2.getCurrentPrice().compareTo(s1.getCurrentPrice());
				else
					return 0;
			});
			try {				
				int counter = 0;
				while(counter < topGainer.size() && topGainer.get(counter).getSymbol() == null) {
					counter ++;
				}
				Stock topGainerCompanyName = YahooFinance.get(topGainer.get(counter).getSymbol());
				historyOfTopGainer = topGainerCompanyName.getHistory(from, to, Interval.DAILY);	
			}
			catch (IOException e) {

			}
		}

		return historyOfTopGainer;
	}

	public List<StockDetails> getStockData() {
		List<StockDetails> stockData = new ArrayList<>();
		Set<String> sectorList = sectorWiseCompanies.keySet();
		Map<String, String> companySectorMap = new HashMap<String, String>();
		try {
			String[] allTmpStocks = new String[15*sectorList.size()];
			int counter = 0;
			for (String sector : sectorList) {
				List<String> companyList = new ArrayList<>(sectorWiseCompanies.get(sector));
				for (int i = 0; (i < companyList.size() && counter < allTmpStocks.length); i++) {
					allTmpStocks[counter] = companyList.get(i);
					companySectorMap.put(companyList.get(i), sector);
					counter++;
				}
			}

			try {
				Map<String, Stock> stocks = YahooFinance.get(allTmpStocks);
				List<Stock> apiStockData = new ArrayList<>(stocks.values());
				if (!ObjectUtils.isEmpty(apiStockData)) {
					apiStockData.forEach(stock -> {
						StockDetails stockDetails = new StockDetails();
						stockDetails.setCompanyName(stock.getName());
						stockDetails.setSymbol(stock.getSymbol());
						stockDetails.setCurrentPrice(stock.getQuote().getPrice());
						stockDetails.setPercentageChange(stock.getQuote().getChangeInPercent());
						stockDetails.setVolume(stock.getQuote().getVolume());
						stockDetails.setSector(companySectorMap.get(stock.getSymbol()));
						stockData.add(stockDetails);
					});
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stockData;

	}

}