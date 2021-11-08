package com.opencode.questionare.service;

import com.opencode.questionare.dto.ApplicationFormResponseDTO;
import com.opencode.questionare.dto.QuestionResponseDTO;
import com.opencode.questionare.dto.UserAnswerResponseDTO;
import com.opencode.questionare.entity.*;
import com.opencode.questionare.repository.ApplicationFormRepository;
import com.opencode.questionare.repository.UserApplicationFormRepository;
import com.opencode.questionare.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShowUserAnswersService {

    private UserRepository userRepository;
    private UserApplicationFormRepository userApplicationFormRepository;
    private ApplicationFormRepository applicationFormRepository;

    @Autowired
    public ShowUserAnswersService(UserRepository userRepository, UserApplicationFormRepository userApplicationFormRepository,
                                  ApplicationFormRepository applicationFormRepository) {
        this.userRepository = userRepository;
        this.userApplicationFormRepository = userApplicationFormRepository;
        this.applicationFormRepository = applicationFormRepository;
    }

    public List<String> getAllUsernames() {
        List<String> usernames = new ArrayList<>();
        List<User> users = userRepository.findAll();
        for (User user : users) {
            usernames.add(user.getUsername());
        }
        return usernames;
    }

    public List<ApplicationForm> getUserApplicationFormsByUsername(String username) {
        List<Long> ids = userApplicationFormRepository.findApplicationFormIdByUsername(username);
        List<ApplicationForm> applicationForms = applicationFormRepository.findApplicationFormsByIdIn(ids);
        return applicationForms;
    }

    public ApplicationFormResponseDTO getUserAnswers(String username, Long appId) {
        ApplicationForm applicationForm = getApplicationFormById(appId);
        UserApplicationForm userApplicationForm = findUserApplicationFormByUsernameAndApplicationFormId(username, appId);
        ApplicationFormResponseDTO applicationFormResponseDTO = new ApplicationFormResponseDTO(applicationForm);
        for (Question question : applicationForm.getQuestions()) {
            QuestionResponseDTO questionResponseDTO = new QuestionResponseDTO(question);
            for (Answer answer : question.getAnswers()) {
                for (UserAnswer userAnswer : userApplicationForm.getUserAnswers()) {
                    if (answer.getId() == userAnswer.getAnswerId()) {
                        UserAnswerResponseDTO userAnswerResponseDTO = new UserAnswerResponseDTO(answer, userAnswer);
                        questionResponseDTO.addAnswer(userAnswerResponseDTO);
                    }
                }
            }
            applicationFormResponseDTO.addQuestion(questionResponseDTO);
        }
        return applicationFormResponseDTO;
    }

    private ApplicationForm getApplicationFormById(Long id) {
        return applicationFormRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("not found Form with id: " + id));
    }

    private UserApplicationForm findUserApplicationFormByUsernameAndApplicationFormId(String username, Long appId) {
        return userApplicationFormRepository.findUserApplicationFormByUsernameAndApplicationFormId(username, appId);
    }
}
