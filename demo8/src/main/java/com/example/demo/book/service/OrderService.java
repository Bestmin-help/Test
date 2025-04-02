package com.example.demo.book.service;

import com.example.demo.book.dto.Orders;
import com.example.demo.book.dto.User;
import com.example.demo.book.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    // 사용자의 주문 생성
    public Orders createOrderForUser(User user) {
        Orders order = new Orders();
        order.setUser(user);
        order.setOrdersDate(new Date());
        return orderRepository.save(order);
    }

    // 특정 사용자의 주문 목록 조회
    public List<Orders> getOrdersForUser(Long userId) {
        return orderRepository.findByUserId(userId);
    }
}
