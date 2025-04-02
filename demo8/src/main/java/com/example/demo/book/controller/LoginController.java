package com.example.demo.book.controller;

import com.example.demo.book.dto.User;
import com.example.demo.book.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    // 기존 /login 경로도 유지
    @GetMapping("/login")
    public String showLoginPage(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "login";  // templates/login.html을 렌더링
    }

    // 로그인 처리 메서드
    @PostMapping("/login")
    public String processLogin(@RequestParam String username,
                               @RequestParam String password,
                               HttpSession session,
                               RedirectAttributes redirectAttributes) {

        // 사용자 확인
        User user = userRepository.findByUsername(username)
                .orElse(null);

        if (user != null && user.getPassword().equals(password)) {
            // 로그인 성공: 세션에 사용자 정보 저장
            session.setAttribute("currentUser", user);
            return "redirect:/book"; // 성공 시 책 목록 페이지로 이동
        } else {
            // 로그인 실패
            redirectAttributes.addFlashAttribute("error", "아이디 또는 비밀번호가 잘못되었습니다.");
            return "redirect:/?error=true";
        }
    }

    // 로그아웃 처리
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}