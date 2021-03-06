package com.emreturgutce.bookcase.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping
    public String index(Model model) {
        model.addAttribute("name", "Emre");
        return "index";
    }

    @GetMapping("/create-book")
    public String createBook(Model model) {
        return "create";
    }
}
