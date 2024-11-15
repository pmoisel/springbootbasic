package com.nterra.springbootbasic.client;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class ClientApplication implements CommandLineRunner {

  private final WebClient plainClient, springClient, springBootClient;

  public ClientApplication(WebClient plainClient, WebClient springClient,
      WebClient springBootClient) {
    this.plainClient = plainClient;
    this.springClient = springClient;
    this.springBootClient = springBootClient;
  }


  public static void main(String[] args) {
    new SpringApplicationBuilder(ClientApplication.class).headless(false)
        .web(WebApplicationType.NONE).run(args);
  }

  public void run(String... args) {
    BookFrame bookFrame = new BookFrame(new BookService(plainClient), new BookService(springClient), new BookService(springBootClient));
    bookFrame.setVisible(true);
  }

}
