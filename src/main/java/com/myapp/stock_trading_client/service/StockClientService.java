package com.myapp.stock_trading_client.service;

import com.myapp.stock_trading_server.grpc.StockRequest;
import com.myapp.stock_trading_server.grpc.StockResponse;
import com.myapp.stock_trading_server.grpc.StockTradingServiceGrpc;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
public class StockClientService {

    @GrpcClient("stockService")
    private StockTradingServiceGrpc.StockTradingServiceBlockingStub blockingStub;

    public StockResponse getStockPrice(String stockSymbol) {
        StockRequest stockRequest = StockRequest.newBuilder()
                .setStockSymbol(stockSymbol)
                .build();
        return blockingStub.getStockPrice(stockRequest);
    }

}
