package com.example.demo.book.repository;

import com.example.demo.book.dto.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
    void registerBook(Book book);

    void modifyBook(Book book);

    void deleteBook(Book book);
}
