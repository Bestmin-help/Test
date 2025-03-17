package com.example.demo.book.dto;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity


public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookid;
    private String bookname;
    private String publisher;
    private int price;

}
