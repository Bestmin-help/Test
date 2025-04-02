package com.example.demo.book.service.impl;

import com.example.demo.book.dto.Book;
import com.example.demo.book.repository.BookRepository;
import com.example.demo.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> list() {
        return bookRepository.findAll();
    }

    @Override
    @Transactional
    public void register(Book book) {
        bookRepository.save(book);
    }

    @Override
    @Transactional
    public void delete(int bookid) {
        // ID 타입 불일치 처리
        bookRepository.deleteById((long) bookid);
    }

    @Override
    public Book findById(int bookid) {
        return bookRepository.findById((long) bookid)
                .orElseThrow(() -> new RuntimeException("책을 찾을 수 없습니다: ID " + bookid));
    }

    @Override
    public void modify(int bookid) {

    }

    @Override
    @Transactional
    public void update(Book book) {
        // 존재하면 저장(업데이트)
        bookRepository.save(book);
    }

    @Override
    public List<Book> searchByBookname(String keyword) {
        return bookRepository.findByBooknameContainingIgnoreCase(keyword);
    }

    @Override
    public List<Book> searchByPublisher(String keyword) {
        return bookRepository.findByPublisherContainingIgnoreCase(keyword);
    }

    @Override
    public List<Book> searchByAll(String keyword) {
        // 주석 처리된 코드를 활성화하는 것이 좋습니다
        Set<Book> result = new HashSet<>();
        result.addAll(bookRepository.findByBooknameContainingIgnoreCase(keyword));
        result.addAll(bookRepository.findByPublisherContainingIgnoreCase(keyword));
        return new ArrayList<>(result);
    }

    @Override
    public List<Book> searchByCart(String keyword) {
        return List.of();
    }

    @Override
    public void addbook(int bookid) {

    }

    @Override
    public void Addbook(int bookid) {

    }

}