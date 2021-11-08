package com.opencode.questionare.service;

import com.opencode.questionare.entity.ApplicationForm;
import com.opencode.questionare.entity.UserAnswer;
import com.opencode.questionare.entity.UserApplicationForm;
import com.opencode.questionare.repository.ApplicationFormRepository;
import com.opencode.questionare.repository.UserApplicationFormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViewForUserService {

    private ApplicationFormRepository applicationFormRepository;
    private UserApplicationFormRepository userApplicationFormRepository;

    @Autowired
    public ViewForUserService(ApplicationFormRepository applicationFormRepository, UserApplicationFormRepository userApplicationFormRepository) {
        this.applicationFormRepository = applicationFormRepository;
        this.userApplicationFormRepository = userApplicationFormRepository;
    }

    public List<ApplicationForm> getAllApplicationForms() {
        return applicationFormRepository.findAll();
    }

    public ApplicationForm getApplicationFormById(Long id) {
        return applicationFormRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("not found ApplicationForm with id: " + id));
    }

    public Long saveUserApplicationForm(UserApplicationForm userApplicationForm, String userName) {
        UserApplicationForm userApplicationFormFromDB = findUserApplicationFormByUsernameAndApplicationFormId(userName, userApplicationForm.getApplicationFormId());
        if (userApplicationFormFromDB != null) {
            userApplicationFormRepository.delete(userApplicationFormFromDB);
        }
        userApplicationForm.setUsername(userName);
        for (UserAnswer userAnswer : userApplicationForm.getUserAnswers()) {
            userAnswer.setUserApplicationForm(userApplicationForm);
        }
        return userApplicationFormRepository.save(userApplicationForm).getId();
    }

    private UserApplicationForm findUserApplicationFormByUsernameAndApplicationFormId(String username, Long appId) {
        return userApplicationFormRepository.findUserApplicationFormByUsernameAndApplicationFormId(username, appId);
    }
}
