package com.example.demo.Controller;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController {

        @GetMapping("/")
    public String test(Model model){

            model.addAttribute("드디어완성했다씨벌","쳐눌러봐");
            return "/hello";
        }

}
