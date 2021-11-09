package com.opencode.questionare.service;

import com.opencode.questionare.entity.ApplicationForm;
import com.opencode.questionare.entity.UserAnswer;
import com.opencode.questionare.entity.UserApplicationForm;
import com.opencode.questionare.repository.ApplicationFormRepository;
import com.opencode.questionare.repository.UserApplicationFormRepository;
import com.opencode.questionare.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViewForUserService {

    private ApplicationFormRepository applicationFormRepository;
    private UserApplicationFormRepository userApplicationFormRepository;
    private UserRepository userRepository;

    @Autowired
    public ViewForUserService(ApplicationFormRepository applicationFormRepository, UserApplicationFormRepository userApplicationFormRepository,
                              UserRepository userRepository) {
        this.applicationFormRepository = applicationFormRepository;
        this.userApplicationFormRepository = userApplicationFormRepository;
        this.userRepository = userRepository;
    }

    public List<ApplicationForm> getAllApplicationForms() {
        return applicationFormRepository.findAll();
    }

    public ApplicationForm getApplicationFormById(Long id) {
        return applicationFormRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("not found ApplicationForm with id: " + id));
    }

    public Long saveUserApplicationForm(UserApplicationForm userApplicationForm, String userName) {
        Long userId = userRepository.findUserIdByUsername(userName);
        UserApplicationForm userApplicationFormFromDB = findUserApplicationFormByUserIdAndApplicationFormId(userId, userApplicationForm.getApplicationFormId());
        if (userApplicationFormFromDB != null) {
            userApplicationFormRepository.delete(userApplicationFormFromDB);
        }
        userApplicationForm.setUserId(userId);
        for (UserAnswer userAnswer : userApplicationForm.getUserAnswers()) {
            userAnswer.setUserApplicationForm(userApplicationForm);
        }
        return userApplicationFormRepository.save(userApplicationForm).getId();
    }

    private UserApplicationForm findUserApplicationFormByUserIdAndApplicationFormId(Long userId, Long appId) {
        return userApplicationFormRepository.findUserApplicationFormByUserIdAndApplicationFormId(userId, appId);
    }
}
