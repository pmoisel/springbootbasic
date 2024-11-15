package com.nterra.springbootbasic.client;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;

@RequiredArgsConstructor
public class BookService {

  private List<Book> books = new ArrayList<>();
  private final WebClient bookClient;

  public List<Book> getAllBooks() {
    return bookClient.get().uri("/books").retrieve().bodyToFlux(Book.class).collectList().block();
  }

  public void addBook(Book book) {
    bookClient.post().uri("/books").bodyValue(book)
        .exchange().block();
  }

  public void updateBook(Book book) {
    bookClient.put().uri("/books/" + book.getId()).bodyValue(book)
        .exchange().block();
  }

  public void deleteBook(int id) {
    bookClient.delete().uri("/books/" + id).exchange().block();
  }

  public Book getBookById(int id) {
    System.out.println("get");
    return null;
  }

}
