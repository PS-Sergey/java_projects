package com.opencode.questionare.controller;

import com.opencode.questionare.dto.ApplicationFormResponseDTO;
import com.opencode.questionare.entity.ApplicationForm;
import com.opencode.questionare.service.ShowUserAnswersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/questionnaire/showAnswers")
public class ShowUserAnswersController {

    private ShowUserAnswersService showUserAnswersService;

    @Autowired
    public ShowUserAnswersController(ShowUserAnswersService showUserAnswersService) {
        this.showUserAnswersService = showUserAnswersService;
    }

    @GetMapping("/users")
    public String getUsers(Model model){
        model.addAttribute("users", showUserAnswersService.getAllUsernames());
        return "users";
    }

    @GetMapping("/{username}")
    public String getUserApplicationFormsByUsername(@PathVariable String username, Model model) {
        List<ApplicationForm> userForms = showUserAnswersService.getUserApplicationFormsByUsername(username);
        model.addAttribute("userForms", userForms);
        return "usersForms";
    }

    @GetMapping("/{username}/{appId}")
    public String getUserAnswers(@PathVariable String username, @PathVariable Long appId, Model model) {
        ApplicationFormResponseDTO applicationFormResponseDTO = showUserAnswersService.getUserAnswers(username, appId);
        model.addAttribute("userAnswers", applicationFormResponseDTO);
        return "userAnswers";
    }
}
