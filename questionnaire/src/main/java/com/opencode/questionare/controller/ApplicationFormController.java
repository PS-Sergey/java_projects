package com.opencode.questionare.controller;

import com.opencode.questionare.controller.dto.ApplicationFormResponseDTO;
import com.opencode.questionare.controller.dto.QuestionResponseDTO;
import com.opencode.questionare.controller.dto.UserAnswerResponseDTO;
import com.opencode.questionare.entity.*;
import com.opencode.questionare.service.AnswerService;
import com.opencode.questionare.service.ApplicationFormService;
import com.opencode.questionare.service.QuestionService;
import com.opencode.questionare.service.UserApplicationFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/questionnaire")
public class ApplicationFormController {

    private ApplicationFormService applicationFormService;
    private QuestionService questionService;
    private AnswerService answerService;
    private UserApplicationFormService userApplicationFormService;

    @Autowired
    public void setApplicationFormService(ApplicationFormService applicationFormService) {
        this.applicationFormService = applicationFormService;
    }

    @Autowired
    public void setQuestionService(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Autowired
    public void setAnswerService(AnswerService answerService) {
        this.answerService = answerService;
    }

    @Autowired
    public void setUserApplicationFormService(UserApplicationFormService userApplicationFormService) {
        this.userApplicationFormService = userApplicationFormService;
    }

    @PostMapping("/createApplication")
    @ResponseBody
    public Long createApplicationForm(@RequestBody ApplicationForm body) {
        return applicationFormService.saveApplicationForm(body);
    }

    @PutMapping("/updateApplication/{id}")
    @ResponseBody
    public void updateApplication(@PathVariable Long id, @RequestBody ApplicationForm body) {
        ApplicationForm applicationFormFromDb = applicationFormService.getApplicationFormById(body.getId());
        ApplicationForm updateApplicationForm = applicationFormService.createApplicationForm(body);
        for (Question question : applicationFormFromDb.getQuestions()) {
            boolean existQuestion = false;
            for (Question updatedQuestion : updateApplicationForm.getQuestions()) {
                if (question.getId() == updatedQuestion.getId()) {
                    existQuestion = true;
                    for (Answer answer : question.getAnswers()) {
                        boolean existAnswer = false;
                        for (Answer updateAnswer : updatedQuestion.getAnswers()) {
                            if (answer.getId() == updateAnswer.getId()) {
                                existAnswer = true;
                            }
                        }
                        if (!existAnswer) {
                            answerService.deleteAnswer(answer);
                        }
                    }
                }
            }
            if (!existQuestion) {
                questionService.deleteQuestion(question);
            }
        }
        applicationFormService.updateApplicationForm(updateApplicationForm);
    }

    @PostMapping("/delApplication/{id}")
    @ResponseBody
    public void deleteApplicationForm(@PathVariable Long id) {
        applicationFormService.deleteApplicationForm(id);
        userApplicationFormService.deleteAllByApplicationFormId(id);
    }

    @GetMapping("/forms")
    public String allApplicationForms(Model model) {
        model.addAttribute("ApplicationForms", applicationFormService.getAllApplicationForms());
        return "allForms";
    }

    @GetMapping("/updateForms/{id}")
    public String getApplicationByIdUpdate(@PathVariable Long id, Model model) {
        model.addAttribute("ApplicationForm", applicationFormService.getApplicationFormById(id));
        return "updateApplicationForm";
    }

    @GetMapping("/forms/{id}")
    public String getApplicationById(@PathVariable Long id, Model model) {
        model.addAttribute("ApplicationForm", applicationFormService.getApplicationFormById(id));
        return "applicationForm";
    }

    @PostMapping("/saveUserAnswers")
    @ResponseBody
    public void saveUserApplicationForm(@RequestBody UserApplicationForm body, Principal principal) {
        userApplicationFormService.saveUserApplicationForm(body, principal.getName());
    }

    @GetMapping("/userForms/{username}")
    public String getUserApplicationFormsByUsername(@PathVariable String username, Model model) {
        List<Long> ids = userApplicationFormService.findApplicationFormIdByUsername(username);
        List<ApplicationForm> applicationForms = applicationFormService.findApplicationFormsByIdIn(ids);
        model.addAttribute("userForms", applicationForms);
        return "usersForms";
    }

    @GetMapping("/userForms/{username}/{appId}")
    public String getUserAnswers(@PathVariable String username, @PathVariable Long appId, Model model) {
        ApplicationForm applicationForm = applicationFormService.getApplicationFormById(appId);
        UserApplicationForm userApplicationForm = userApplicationFormService.findUserApplicationFormByUsernameAndApplicationFormId(username, appId);
        ApplicationFormResponseDTO applicationFormResponseDTO = new ApplicationFormResponseDTO(applicationForm);
        for (Question question : applicationForm.getQuestions()) {
            QuestionResponseDTO questionResponseDTO = new QuestionResponseDTO(question);
            for (Answer answer : question.getAnswers()) {
                for (UserAnswer userAnswer : userApplicationForm.getUserAnswers()) {
                    if (answer.getId() == userAnswer.getAnswerId()) {
                        UserAnswerResponseDTO userAnswerResponseDTO = new UserAnswerResponseDTO(answer, userAnswer);
                        questionResponseDTO.addAnswer(userAnswerResponseDTO);
                    }
                }
            }
            applicationFormResponseDTO.addQuestion(questionResponseDTO);
        }
        model.addAttribute("userAnswers", applicationFormResponseDTO);
        return "userAnswers";
    }
}
