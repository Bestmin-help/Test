package com.example.demo.book.service;

import com.example.demo.book.dto.Book;
import com.example.demo.book.dto.CartItem;
import com.example.demo.book.repository.BookRepository;
import com.example.demo.book.repository.CartRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private BookRepository bookRepository;

    @Transactional
    public void addToCart(Long bookId, Long userId){
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found: " + bookId));

        CartItem cartItem = new CartItem();
        cartItem.setBook(book);
        cartItem.setQuantity(1);
        cartItem.setUserId(userId);

        cartRepository.save(cartItem);
    }
    public List<CartItem> getCartItems(Long userId) {
        return cartRepository.findByUserId(userId);
    }

    // 장바구니 항목 삭제
    @Transactional
    public void removeFromCart(Long cartItemId) {
        cartRepository.deleteById(cartItemId);
    }

    // 장바구니 비우기
    @Transactional
    public void clearCart(Long userId) {
        List<CartItem> userItems = cartRepository.findByUserId(userId);
        cartRepository.deleteAll(userItems);
    }
}





