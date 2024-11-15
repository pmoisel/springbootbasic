package com.nterra.springbootbasic.springboot;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Book {
  @Id
  private Integer id;
  private String title;
  private String author;

  public Book(String title, String author) {
    this.title = title;
    this.author = author;
  }
}