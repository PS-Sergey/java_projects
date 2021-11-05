package com.opencode.questionare.controller.dto;

import com.opencode.questionare.entity.ApplicationForm;

import java.util.ArrayList;
import java.util.List;

public class ApplicationFormResponseDTO {

    private Long id;
    private String title;
    private List<QuestionResponseDTO> questions;

    public ApplicationFormResponseDTO(ApplicationForm applicationForm) {
        this.id = applicationForm.getId();
        this.title = applicationForm.getTitle();
    }

    public void addQuestion(QuestionResponseDTO question) {
        if (questions == null) {
            this.questions = new ArrayList<QuestionResponseDTO>();
        }
        this.questions.add(question);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<QuestionResponseDTO> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionResponseDTO> questions) {
        this.questions = questions;
    }
}
