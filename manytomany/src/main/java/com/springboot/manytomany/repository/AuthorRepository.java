package com.springboot.manytomany.repository;

import java.util.*;

import com.springboot.manytomany.model.Author;
import com.springboot.manytomany.model.Book;

public interface AuthorRepository {
   
    ArrayList<Author> getAuthors();
    Author getAuthorById(int authorId);
    Author addAuthor(Author author);
    Author updateAuthor(int authorId,Author author);
    void deleteAuthor(int authorId);
    List<Book> getAuthorBooks(int id);
}