package com.opencode.questionare.controller.dto;

import java.util.List;

public class QuestionRequestDTO {

    private Long id;
    private String questionText;
    private List<AnswerRequestDTO> answers;

//    public QuestionRequestDTO(String questionText, List<AnswerRequestDTO> answers) {
//        this.questionText = questionText;
//        this.answers = answers;
//    }
//
//    public QuestionRequestDTO() {
//    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public List<AnswerRequestDTO> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerRequestDTO> answers) {
        this.answers = answers;
    }

    @Override
    public String toString() {
        return "QuestionRequestDTO{" +
                "questionText='" + questionText + '\'' +
                '}';
    }
}
