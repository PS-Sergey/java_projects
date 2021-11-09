package com.opencode.questionare.controller;

import com.opencode.questionare.entity.UserApplicationForm;
import com.opencode.questionare.response.StringResponse;
import com.opencode.questionare.service.ViewForUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/questionnaire/applications")
public class ViewForUserController {

    private ViewForUserService viewForUserService;

    @Autowired
    public ViewForUserController(ViewForUserService viewForUserService) {
        this.viewForUserService = viewForUserService;
    }

    @GetMapping("/forms")
    public String allApplicationForms(Model model) {
        model.addAttribute("ApplicationForms", viewForUserService.getAllApplicationForms());
        return "allForms";
    }

    @GetMapping("/forms/{id}")
    public String getApplicationById(@PathVariable Long id, Model model) {
        model.addAttribute("ApplicationForm", viewForUserService.getApplicationFormById(id));
        return "applicationForm";
    }

    @PostMapping("/saveUserAnswers")
    @ResponseBody
    public ResponseEntity<StringResponse> saveUserApplicationForm(@RequestBody UserApplicationForm body, Principal principal) {
        viewForUserService.saveUserApplicationForm(body, principal.getName());
        ResponseEntity<StringResponse> response = new ResponseEntity<>(new StringResponse("Ответ сохранен"), HttpStatus.OK);
        return response;
    }
}
