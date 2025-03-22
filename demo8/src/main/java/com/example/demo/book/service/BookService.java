package com.example.demo.book.service;

import com.example.demo.book.dto.Book;
import jakarta.transaction.Transactional;

import java.util.List;


public interface BookService {
    List<Book> list();

    void register(Book book);

    void delete(int bookid);

    Book findById(int bookid);

    void modify(int bookid);

    @Transactional
    void update(Book book);
}
