package com.example.demo;

public class Book{
    private int isbn;  
      
    public Book(int isbn) {  
        super();  
        this.isbn = isbn;  
    }  
  
    public int getIsbn() {  
        return isbn;  
    }  
  
    public void setIsbn(int isbn) {  
        this.isbn = isbn;  
    }  
  
    @Override  
    public String toString() {  
        return "Book [isbn=" + isbn + "]";  
    }  
      
      
} 