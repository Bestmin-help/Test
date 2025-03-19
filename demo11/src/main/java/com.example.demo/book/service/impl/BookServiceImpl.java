package com.example.demo.book.service.impl;


import com.example.demo.book.dto.Book;
import com.example.demo.book.repository.BookRepository;
import com.example.demo.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll(); // 실제 데이터 반환
    }

    @Override
    public List<Book> list() {
        return bookRepository.findAll();
    }

    @Override
    public void registerBook(Book book) {
        bookRepository.save(book);
    }

    @Override
    public void modifyBook(Book book) {
        bookRepository.save(book);
    }

    @Override
    public void deleteBook(Book book) {
        bookRepository.delete(book);
    }

    @Override
    public Book findBookById(int bookid) {
        return bookRepository.findById((long) bookid)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + bookid));
    }

    @Override
    public Book findBookByid(int bookid) {
        return bookRepository.findById((long) bookid)
                .orElseThrow(() -> new RuntimeException("Book not found" + bookid));
    }

    @Override
    public void flush() {
        bookRepository.flush();
    }

    @Override
    public <S extends Book> S saveAndFlush(S entity) {
        return bookRepository.saveAndFlush(entity);
    }

    @Override
    public <S extends Book> List<S> saveAllAndFlush(Iterable<S> entities) {
        return bookRepository.saveAllAndFlush(entities);
    }

    @Override
    public void deleteAllInBatch(Iterable<Book> entities) {
        bookRepository.deleteAllInBatch(entities);
    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {
        bookRepository.deleteAllByIdInBatch(longs);
    }

    @Override
    public void deleteAllInBatch() {
        bookRepository.deleteAllInBatch();
    }

    @Override
    @Deprecated
    public Book getOne(Long aLong) {
        // 이 메서드를 getReferenceById로 대체하거나 위임
        return bookRepository.getReferenceById(aLong);
    }

    @Deprecated
    @Override
    public Book getById(Long aLong) {
        // 이 메서드를 getReferenceById로 대체하거나 위임
        return bookRepository.getReferenceById(aLong);
    }

    @Override
    public Book getReferenceById(Long aLong) {
        // 실제 구현 로직 작성
        return bookRepository.getReferenceById(aLong);
    }

    @Override
    public <S extends Book> Optional<S> findOne(Example<S> example) {
        return bookRepository.findOne(example);
    }

    @Override
    public <S extends Book> List<S> findAll(Example<S> example) {
        return bookRepository.findAll(example);
    }

    @Override
    public <S extends Book> List<S> findAll(Example<S> example, Sort sort) {
        return bookRepository.findAll(example, sort);
    }

    @Override
    public <S extends Book> Page<S> findAll(Example<S> example, Pageable pageable) {
        return bookRepository.findAll(example, pageable);
    }

    @Override
    public <S extends Book> long count(Example<S> example) {
        return bookRepository.count(example);
    }

    @Override
    public <S extends Book> boolean exists(Example<S> example) {
        return bookRepository.exists(example);
    }

    @Override
    public <S extends Book, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return bookRepository.findBy(example, queryFunction);
    }

    @Override
    public <S extends Book> S save(S entity) {
        return bookRepository.save(entity);
    }

    @Override
    public <S extends Book> List<S> saveAll(Iterable<S> entities) {
        return bookRepository.saveAll(entities);
    }

    @Override
    public Optional<Book> findById(Long aLong) {
        return bookRepository.findById(aLong);
    }

    @Override
    public boolean existsById(Long aLong) {
        return bookRepository.existsById(aLong);
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> findAllById(Iterable<Long> longs) {
        return bookRepository.findAllById(longs);
    }

    @Override
    public long count() {
        return bookRepository.count();
    }

    @Override
    public void deleteById(Long aLong) {
        bookRepository.deleteById(aLong);
    }

    @Override
    public void delete(Book entity) {
        bookRepository.delete(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {
        bookRepository.deleteAllById(longs);
    }

    @Override
    public void deleteAll(Iterable<? extends Book> entities) {
        bookRepository.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        bookRepository.deleteAll();
    }

    @Override
    public List<Book> findAll(Sort sort) {
        return bookRepository.findAll(sort);
    }

    @Override
    public Page<Book> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }
}