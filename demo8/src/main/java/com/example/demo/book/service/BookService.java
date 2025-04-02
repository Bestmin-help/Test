package com.example.demo.book.service;

import com.example.demo.book.dto.Book;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

public interface BookService {
    /**
     * 모든 도서 목록을 조회합니다.
     */
    List<Book> list();

    @Transactional
    void register(Book book);

    @Transactional
    void delete(int bookid);

    Book findById(int bookid);

    void modify(int bookid);

    @Transactional
    void update(Book book);


    List<Book> searchByBookname(String keyword);


    List<Book> searchByPublisher(String keyword);


    List<Book> searchByAll(String keyword);

    List<Book> searchByCart(String keyword);

    void addbook(int bookid);

    void Addbook(int bookid);
}