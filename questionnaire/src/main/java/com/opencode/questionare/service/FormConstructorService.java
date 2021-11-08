package com.opencode.questionare.service;

import com.opencode.questionare.entity.Answer;
import com.opencode.questionare.entity.ApplicationForm;
import com.opencode.questionare.entity.Question;
import com.opencode.questionare.repository.AnswerRepository;
import com.opencode.questionare.repository.ApplicationFormRepository;
import com.opencode.questionare.repository.QuestionRepository;
import com.opencode.questionare.repository.UserApplicationFormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FormConstructorService {

    private ApplicationFormRepository applicationFormRepository;
    private QuestionRepository questionRepository;
    private AnswerRepository answerRepository;
    private UserApplicationFormRepository userApplicationFormRepository;

    @Autowired
    public FormConstructorService(ApplicationFormRepository applicationFormRepository, QuestionRepository questionRepository,
                                  AnswerRepository answerRepository, UserApplicationFormRepository userApplicationFormRepository) {
        this.applicationFormRepository = applicationFormRepository;
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.userApplicationFormRepository = userApplicationFormRepository;
    }

    public ApplicationForm getApplicationFormById(Long id) {
        return applicationFormRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("not found ApplicationForm with id: " + id));
    }

    public Long saveApplicationForm(ApplicationForm applicationForm) {
        for (Question question : applicationForm.getQuestions()) {
            question.setApplicationForm(applicationForm);
            for (Answer answer : question.getAnswers()) {
                answer.setQuestion(question);
            }
        }
        return applicationFormRepository.save(applicationForm).getId();
    }

    public void updateApplicationForm(Long id, ApplicationForm updateApplicationForm) {
        ApplicationForm formFromDB = getApplicationFormById(id);
        for (Question questionFromDB : formFromDB.getQuestions()) {
            boolean existQuestion = false;
            for (Question updatedQuestion : updateApplicationForm.getQuestions()) {
                if (questionFromDB.getId() == updatedQuestion.getId()) {
                    existQuestion = true;
                    for (Answer answerFromDB : questionFromDB.getAnswers()) {
                        boolean existAnswer = false;
                        for (Answer updateAnswer : updatedQuestion.getAnswers()) {
                            if (answerFromDB.getId() == updateAnswer.getId()) {
                                existAnswer = true;
                            }
                        }
                        if (!existAnswer) {
                            deleteAnswer(answerFromDB);
                        }
                    }
                }
            }
            if (!existQuestion) {
                deleteQuestion(questionFromDB);
            }
        }
        saveApplicationForm(updateApplicationForm);
    }

    private void deleteAnswer(Answer answer) {
        answer.setQuestion(null);
        answerRepository.delete(answer);
    }

    private void deleteQuestion(Question question) {
        question.setApplicationForm(null);
        questionRepository.delete(question);
    }

    @Transactional
    public void deleteApplicationFormById(Long id) {
        applicationFormRepository.deleteById(id);
        userApplicationFormRepository.deleteAllByApplicationFormId(id);
    }


}
