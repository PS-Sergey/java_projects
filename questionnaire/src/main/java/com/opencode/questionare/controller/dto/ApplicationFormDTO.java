package com.opencode.questionare.controller.dto;

import java.util.List;

public class ApplicationFormDTO {

    private Long id;
    private String title;
    private List<QuestionRequestDTO> questions;

//    public ApplicationFormDTO(String title, List<QuestionRequestDTO> questions) {
//        this.title = title;
//        this.questions = questions;
//    }
//
//    public ApplicationFormDTO() {
//    }


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

    public List<QuestionRequestDTO> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionRequestDTO> questions) {
        this.questions = questions;
    }

    @Override
    public String toString() {
        return "ApplicationFormDTO{" +
                "title='" + title + '\'' +
                '}';
    }
}
