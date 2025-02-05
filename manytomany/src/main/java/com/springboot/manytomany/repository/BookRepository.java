package com.springboot.manytomany.repository;
import java.util.ArrayList;
import java.util.List;

import com.springboot.manytomany.model.Author;
import com.springboot.manytomany.model.Book;
import com.springboot.manytomany.model.Publisher;


public interface BookRepository {
	   
    ArrayList<Book> getBooks();
    Book getBookById(int bookId);
    Book addBook(Book book);
    Book updateBook(int bookId,Book book);
    void deleteBook(int bookId);
    Publisher getBookPublisher(int bookId);
    List<Author> getBookAuthors(int id);
}