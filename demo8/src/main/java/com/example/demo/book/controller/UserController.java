package com.example.demo.book.controller;


import com.example.demo.book.dto.User;
import com.example.demo.book.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("")
    public String listUsers(Model model) {
        model.addAttribute("userList", userService.getAllUsers());
        return "user";
    }

    @PostMapping("register")
    public String addUser(@ModelAttribute User user) {
        userService.saveUser(user);
        return "redirect:/user";
    }

    @GetMapping("delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/user";
    }


        // 사용자 등록, 삭제 등 추가 메서드...
    }
