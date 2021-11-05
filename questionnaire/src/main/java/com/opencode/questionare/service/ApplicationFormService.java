package com.opencode.questionare.service;

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

    public ApplicationForm createApplicationForm(ApplicationForm applicationForm) {
        for (Question question : applicationForm.getQuestions()) {
            for (Answer answer : question.getAnswers()) {
                answer.setQuestion(question);
            }
            question.setApplicationForm(applicationForm);
        }
        return  applicationForm;
    }

    public Long saveApplicationForm(ApplicationForm applicationForm) {
        return applicationFormRepository.save(createApplicationForm(applicationForm)).getId();
    }

    public List<ApplicationForm> getAllApplicationForms() {
        return applicationFormRepository.findAll();
    }

    public ApplicationForm getApplicationFormById(Long id) {
        return applicationFormRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("not found Form with id: " + id));
    }

    public Long updateApplicationForm(ApplicationForm applicationForm) {
        return applicationFormRepository.save(applicationForm).getId();
    }

    public void deleteApplicationForm(Long id) {
        applicationFormRepository.deleteById(id);
    }

    public List<ApplicationForm> findApplicationFormsByIdIn(List<Long> ids) {
        return applicationFormRepository.findApplicationFormsByIdIn(ids);
    }
}
