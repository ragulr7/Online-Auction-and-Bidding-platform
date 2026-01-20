package com.ey;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class OnlineAuctionAndBiddingPlatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineAuctionAndBiddingPlatformApplication.class, args);
	}

}
