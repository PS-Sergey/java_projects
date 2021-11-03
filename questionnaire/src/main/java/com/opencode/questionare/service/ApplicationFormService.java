package com.opencode.questionare.service;

import com.opencode.questionare.controller.dto.AnswerRequestDTO;
import com.opencode.questionare.controller.dto.ApplicationFormDTO;
import com.opencode.questionare.controller.dto.QuestionRequestDTO;
import com.opencode.questionare.entity.Answer;
import com.opencode.questionare.entity.ApplicationForm;
import com.opencode.questionare.entity.Question;
import com.opencode.questionare.repository.ApplicationFormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationFormService {

    private ApplicationFormRepository applicationFormRepository;

    @Autowired
    public ApplicationFormService(ApplicationFormRepository applicationFormRepository) {
        this.applicationFormRepository = applicationFormRepository;
    }

    public ApplicationForm createApplicationForm(ApplicationFormDTO applicationFormDTO) {
        ApplicationForm applicationForm = new ApplicationForm(applicationFormDTO.getTitle());
        applicationForm.setId(applicationFormDTO.getId());
        for (QuestionRequestDTO questionRequestDTO : applicationFormDTO.getQuestions()) {
            Question question = new Question(questionRequestDTO.getQuestionText());
            question.setId(questionRequestDTO.getId());
            for (AnswerRequestDTO answerRequestDTO : questionRequestDTO.getAnswers()) {
                Answer answer = new Answer(answerRequestDTO.getAnswerText(), answerRequestDTO.getAnswerCheck());
                answer.setId(answerRequestDTO.getId());
                question.addAnswer(answer);
            }
            applicationForm.addQuestion(question);
        }
        return  applicationForm;
    }

    public Long saveApplicationForm(ApplicationFormDTO applicationFormDTO) {
//        ApplicationForm applicationForm = new ApplicationForm(applicationFormDTO.getTitle());
//        applicationForm.setId(applicationFormDTO.getId());
//        for (QuestionRequestDTO questionRequestDTO : applicationFormDTO.getQuestions()) {
//            Question question = new Question(questionRequestDTO.getQuestionText());
//            for (AnswerRequestDTO answerRequestDTO : questionRequestDTO.getAnswers()) {
//                Answer answer = new Answer(answerRequestDTO.getAnswerText(), answerRequestDTO.getAnswerCheck());
//                question.addAnswer(answer);
//            }
//            applicationForm.addQuestion(question);
//        }
        return applicationFormRepository.save(createApplicationForm(applicationFormDTO)).getId();
    }

    public List<ApplicationForm> getAllApplicationForms() {
        return applicationFormRepository.findAll();
    }

    public ApplicationForm getApplicationFormById(Long id) {
        return applicationFormRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("not found Form with id: " + id));
    }

    public Long updateApplicationForm(ApplicationForm applicationForm) {
//        ApplicationForm applicationForm = getApplicationFormById(applicationFormDTO.getId());
//        ApplicationForm updateApplicationForm = createApplicationForm(applicationFormDTO);
//        for (Question question : applicationForm.getQuestions()) {
//            boolean existQuestion = false;
//            for (Question updatedQuestion : updateApplicationForm.getQuestions()) {
//                if (question.getId() == updatedQuestion.getId()) {
//                    existQuestion = true;
//                    for (Answer answer : question.getAnswer()) {
//                        boolean existAnswer = false;
//                        for (Answer updateAnswer : updatedQuestion.getAnswer()) {
//                            if (answer.getId() == updateAnswer.getId()) {
//                                existAnswer = true;
//                            }
//                        }
//                        if (!existAnswer) {
//                            //Удалить ответ
//                        }
//                    }
//                }
//            }
//            if (!existQuestion) {
//                //Удалить вопрос
//            }
//        }
//        return saveApplicationForm(applicationFormDTO);
        return applicationFormRepository.save(applicationForm).getId();
    }

    public void deleteApplicationForm(Long id) {
        applicationFormRepository.deleteById(id);
    }
}
