package com.nterra.springbootbasic.springboot;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class BookRepositoryMock implements BookRepository {

  private final Map<Integer, Book> books = new HashMap<>();

  public BookRepositoryMock(Book... books) {
    for (Book book : books) {
      this.books.put(book.getId(), book);
    }
  }

  @Override
  public Optional<Book> findById(Integer id) {
    return Optional.ofNullable(books.getOrDefault(id, null));
  }

  @Override
  public <S extends Book> S save(S entity) {
    books.put(entity.getId(), entity);
    return entity;
  }

  @Override
  public <S extends Book> Iterable<S> saveAll(Iterable<S> entities) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public boolean existsById(Integer integer) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public Iterable<Book> findAll() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public Iterable<Book> findAllById(Iterable<Integer> integers) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public long count() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void deleteById(Integer integer) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void delete(Book entity) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void deleteAllById(Iterable<? extends Integer> integers) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void deleteAll(Iterable<? extends Book> entities) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void deleteAll() {
    throw new UnsupportedOperationException("Not supported yet.");
  }
}
