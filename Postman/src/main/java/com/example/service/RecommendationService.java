package com.example.service;
import java.io.IOException;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PathVariable;
import com.example.model.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

@Service
public class RecommendationService {
	
	
	@Autowired
	UserHistoryService saved_stocks_service;
	
	@Autowired
	UserService user_service;
	
	Calendar s = Calendar.getInstance();
	Calendar l = Calendar.getInstance();
	

	
	Map<String, List<String>> sector_wise_companies = Map.ofEntries(
			new AbstractMap.SimpleEntry<String, List<String>>("Automobile",
					new ArrayList<>(Arrays.asList("BAJAJ-AUTO.NS", "EICHERMOT.NS", "HEROMOTOCO.NS", "M&M.NS",
							"MARUTI.NS", "TATAMOTORS.NS"))),
			new AbstractMap.SimpleEntry<String, List<String>>("Banking",
					new ArrayList<>(Arrays.asList("AXISBANK.NS", "HDFCBANK.NS", "ICICIBANK.NS ", " INDUSINDBK.NS ",
							"KOTAKBANK.NS ", " SBIN.NS"))),
			new AbstractMap.SimpleEntry<String, List<String>>("Cement",
					new ArrayList<>(Arrays.asList("ASIANPAINT.NS", "BRITANNIA.NS", "HINDUNILVR.NS", "ITC.NS",
							"NESTLEIND.NS", "TITAN.NS"))),
			new AbstractMap.SimpleEntry<String, List<String>>("Energy",
					new ArrayList<>(Arrays.asList("BPCL.NS", "GAIL.NS", "IOC.NS", "ONGC.NS", "RELIANCE.NS", "NTPC.NS",
							"POWERGRID.NS"))),
			new AbstractMap.SimpleEntry<String, List<String>>("Information Technology",
					new ArrayList<>(Arrays.asList("HCLTECH.NS", "INFY.NS", "TCS.NS", "TECHM.NS", "WIPRO.NS"))));

	
	//@ApiOperation(value = "fetch sector data", notes = "Fetch data from yahoo finance api for particular category")
	public List<UserHistory> FetchHistoricData(@PathVariable("category") String category) {
		
		//ObjectMapper mapper = new ObjectMapper();
		List<UserHistory> res = new ArrayList<>();
		
		
		String jsonStr = null;
		 ObjectMapper Obj = new ObjectMapper(); 
		  
	        try { 
	  
	            // get Oraganisation object as a json string 
	             jsonStr = Obj.writeValueAsString(res); 
	  
	            // Displaying JSON String 
	            System.out.println(jsonStr); 
	        } 
	  
	        catch (IOException e) { 
	            e.printStackTrace(); 
	        } 	
		return res;

	}	
	public List<UserHistory> FetchHistoricDataALL() {
		
		//ObjectMapper mapper = new ObjectMapper();
		List<UserHistory> res = new ArrayList<>();
		
		try {
			for (String sector : sector_wise_companies.keySet()) {
				List<String> companies = sector_wise_companies.get(sector);
				
				String[] tmp = new String[companies.size()];
				for (int i = 0; i < tmp.length; i++)
					tmp[i] = companies.get(i);
				
				Map<String, Stock> stocks = YahooFinance.get(tmp);
				List<Stock> all_stocks = new ArrayList<>(stocks.values());
				
				for (Stock company : all_stocks) {
					if (company == null)
						continue;
					UserHistory t = new UserHistory();
					System.out.println(t);
					res.add(t);
				}
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return res;
	}
	
	public List<StockDetails> getRecommendation(String sector, String parameter) {   //sort method acc to growth
		List<StockDetails> recommendedStocks = new ArrayList<StockDetails>();
		try {
		List<String> companies = sector_wise_companies.get(sector);

		String[] tmp = new String[companies.size()];
		for (int i = 0; i < tmp.length; i++)
		tmp[i] = companies.get(i); //get stock symbols

		Map<String, Stock> stocks = YahooFinance.get(tmp);
		List<Stock> sorted_stocks = new ArrayList<>(stocks.values());

		if(!ObjectUtils.isEmpty(sorted_stocks)) {
		sorted_stocks.forEach(stock -> {
		StockDetails stockDetail = new StockDetails();
		stockDetail.setCompanyName(stock.getName());
		stockDetail.setCurrentPrice(stock.getQuote().getPrice());
		stockDetail.setMarketCap(stock.getStats().getMarketCap());
		stockDetail.setPercentageChange(stock.getQuote().getChangeInPercent());
		stockDetail.setBidPrice(stock.getQuote().getBid());
	//	stockDetail.setRevenue(stock.getStats().getRevenue());
    //		stockDetail.setDividend(stock.getDividend().getAnnualYield());
		try {
		stockDetail.setVolume(stock.getHistory().get(0).getVolume());
		} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		stockDetail.setGrowth(stock.getQuote().getChangeFromAvg50InPercent());
		recommendedStocks.add(stockDetail);
		
		//stockDetail.setTwoWeeksPrice(stock);
		});


		if("Growth".equalsIgnoreCase(parameter)) { // when user selects growth
		Collections.sort(recommendedStocks, new Comparator<StockDetails>() {
		public int compare(StockDetails s1, StockDetails s2) {
		return s2.getGrowth().compareTo(s1.getGrowth());
		}
		});}


		if("Roe".equalsIgnoreCase(parameter)) { // when user selects roe
		Collections.sort(recommendedStocks, new Comparator<StockDetails>() {
		public int compare(StockDetails s1, StockDetails s2) {
		return s2.getRoe().compareTo(s1.getRoe());
		}
		});}


		if("Volume".equalsIgnoreCase(parameter)) { // when user selects volume
		Collections.sort(recommendedStocks, new Comparator<StockDetails>() {
		public int compare(StockDetails s1, StockDetails s2) {
		return s2.getVolume().compareTo(s1.getVolume());
		}
		});}
		if("Momentum".equalsIgnoreCase(parameter)) { // when user selects volume
			Collections.sort(recommendedStocks, new Comparator<StockDetails>() {
			public int compare(StockDetails s1, StockDetails s2) {
			return s2.getVolume().compareTo(s1.getVolume());
			}
			});}
		
		if(parameter.equalsIgnoreCase(null)) { // default case
		Collections.sort(recommendedStocks, new Comparator<StockDetails>() {
		public int compare(StockDetails s1, StockDetails s2) {
		return s2.getGrowth().compareTo(s1.getGrowth());
		}
		});}
		   

		} else {
		System.out.print("Unable to fetch data from yahoo api.");
		}




		} catch (IOException e1) {
		e1.printStackTrace();
		}
		return recommendedStocks;
		}
}


