package com.citi.trade.controller;

import java.io.IOException;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.citi.trade.model.StockDetails;
import com.citi.trade.service.DashBoardServiceImpl;

import yahoofinance.histquotes.HistoricalQuote;



@RestController
@RequestMapping("/dashboard")
@Component
public class DashBoardController {
	
	
	@Autowired
	private DashBoardServiceImpl dashboardService;
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/getPriceShockers")
	public List<StockDetails> getPriceShokers() throws IOException {
		
		return  dashboardService.getPriceShockers();
	}
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/getVolumeShockers")
	public List<StockDetails> getVolumeShokers() throws IOException {
		return  dashboardService.getVolumeShokers();
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/getTopGainer")
	public List<HistoricalQuote> getTopGainer() throws IOException {
		return  dashboardService.getTopGainer();
	}
	
	
	
	
}
