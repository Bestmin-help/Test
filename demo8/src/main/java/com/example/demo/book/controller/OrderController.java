package com.example.demo.book.controller;

import com.example.demo.book.dto.Orders;
import com.example.demo.book.dto.User;
import com.example.demo.book.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // 주문 생성 API (POST /orders/create)
    @PostMapping("/create")
    public Orders createOrder(@RequestBody User user) {
        return orderService.createOrderForUser(user);
    }

    // 특정 사용자의 주문 목록 조회 API (GET /orders/user/{userId})
    @GetMapping("/user/{userId}")
    public List<Orders> getOrdersByUser(@PathVariable Long userId) {
        return orderService.getOrdersForUser(userId);
    }
}
