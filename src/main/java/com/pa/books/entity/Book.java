package com.pa.books.entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author apo
 */
@Entity
@Table(name = "books")
public class Book {

    @Id
    @Size(min = 9,max = 9,
    message = "ISBN contain 9 numbers")
    private String isbn;

    @Column(name = "author")
    @Pattern(regexp = "^[A-Z][a-z]*", message = "First letter must be capital")
    @Size(min = 2, max = 30, message = "Author name must have from 2 to 30 characters")
    private String author;

    @Column(name = "title")
    @NotEmpty(message = "This field should not be empty")
    private String title;

    public Book() {}

    public Book(String isbn, String author, String title) {
        this.isbn = isbn;
        this.author = author;
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
