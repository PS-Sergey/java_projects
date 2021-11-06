package com.opencode.questionare.service;

import com.opencode.questionare.entity.UserAnswer;
import com.opencode.questionare.entity.UserApplicationForm;
import com.opencode.questionare.repository.UserApplicationFormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserApplicationFormService {

    UserApplicationFormRepository userApplicationFormRepository;

    @Autowired
    public void setUserApplicationFormRepository(UserApplicationFormRepository userApplicationFormRepository) {
        this.userApplicationFormRepository = userApplicationFormRepository;
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

    public List<Long> findApplicationFormIdByUsername(String username) {
        return userApplicationFormRepository.findApplicationFormIdByUsername(username);
    }

    public UserApplicationForm findUserApplicationFormByUsernameAndApplicationFormId(String username, Long appId) {
        return userApplicationFormRepository.findUserApplicationFormByUsernameAndApplicationFormId(username, appId);
    }

    @Transactional
    public void deleteAllByApplicationFormId(Long applicationFormId) {
        userApplicationFormRepository.deleteAllByApplicationFormId(applicationFormId);
    }
}
