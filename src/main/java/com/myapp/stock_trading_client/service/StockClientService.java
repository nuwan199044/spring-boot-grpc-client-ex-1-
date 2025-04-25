package com.myapp.stock_trading_client.service;

import com.myapp.stock_trading_server.grpc.StockRequest;
import com.myapp.stock_trading_server.grpc.StockResponse;
import com.myapp.stock_trading_server.grpc.StockTradingServiceGrpc;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
public class StockClientService {

//    @GrpcClient("stockService")
//    private StockTradingServiceGrpc.StockTradingServiceBlockingStub blockingStub;
//
//    public StockResponse getStockPrice(String stockSymbol) {
//        StockRequest stockRequest = StockRequest.newBuilder()
//                .setStockSymbol(stockSymbol)
//                .build();
//        return blockingStub.getStockPrice(stockRequest);
//    }

    @GrpcClient("stockService")
    private StockTradingServiceGrpc.StockTradingServiceStub stub;

    public void subscribeStockPrice(String symbol) {
        StockRequest stockRequest = StockRequest.newBuilder()
                .setStockSymbol(symbol)
                .build();

        stub.subscribeStockPrice(stockRequest, new StreamObserver<StockResponse>() {
            @Override
            public void onNext(StockResponse stockResponse) {
                System.out.println("Stock price update -> "+ stockResponse);
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("Error "+ throwable.getMessage());
            }

            @Override
            public void onCompleted() {
                System.out.println("process is completed !");
            }
        });
    }



}
