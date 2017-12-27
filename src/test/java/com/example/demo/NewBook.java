package com.example.demo;

import java.io.Serializable;

public class NewBook extends Book implements Serializable {
    private String author;  
      
    public NewBook(int isbn,String author) {  
        super(isbn);  
        this.author = author;  
    }  
  
    public String getAuthor() {  
        return author;  
    }  
  
    public void setAuthor(String author) {  
        this.author = author;  
    }  
  
    @Override  
    public String toString() {  
        return "NewBook [author=" + author + super.toString()  
                + "]";  
    }  
  
      
} 