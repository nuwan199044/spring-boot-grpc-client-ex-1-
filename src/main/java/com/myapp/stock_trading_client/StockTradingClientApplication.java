package com.myapp.stock_trading_client;

import com.myapp.stock_trading_client.service.StockClientService;
import com.myapp.stock_trading_server.grpc.StockResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class StockTradingClientApplication implements CommandLineRunner {

	private final StockClientService stockClientService;

	public static void main(String[] args) {
		SpringApplication.run(StockTradingClientApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		stockClientService.subscribeStockPrice("GOOGL");
	}
}
