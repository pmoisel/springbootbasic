package com.nterra.springbootbasic.springboot;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BookServiceMockitoExtensionTest {

  @Mock
  BookRepository bookRepository;

  @Test
  public void testUpdate() {
    // Arrange
    when(bookRepository.findById(1)).thenReturn(Optional.of(new Book(1, "Nterra", "Spring Boot")));
    when(bookRepository.save(any())).thenAnswer(invocation -> invocation.getArguments()[0]);
    BookService bookService = new BookService(bookRepository);

    // Act
    Optional<Book> updatedBook = bookService.updateBook(1,
        new Book(1, "Testing is easy", "nterra"));

    // Assert
    assertThat(updatedBook.isPresent()).isTrue();
    assertThat(updatedBook.get().getId()).isEqualTo(1);

  }
}
