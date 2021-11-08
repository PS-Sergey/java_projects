package com.opencode.questionare.controller;

import com.opencode.questionare.entity.ApplicationForm;
import com.opencode.questionare.response.StringResponse;
import com.opencode.questionare.service.FormConstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/questionnaire/constructor")
public class FormConstructorController {

    private FormConstructorService formConstructorService;

    @Autowired
    public FormConstructorController(FormConstructorService formConstructorService) {
        this.formConstructorService = formConstructorService;
    }

    @PostMapping("/createApplicationForm")
    @ResponseBody
    public ResponseEntity<StringResponse> createApplicationForm(@RequestBody ApplicationForm body) {
        Long id = formConstructorService.saveApplicationForm(body);
        ResponseEntity<StringResponse> response = new ResponseEntity<>(new StringResponse(String.format("Анкета успешно сохранена, id = %s", id)), HttpStatus.OK);
        return response;
    }

    @PutMapping("/updateApplication/{id}")
    @ResponseBody
    public ResponseEntity<StringResponse> updateApplication(@PathVariable Long id, @RequestBody ApplicationForm body) {
        formConstructorService.updateApplicationForm(id, body);
        ResponseEntity<StringResponse> response = new ResponseEntity<>(new StringResponse(String.format("Анкета с id = %s успешно обновлена", id)), HttpStatus.OK);
        return response;
    }

    @PostMapping("/delApplication/{id}")
    @ResponseBody
    public ResponseEntity<StringResponse> deleteApplicationForm(@PathVariable Long id) {
        formConstructorService.deleteApplicationFormById(id);
        ResponseEntity<StringResponse> response = new ResponseEntity<>(new StringResponse(String.format("Анкета с id = %s успешно удалена", id)), HttpStatus.OK);
        return response;
    }

    @GetMapping("/createForm")
    public String createForm() {
        return "createNewApplicationForm";
    }

    @GetMapping("/updateForms/{id}")
    public String getApplicationFormForUpdateById(@PathVariable Long id, Model model) {
        model.addAttribute("ApplicationForm", formConstructorService.getApplicationFormById(id));
        return "updateApplicationForm";
    }
}
