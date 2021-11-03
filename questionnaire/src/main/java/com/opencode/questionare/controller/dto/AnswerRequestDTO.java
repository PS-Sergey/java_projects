package com.opencode.questionare.controller.dto;

public class AnswerRequestDTO {

    private Long id;
    private String answerText;
    private Boolean answerCheck;

//    public AnswerRequestDTO(String answerText, Boolean answerCheck) {
//        this.answerText = answerText;
//        this.answerCheck = answerCheck;
//    }
//
//    public AnswerRequestDTO() {
//    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public Boolean getAnswerCheck() {
        return answerCheck;
    }

    public void setAnswerCheck(Boolean answerCheck) {
        this.answerCheck = answerCheck;
    }

    @Override
    public String toString() {
        return "AnswerRequestDTO{" +
                "answerText='" + answerText + '\'' +
                ", answerCheck=" + answerCheck +
                '}';
    }
}
