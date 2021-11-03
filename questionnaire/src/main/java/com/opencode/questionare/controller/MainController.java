package com.opencode.questionare.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/questionnaire/createForm")
    public String createForm() {
        return "createNewApplicationForm";
    }
}
