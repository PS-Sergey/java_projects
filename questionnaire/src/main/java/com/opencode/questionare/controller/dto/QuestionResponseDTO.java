package com.opencode.questionare.controller.dto;

import com.opencode.questionare.entity.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionResponseDTO {

    private Long id;
    private String questionText;
    private List<UserAnswerResponseDTO> answers;

    public QuestionResponseDTO(Question question) {
        this.id = question.getId();
        this.questionText = question.getQuestionText();
    }

    public void addAnswer(UserAnswerResponseDTO answer) {
        if (this.answers == null) {
            this.answers = new ArrayList<UserAnswerResponseDTO>();
        }
        this.answers.add(answer);
    }

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

    public List<UserAnswerResponseDTO> getAnswers() {
        return answers;
    }

    public void setAnswers(List<UserAnswerResponseDTO> answers) {
        this.answers = answers;
    }
}
