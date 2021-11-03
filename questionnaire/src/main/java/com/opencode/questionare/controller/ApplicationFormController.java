package com.opencode.questionare.controller;

import com.opencode.questionare.controller.dto.ApplicationFormDTO;
import com.opencode.questionare.entity.Answer;
import com.opencode.questionare.entity.ApplicationForm;
import com.opencode.questionare.entity.Question;
import com.opencode.questionare.service.AnswerService;
import com.opencode.questionare.service.ApplicationFormService;
import com.opencode.questionare.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
@Controller
@RequestMapping("/questionnaire")
public class ApplicationFormController {

    @Autowired
    private ApplicationFormService applicationFormService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private AnswerService answerService;

    @PostMapping("/createApplication")
    @ResponseBody
    public Long createApplicationForm(@RequestBody ApplicationFormDTO body) {
//        ApplicationForm applicationForm = new ApplicationForm(body.getTitle());
//        for (QuestionRequestDTO questionRequestDTO : body.getQuestions()) {
//            Question question = new Question(questionRequestDTO.getQuestionText());
//            for (AnswerRequestDTO answerRequestDTO : questionRequestDTO.getAnswers()) {
//                Answer answer = new Answer(answerRequestDTO.getAnswerText(), answerRequestDTO.getAnswerCheck());
//                question.addAnswer(answer);
//            }
//            applicationForm.addQuestion(question);
//        }
        return applicationFormService.saveApplicationForm(body);
    }

    @GetMapping("/applications")
    @ResponseBody
    public List<ApplicationForm> findAllForms() {
        return applicationFormService.getAllApplicationForms();
    }

    @GetMapping("/application/{id}")
    @ResponseBody
    public ApplicationForm getApplicationById(@PathVariable Long id) {
        return applicationFormService.getApplicationFormById(id);
    }

    @PutMapping("/updateApplication/{id}")
    @ResponseBody
    public void updateApplication(@PathVariable Long id, @RequestBody ApplicationFormDTO body) {
        ApplicationForm applicationForm = applicationFormService.getApplicationFormById(body.getId());
        ApplicationForm updateApplicationForm = applicationFormService.createApplicationForm(body);
        for (Question question : applicationForm.getQuestions()) {
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
//
//    @PostMapping("/new")
//    public String create(@ModelAttribute("ApplicationForm") ApplicationForm applicationForm) {
//        applicationFormService.createApplicationForm(applicationForm);
//        return "index";
//    }

}
