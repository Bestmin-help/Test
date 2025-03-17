package com.example.demo.book.controller;

import com.example.demo.book.dto.Book;
import com.example.demo.book.service.BookService;
import org.springframework.beans.   factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;

@Controller
public class BookController {

            @Autowired
            BookService bookService;

            @GetMapping("/pedro")
            public String book(Model model) {

                List<Book> bookList = bookService.getAllBooks();
                model.addAttribute("bookList", bookList);
                model.addAttribute("book", new Book());
                return "pedro";
            }

            @PostMapping("/pedro/register")
                    public String register(@ModelAttribute Book book){
                bookService.registerBook(book);
                return "redirect:pedro";
                }

            @PostMapping("/pedro/modify")
            public String modify(@ModelAttribute Book book){
                bookService.modifyBook(book);
                return "redirect:pedro";
            }

            @GetMapping("/pedro/modify/{bookid}")
            public String showModifyForm(@PathVariable int bookid, Model model) {
                Book book = bookService.findBookById(bookid);
                model.addAttribute("book", book);
                model.addAttribute("book", new Book());
                return "modify";
            }
            @GetMapping("/pedro/delete/{bookid}")
            public String deleteBook(@PathVariable int bookid) {
                 Book book = bookService.findBookById(bookid);
                bookService.deleteBook(book);
                return "redirect:/pedro";
    }




            }

