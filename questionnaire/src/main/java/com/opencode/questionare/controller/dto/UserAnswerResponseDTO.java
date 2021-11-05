package com.opencode.questionare.controller.dto;

import com.opencode.questionare.entity.Answer;
import com.opencode.questionare.entity.UserAnswer;

public class UserAnswerResponseDTO {

    private Long id;
    private String answerText;
    private Boolean answerCheck;
    private Boolean trueCheck;

    public UserAnswerResponseDTO(Answer answer, UserAnswer userAnswer) {
        this.id = answer.getId();
        this.answerText = answer.getAnswerText();
        this.answerCheck = userAnswer.isUserAnswerCheck();
        this.trueCheck = answer.getAnswerCheck() == userAnswer.isUserAnswerCheck();
    }

    public Boolean getTrueCheck() {
        return trueCheck;
    }

    public void setTrueCheck(Boolean trueCheck) {
        this.trueCheck = trueCheck;
    }

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
}
