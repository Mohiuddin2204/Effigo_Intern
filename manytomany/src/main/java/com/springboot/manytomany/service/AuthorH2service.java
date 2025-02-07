package com.springboot.manytomany.service;

import java.util.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.springboot.manytomany.model.Author;
import com.springboot.manytomany.model.Book;
import com.springboot.manytomany.repository.AuthorJpaRepository;
import com.springboot.manytomany.repository.AuthorRepository;
import com.springboot.manytomany.repository.BookJpaRepository;

import jakarta.transaction.Transactional;

@Service
public class AuthorH2service implements AuthorRepository{
    @Autowired
    private AuthorJpaRepository authorJpaRepository;
    
    @Autowired
    private BookJpaRepository bookJpaRepository;
    @Override
    public ArrayList<Author> getAuthors(){
        List<Author> authorList = authorJpaRepository.findAll();
        ArrayList<Author> authors = new ArrayList<>(authorList);
        return authors;
    }
    @Override
    public Author getAuthorById(int id){
        try{
            Author author=authorJpaRepository.findById(id).get();
        return author;
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
    @Transactional
    @Override
    public Author addAuthor(Author author) {
        // Initialize the books list if it is null
        if (author.getBooks() == null) {
            author.setBooks(new ArrayList<>());
        }

        List<Integer> ids = new ArrayList<>();

        // Collect book ids from the author's books list
        for (Book book : author.getBooks()) {
            if (book != null) {
                ids.add(book.getId());
            }
        }

        // Find all books by their ids from the repository
        List<Book> books = bookJpaRepository.findAllById(ids);

        // Set the books list in the author (this step might be redundant if the books are already set)
        author.setBooks(books);

        // Loop through the books and add the author to each book's authors list
        for (Book book : books) {
            if (book.getAuthors() == null) {
                book.setAuthors(new ArrayList<>());  // Initialize the authors list if it's null
            }
            // Avoid adding duplicate authors using stream check
            if (book.getAuthors().stream().noneMatch(existingAuthor -> existingAuthor.equals(author))) {
                book.getAuthors().add(author);  // Add the author to the book's authors list
            }
        }

        // Save the updated list of books (with author added to each book)
        bookJpaRepository.saveAll(books);

        // Save the author entity after linking the books
        return authorJpaRepository.save(author);
    }

    @Override
    public Author updateAuthor(int id, Author author) {
        try{
        Author orginal=authorJpaRepository.findById(id).get();
       

        if(author.getAuthorName()!=null){
            orginal.setAuthorName(author.getAuthorName());
        }
        if(author.getBooks()!=null){
             
            List<Book> books1= orginal.getBooks();
                for (Book book : books1) {
                    book.getAuthors().remove(orginal);
                }
                bookJpaRepository.saveAll(books1);
            List<Integer> ids=new ArrayList<>();
              for(Book book:author.getBooks()){
                ids.add(book.getId());
              }
              
              List<Book> books=bookJpaRepository.findAllById(ids);
              orginal.setBooks(books);
              for(Book book:books){
                book.getAuthors().add(orginal);
              }
              bookJpaRepository.saveAll(books);

        }
        return  authorJpaRepository.save(orginal);
    }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
    @Override
    public void deleteAuthor(int id) {
        try{
        Author author=authorJpaRepository.findById(id).get();
        List<Book> books=author.getBooks();
        for(Book book :books){
            book.getAuthors().remove(author);
        }
         bookJpaRepository.saveAll(books);

        authorJpaRepository.deleteById(id);
    }
    catch(Exception e){
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    }
    @Override
    public List<Book> getAuthorBooks(int id){
          try{
            Author author=authorJpaRepository.findById(id).get();
            return author.getBooks();
          }
          catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
          }
    }
}