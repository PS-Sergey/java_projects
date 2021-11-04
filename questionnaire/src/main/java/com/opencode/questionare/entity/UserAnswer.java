package com.opencode.questionare.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "userAnswer")
public class UserAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "answer_id")
    private Long answerId;

    @Column(name = "user_answer_check")
    private boolean userAnswerCheck;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userApplicationForm")
    @JsonIgnore
    private UserApplicationForm userApplicationForm;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }

    public boolean isUserAnswerCheck() {
        return userAnswerCheck;
    }

    public void setUserAnswerCheck(boolean userAnswerCheck) {
        this.userAnswerCheck = userAnswerCheck;
    }

    public UserApplicationForm getUserApplicationForm() {
        return userApplicationForm;
    }

    public void setUserApplicationForm(UserApplicationForm userApplicationForm) {
        this.userApplicationForm = userApplicationForm;
    }
}
