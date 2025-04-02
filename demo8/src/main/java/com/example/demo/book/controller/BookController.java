package com.example.demo.book.controller;

import com.example.demo.book.dto.Book;
import com.example.demo.book.service.BookService;
import com.example.demo.book.service.CartService;
import com.fasterxml.jackson.databind.DatabindContext;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;
    @Autowired
    private CartService cartService;

    // 책 목록
    @GetMapping("")
    public String book(Model model) {
        List<Book> bookList = bookService.list();
        model.addAttribute("bookList", bookList);
        return "book";
    }

    // 등록 폼
    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("book", new Book());
        return "register";
    }

    // 책 등록 처리
    @PostMapping("/register")
    public String registerBook(@ModelAttribute Book book) {
        bookService.register(book);
        return "redirect:/book";
    }

    // 수정 폼
    @GetMapping("/modify/{bookid}")
    public String showModifyForm(@PathVariable int bookid, Model model) {
        Book book = bookService.findById(bookid);
        model.addAttribute("book", book);
        return "modify";
    }

    // 책 수정 처리 (PathVariable 사용)
    @PostMapping("/modify/{bookid}")
    public String modifyBook(@PathVariable int bookid, @ModelAttribute Book book) {
        book.setBookid(bookid);
        bookService.update(book);
        return "redirect:/book";
    }

    // 책 수정 처리 (대체 방법)
    @PostMapping("/modify")
    public String modifyBookAlternate(@ModelAttribute Book book) {
        bookService.update(book);
        return "redirect:/book";
    }

    // 책 삭제 (Ajax)
    @DeleteMapping("/delete/{bookid}")
    @ResponseBody
    public Map<String, Object> deleteBook(@PathVariable int bookid) {
        Map<String, Object> response = new HashMap<>();

        try {
            bookService.delete(bookid);
            response.put("success", true);
            response.put("message", "책이 성공적으로 삭제되었습니다.");
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "삭제 실패: " + e.getMessage());
        }

        return response;
    }

    // 기존 삭제 메서드 (폼 제출 방식)
    @PostMapping("/delete/{bookid}")
    public String deleteBookForm(@PathVariable int bookid, RedirectAttributes redirectAttributes) {
        try {
            bookService.delete(bookid);
            redirectAttributes.addFlashAttribute("successMessage", "책이 성공적으로 삭제되었습니다.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "삭제 실패: " + e.getMessage());
        }
        return "redirect:/book";
    }

    // 책 검색 (Ajax)
    @GetMapping("/search")
    @ResponseBody
    public List<Book> searchBook(
            @RequestParam(required = false) String searchType,
            @RequestParam(required = false) String keyword) {

//        System.out.println("검색유형: " + searchType);
//        System.out.println("검색어: " + keyword);

        if ((searchType == null || searchType.isEmpty()) || "all".equals(searchType)
                && (keyword == null || keyword.isEmpty())) {
            return bookService.list();
        }

        switch (searchType) {
            case "bookname":
                return bookService.searchByBookname(keyword);
            case "publisher":
                return bookService.searchByPublisher(keyword);
            default:
                return bookService.searchByAll(keyword);
        }
    }

    @PostMapping("/add/{bookid}")
    @ResponseBody
    public Map<String, Object> addBook(@PathVariable int bookid, HttpSession session) {
        Map<String, Object> response = new HashMap<>();

        Long userId = (Long) session.getAttribute("userId");
        if(userId == null) {
            userId = 1L;
            session.setAttribute("userId", userId);

        }


        try {
            cartService.addToCart((long) bookid, userId);
            response.put("success", true);
            response.put("message", "주문 목록에 추가되었습니다.");
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "처리 중 오류가 발생했습니다.: " + e.getMessage());
        }

        return response;
    }
}


