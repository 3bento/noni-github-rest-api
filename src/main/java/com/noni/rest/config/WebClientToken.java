package com.noni.rest.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeFilterFunctions;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Configuration
public class WebClientToken {

	@Value("${GITHUB_PERSONAL_ACCESS_TOKEN}")
	private String token;
	
	@Bean
	public WebClient webClient() {
		return WebClient.builder()
		        .baseUrl("https://api.github.com")
		        .defaultHeader(HttpHeaders.CONTENT_TYPE, "application/vnd.github.v3+json")
		        .defaultHeader(HttpHeaders.USER_AGENT, "Spring 5 WebClient")
		        .filter(ExchangeFilterFunctions.basicAuthentication("nonihongo2", token))
		        .filter(logRequest())
		        .build();
	}
	
	private ExchangeFilterFunction logRequest() {
		return (clientRequest, next) -> {
			log.info("Request: {}, {}", clientRequest.method(), clientRequest.url());
			clientRequest.headers().forEach((name, values) -> values.forEach(value -> log.info("{}={}", name, value)));
			return next.exchange(clientRequest);
		};
	}

}
