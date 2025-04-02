package com.example.demo.book.repository;

import com.example.demo.book.dto.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
    List<Book> findByBooknameContainingIgnoreCase(String keyword);
    List<Book> findByPublisherContainingIgnoreCase(String keyword);
}

//여기서는 기본적은 crud 매서드를 자동 제공한다
// save, findAll(), deleteById() 등등