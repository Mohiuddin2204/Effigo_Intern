package com.springboot.manytomany.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="publisher")
public class Publisher {
  
    @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name="id")
     private int id;
     
     @Column(name="publishername")
     private String publisherName;
     public Publisher(){

     }
     public Publisher(int id,String name){
            this.id=id;
            this.publisherName=name;
     }
     

     public int getId()
     {
        return id;
     }
     public String getPublisherName(){
        return  publisherName;
     }
     public void setPublisherName(String name){
        this.publisherName=name;
     }
}