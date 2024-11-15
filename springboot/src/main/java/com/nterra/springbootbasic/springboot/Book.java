package com.nterra.springbootbasic.springboot;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

  private Integer id;
  private String title;
  private String author;

  public Book(String title, String author) {
    this.title = title;
    this.author = author;
  }
}
