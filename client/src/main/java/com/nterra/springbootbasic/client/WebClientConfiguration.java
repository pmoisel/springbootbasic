package com.nterra.springbootbasic.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfiguration {

  @Bean
  public WebClient plainClient() {
    return WebClient.create("http://localhost:8079");
  }

  @Bean
  public WebClient springClient() {
    return WebClient.create("http://localhost:8080/spring");
  }

  @Bean
  public WebClient springBootClient() {
    return WebClient.create("http://localhost:8081");
  }
}
