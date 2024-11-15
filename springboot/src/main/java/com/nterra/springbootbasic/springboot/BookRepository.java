package com.nterra.springbootbasic.springboot;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class BookRepository {

  private final Map<Integer, Book> books = new HashMap<>();


  public BookRepository() {
    books.put(1, new Book(1, "Spring Boot for Dummies", "not Philipp"));
  }

  public Book save(Book book) {
    return books.put(book.getId(), book);
  }

  public Optional<Book> findById(Integer id) {
    return Optional.ofNullable(books.getOrDefault(id, null));
  }

  public Iterable<Book> findAll() {
    return books.values();
  }

  public int count() {
    return books.size();
  }

  public void deleteById(Integer id) {
    books.remove(id);
  }
}
