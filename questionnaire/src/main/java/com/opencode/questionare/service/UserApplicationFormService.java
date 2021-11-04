package com.opencode.questionare.service;

import com.opencode.questionare.entity.UserAnswer;
import com.opencode.questionare.entity.UserApplicationForm;
import com.opencode.questionare.repository.UserApplicationFormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserApplicationFormService {

    UserApplicationFormRepository userApplicationFormRepository;

    @Autowired
    public void setUserApplicationFormRepository(UserApplicationFormRepository userApplicationFormRepository) {
        this.userApplicationFormRepository = userApplicationFormRepository;
    }

    public Long saveUserApplicationForm(UserApplicationForm userApplicationForm, String userName) {
        userApplicationForm.setUsername(userName);
        for (UserAnswer userAnswer : userApplicationForm.getUserAnswers()) {
            userAnswer.setUserApplicationForm(userApplicationForm);
        }
        return userApplicationFormRepository.save(userApplicationForm).getId();
    }
}
