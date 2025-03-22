package com.example.demo.book.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // ID 자동 증가 설정
    private int bookid;  // int 타입 사용
    private String bookname;
    private String publisher;
    private int price;

    // 생성자, getter, setter, toString 등 필요한 메서드들 자동 생성 가능
}
