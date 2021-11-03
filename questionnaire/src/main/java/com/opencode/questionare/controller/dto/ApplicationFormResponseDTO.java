package com.opencode.questionare.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.opencode.questionare.entity.Question;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ApplicationFormResponseDTO {

    private Long id;
    private String title;
    private Question[] questions;

    public ApplicationFormResponseDTO(Long id, String title, Question[] questions) {
        this.id = id;
        this.title = title;
        this.questions = questions;
    }

    public ApplicationFormResponseDTO() {
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

    public Question[] getQuestions() {
        return questions;
    }

    public void setQuestions(Question[] questions) {
        this.questions = questions;
    }
}
