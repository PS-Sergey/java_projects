package com.opencode.questionare.controller.dto;

import com.opencode.questionare.entity.ApplicationForm;

public class QuestionResponseDTO {

    private Long question_id;
    private String text;
    private String response;
    //private ApplicationForm applicationForm;

    public QuestionResponseDTO(Long question_id, String text, String response, ApplicationForm applicationForm) {
        this.question_id = question_id;
        this.text = text;
        this.response = response;
        //this.applicationForm = applicationForm;
    }

    public QuestionResponseDTO() {
    }

    public Long getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(Long question_id) {
        this.question_id = question_id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

//    public ApplicationForm getApplicationForm() {
//        return applicationForm;
//    }
//
//    public void setApplicationForm(ApplicationForm applicationForm) {
//        this.applicationForm = applicationForm;
//    }
}
