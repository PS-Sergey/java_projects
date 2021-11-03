package com.opencode.questionare.service;

import com.opencode.questionare.entity.Answer;
import com.opencode.questionare.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnswerService {

    private AnswerRepository answerRepository;

    @Autowired
    public AnswerService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    public void deleteAnswer(Answer answer) {
        answer.setQuestion(null);
        answerRepository.delete(answer);
    }
}
