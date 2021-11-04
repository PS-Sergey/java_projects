package com.opencode.questionare.controller.dto;

import com.opencode.questionare.entity.UserAnswer;

import java.util.List;

public class UserApplicationFormRequestDTO {

    private Long id;
    private String username;
    private Long applicationFormId;
    private List<UserAnswer> userAnswers;
}
