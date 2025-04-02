package com.example.demo.book.controller;


import org.springframework.ui.Model;
import com.example.demo.book.dto.CartItem;
import com.example.demo.book.service.CartService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CartController {

    @Autowired
    private CartService cartService;



    @GetMapping("/cart")
    public String viewcart(Model model, HttpSession session) {
        Long userId = getUserIdFromSession(session);

        try {
            List<CartItem> cartItems = cartService.getCartItems(userId);

            int totalItems = 0;
            int totalPrice = 0;

            for (CartItem item : cartItems) {
                totalItems += item.getQuantity();
                totalPrice += item.getBook().getPrice() * item.getQuantity();

            }

            int totalAmount = totalPrice;

            model.addAttribute("cartItems", cartItems);
            model.addAttribute("totalItems", totalItems);
            model.addAttribute("totalAmount", totalAmount);


            return "cart";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "장바구니 조회중 오류가 발생했습니다: " + e.getMessage());
            return "error";
        }
    }

        private Long getUserIdFromSession (HttpSession session){
            Long userId = (Long) session.getAttribute("userId");
            if(userId == null){
                userId = 1L;
                session.setAttribute("userId", userId);
            }
            return userId;
        }
    @DeleteMapping("/cart/remove/{id}")
    @ResponseBody
    public Map<String, Object> removeCartItem(@PathVariable Long id) {
        Map<String, Object> res = new HashMap<>();
        try {
            cartService.removeFromCart(id);
            res.put("success", true);
            res.put("message", "항목이 삭제되었습니다.");
        } catch (Exception e) {
            res.put("success", false);
            res.put("message", "삭제 중 오류 발생: " + e.getMessage());
        }
        return res;
    }
    @DeleteMapping("/cart/clear")
    @ResponseBody
    public Map<String, Object> clearCart(HttpSession session) {
        Map<String, Object> res = new HashMap<>();
        try {
            Long userId = (Long) session.getAttribute("userId");
            if (userId == null) userId = 1L;
            cartService.clearCart(userId);
            res.put("success", true);
            res.put("message", "장바구니를 비웠습니다.");
        } catch (Exception e) {
            res.put("success", false);
            res.put("message", "전체 삭제 중 오류 발생: " + e.getMessage());
        }
        return res;
    }




}
