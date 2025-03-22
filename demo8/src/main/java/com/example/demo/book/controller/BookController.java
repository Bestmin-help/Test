package com.example.demo.book.controller;

import com.example.demo.book.dto.Book;
import com.example.demo.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BookController {

    @Autowired
    BookService bookService;

    // 책 목록
    @GetMapping("/book")
    public String book(Model model) {

        List<Book> bookList = bookService.list();
        model.addAttribute("bookList", bookList);

        return "book";
    }

    // 등록 폼
    @GetMapping("/book/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("book", new Book());
        return "register"; // 촤종 register.html로 이동
    }

    @PostMapping("/book/register")
    public String registerbook(@ModelAttribute Book book) {
        bookService.register(book);

        return "redirect:/book";

    }

    @GetMapping("/book/delete/{bookid}")
    public String deleteBook(@PathVariable int bookid) {
        bookService.delete(bookid);
        return "redirect:/book";
    }

    @GetMapping("/book/modify/{bookid}")
    public String showModifyForm(@PathVariable int bookid, Model model) {
        Book book = bookService.findById(bookid);
        model.addAttribute("book", book);
        return "modify";
    }

    @PostMapping("/book/modify/{bookid}")
    public String modifybook(@PathVariable int bookid, @ModelAttribute Book book) {
        book.setBookid(bookid);
        bookService.update(book);

        return "redirect:/book";

    }

    @PostMapping("/book/modify")
    public String modifyBook(@ModelAttribute Book book) {
        bookService.update(book);
        return "redirect:/book";
    }

    @DeleteMapping("/book/delete/{bookid}")
    @ResponseBody
    public ResponseEntity<String> ajaxDeleteBook(@PathVariable int bookid) {
        try {
            bookService.delete(bookid);
            return ResponseEntity.ok("삭제 성공");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("삭제 실패: " + e.getMessage());
        }
    }
}