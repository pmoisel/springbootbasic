package com.nterra.springbootbasic.springboot;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class BookController {

  private final BookService bookService;

  public BookController(BookService bookService) {
    this.bookService = bookService;
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Iterable<Book>> getBooks() {
    return ResponseEntity.ok(bookService.getAllBooks());
  }

  @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
  public ResponseEntity<Iterable<Book>> getBooksAsXML() {
    return ResponseEntity.ok(bookService.getAllBooks());
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Book> postBook(@RequestBody Book book) {
    return ResponseEntity.ok(bookService.createBook(book));
  }

  @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Book> putBook(@PathVariable Integer id, @RequestBody Book book) {
    return ResponseEntity.of(bookService.updateBook(id, book));
  }

  @DeleteMapping(path = "/{id}")
  public ResponseEntity<Void> deleteBooks(@PathVariable Integer id) {
    bookService.deleteBook(id);
    return ResponseEntity.ok().build();
  }
}
