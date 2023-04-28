package com.webshop.CloudGateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Mono;

import java.util.Base64;

@SpringBootApplication
public class CloudGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudGatewayApplication.class, args);
	}

	@Bean
	KeyResolver userKeyResolver(){
		return exchange -> Mono.just("userKey");
	}

	@Bean
	public RedisRateLimiter redisRateLimiter() {
		return new RedisRateLimiter(5, 5, 1);
	}

	@Bean
	public Base64.Decoder getDecoder(){
		Base64.Decoder deckoder = Base64.getUrlDecoder();
		return deckoder;
	}
}
