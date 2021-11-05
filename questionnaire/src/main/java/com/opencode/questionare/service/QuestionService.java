package com.opencode.questionare.service;

import com.opencode.questionare.entity.Question;
import com.opencode.questionare.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {

    private QuestionRepository questionRepository;

    @Autowired
    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public void deleteQuestion(Question question) {
        question.setApplicationForm(null);
        questionRepository.delete(question);
    }
}
