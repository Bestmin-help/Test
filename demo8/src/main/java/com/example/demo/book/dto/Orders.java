package com.example.demo.book.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id") // 실제 DB 컬럼명
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "orders_date")
    private Date ordersDate;

    // User 객체를 받아서 userId와 username을 설정하는 메서드
    public void setUser(User user) {
        if (user != null) {
            this.userId = user.getId();
            this.username = user.getUsername();
        }
    }
}
