package com.springboot.manytomany.model;

import java.util.*;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Table;


@Entity
@Table(name="book")
public class Book {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private int id;

     @Column(name="name")
    private String name;
    @Column(name="imageurl")
    private String imageUrl;
     @ManyToOne
  @JoinColumn(name = "publisherid")
    private Publisher publisher;

     @ManyToMany
     @JoinTable(
         name="book_author",
         joinColumns=@JoinColumn(name="bookid"),  // Foreign key to the Book table.
         inverseJoinColumns=@JoinColumn(name="authorid")  // Foreign key to the Author table.
     )

     private List<Author> authors;

    public Book(){
        
    }
    public Book(int id, String name , String imageUrl , Publisher publisher,List<Author> authors ){
        this.id=id;
        this.name=name;
        this.imageUrl=imageUrl;
        this.publisher=publisher;
        this.authors = authors;
        
    }
    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }

    public String getImageUrl(){
        return imageUrl;
    }
    public void setId(int id){
        this.id=id;
    }
    public void setName(String name){
        this.name =name;
    }
    public void setImageUrl(String imageUrl){
        this.imageUrl =imageUrl;
    }


    public Publisher getPublisher(){
        return publisher;
    }
    public void setPublisher(Publisher publisher){
        this.publisher=publisher;
    }
    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }
}