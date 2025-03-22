package com.example.demo.book.service.impl;


import com.example.demo.book.dto.Book;
import com.example.demo.book.repository.BookRepository;
import com.example.demo.book.service.BookService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepository;

    @Override
    public List<Book> list() {
        return bookRepository.findAll();
    }

    @Override
    @Transactional
    public void register(Book book) {


        bookRepository.save(book);
        // 저장된 책
    }

    @Override
    @Transactional
    public void delete(int bookid) {
        bookRepository.deleteById((long) bookid);
    }

    @Override
    public Book findById(int bookid) {
        return bookRepository.findById((long) bookid)
                .orElseThrow(() -> new RuntimeException("책 없음:" + bookid ));
    }

    @Override
    public void modify(int bookid) {

    }

    @Transactional
    @Override
    public void update(Book book) {
        bookRepository.save(book); // JPA의 save 메소드는 이미 존재하는 엔티티면 업데이트함
    }

}