package com.nterra.springbootbasic.spring;

import jakarta.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

@Repository
public class BookRepository {

  private final Map<Integer, Book> books = new HashMap<>();

  @PostConstruct
  public void init() {
    books.put(1, new Book(1, "Spring for Dummies", "not Philipp"));
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
