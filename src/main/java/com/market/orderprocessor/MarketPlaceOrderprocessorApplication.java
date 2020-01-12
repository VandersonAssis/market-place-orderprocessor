package com.market.orderprocessor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MarketPlaceOrderprocessorApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarketPlaceOrderprocessorApplication.class, args);
	}

}
