package com.nterra.springbootbasic.springboot;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(BookController.class)
@Import(BookService.class)
public class BookControllerTestIT {

  @MockBean
  BookRepository bookRepository;
  @Autowired
  MockMvc mockMvc;
  @Autowired
  ObjectMapper objectMapper;

  @Test
  public void testCreateBook() throws Exception {
    // Arrange
    Book book = new Book("Title", "Author");
    // Act
    mockMvc.perform(
            post("/books")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(book))
        )
        // Assert
        .andExpect(status().isOk());

    book.setId(1);
    verify(bookRepository, times(1)).save(book);
  }
}
