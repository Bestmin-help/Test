package com.example.demo.book.service;

import com.example.demo.book.dto.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface BookService extends JpaRepository<Book, Long> {
    List<Book> getAllBooks();

    List<Book> list();

    void registerBook(Book book);
    void modifyBook(Book book);
    void deleteBook(Book book);
    Book findBookById(int bookid);

    Book findBookByid(int bookid);
}

// 데이터베이스 접근 로직