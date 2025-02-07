package com.springboot.manytomany.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.springboot.manytomany.model.Author;
import com.springboot.manytomany.model.Book;
import com.springboot.manytomany.model.Publisher;
import com.springboot.manytomany.repository.AuthorJpaRepository;
import com.springboot.manytomany.repository.BookJpaRepository;
import com.springboot.manytomany.repository.BookRepository;
import com.springboot.manytomany.repository.PublisherJpaRepository;

import jakarta.transaction.Transactional;


@Service
public class BookH2service implements BookRepository {
    
    @Autowired
    private BookJpaRepository bookJpaRepository;
    
    @Autowired
    private PublisherJpaRepository publisherJpaRepository;

    @Autowired
    private AuthorJpaRepository authorJpaRepository;

    @Override
    public ArrayList<Book> getBooks(){
        List<Book> bookList = bookJpaRepository.findAll();
        return new ArrayList<>(bookList);
    }

    @Override
    public Book getBookById(int id){
        return bookJpaRepository.findById(id).orElseThrow(() -> 
            new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));
    }

    @Transactional
    @Override
    public Book addBook(Book book) {
        try {
            // Validate and get the publisher from the book
            Publisher publisher = book.getPublisher();
            if (publisher == null || publisher.getId() == 0) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid publisher ID");
            }
            
            // Find publisher by ID
            publisher = publisherJpaRepository.findById(publisher.getId()).orElseThrow(() -> 
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Publisher not found"));

            book.setPublisher(publisher);

            // Collect author IDs from the book's authors list
            List<Integer> authorIds = new ArrayList<>();
            for (Author author : book.getAuthors()) {
                if (author != null && author.getAuthorId() != -1) {  
                    authorIds.add(author.getAuthorId());
                }
            }

            // Find all authors by their IDs
            List<Author> authors = authorJpaRepository.findAllById(authorIds);

            // Ensure all authors were found
            if (authors.size() != authorIds.size()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Some authors were not found");
            }

            // Set authors to the book
            book.setAuthors(authors);

            // Save the book along with the associated authors and publisher
            return bookJpaRepository.save(book);
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error occurred while adding the book", e);
        }
    }

    @Override
    public Book updateBook(int bookId, Book book) {
        Book original = bookJpaRepository.findById(bookId).orElseThrow(() -> 
            new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));

        if (book.getName() != null) {
            original.setName(book.getName());
        }
        if (book.getImageUrl() != null) {
            original.setImageUrl(book.getImageUrl());
        }
        if (book.getPublisher() != null) {
            Publisher publisher = publisherJpaRepository.findById(book.getPublisher().getId()).orElseThrow(() -> 
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Publisher not found"));
            original.setPublisher(publisher);
        }
        if (book.getAuthors() != null) {
            List<Integer> authorIds = new ArrayList<>();
            for (Author author : book.getAuthors()) {
                authorIds.add(author.getAuthorId());
            }
            List<Author> authors = authorJpaRepository.findAllById(authorIds);
            if (authors.size() != authorIds.size()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Some authors were not found");
            }
            original.setAuthors(authors);
        }

        return bookJpaRepository.save(original);
    }

    @Override
    public void deleteBook(int bookId) {
        try {
            bookJpaRepository.deleteById(bookId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found");
        }
    }

    public Publisher getBookPublisher(int id) {
        Book book = bookJpaRepository.findById(id).orElseThrow(() -> 
            new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));
        Publisher publisher = book.getPublisher();
        if (publisher == null) {
            publisher = new Publisher();  // Returning a default "no publisher" publisher if null
            publisher.setPublisherName("No publisher assigned");
        }
        return publisher;
    }

    @Override
    public List<Author> getBookAuthors(int id){
        Book book = bookJpaRepository.findById(id).orElseThrow(() -> 
            new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));
        return book.getAuthors();
    }
}