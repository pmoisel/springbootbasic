package com.nterra.springbootbasic.spring;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

  @Autowired
  private BookRepository bookRepository;

  public Iterable<Book> getAllBooks() {
    return bookRepository.findAll();
  }

  public Book createBook(Book book) {
    book.setId(bookRepository.count() + 1);
    return bookRepository.save(book);
  }

  public Optional<Book> updateBook(Integer id, Book book) {
    return bookRepository.findById(id).map(b -> {
          b.setAuthor(book.getAuthor());
          b.setTitle(book.getTitle());
          return b;
        })
        .map(bookRepository::save);
  }

  public void deleteBook(Integer id) {
    bookRepository.deleteById(id);
  }
}
