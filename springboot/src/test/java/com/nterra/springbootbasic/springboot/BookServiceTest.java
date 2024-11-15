package com.nterra.springbootbasic.springboot;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import org.junit.jupiter.api.Test;

public class BookServiceTest {

  @Test
  public void testUpdate() {
    // Arrange
    BookService bookService = new BookService(
        new BookRepositoryMock(new Book(1, "Spring Boot", "Nterra")));

    // Act
    Optional<Book> updatedBook = bookService.updateBook(1, new Book(1,"Testing is easy", "nterra"));

    // Assert
    assertThat(updatedBook.isPresent()).isTrue();
    assertThat(updatedBook.get().getId()).isEqualTo(1);

  }
}
