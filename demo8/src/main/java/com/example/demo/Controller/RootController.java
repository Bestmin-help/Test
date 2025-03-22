package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class RootController {

    @GetMapping("/")
    public String test(Model model) {

        model.addAttribute("테스트를 시작하지", "눌러보거라");
        return "/hello";
    }
}
