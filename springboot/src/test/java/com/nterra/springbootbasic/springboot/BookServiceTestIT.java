package com.nterra.springbootbasic.springboot;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BookServiceTestIT {

  @Autowired
  BookService bookService;

  @Test
  public void testUpdate() {
    // Arrange
    bookService.createBook(new Book(1, "Spring Boot", "Nterra"));

    // Act
    Optional<Book> updatedBook = bookService.updateBook(1,
        new Book(1, "Testing is easy", "nterra"));

    // Assert
    assertThat(updatedBook.isPresent()).isTrue();
    assertThat(updatedBook.get().getId()).isEqualTo(1);

  }
}
