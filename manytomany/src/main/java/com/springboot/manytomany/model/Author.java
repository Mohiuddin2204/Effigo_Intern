package com.springboot.manytomany.model;


import java.util.* ;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="author")
public class Author {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="authorid")
    private int authorId;
    
   
     @Column(name="authorname")
     private String authorName;
    
      @JsonIgnore
      @ManyToMany(mappedBy = "authors")
      private List<Book> books;
      public Author(){

      }
      public Author(int authorId,String authorName,List<Book> books){
         this.authorId=authorId;
         this.authorName=authorName;
         this.books=books;
       
      }
     public int getAuthorId(){
        return authorId;
     }
     public String getAuthorName(){
        return authorName;
     }

     public void setAuthorId(int some){
        this.authorId=some;
     }
     public void setAuthorName(String authorName){
        this.authorName=authorName;
     }
     public List<Book> getBooks() {
      return books;
  }

     public void setBooks(List<Book> books) {
      this.books = books;
  }
 
}