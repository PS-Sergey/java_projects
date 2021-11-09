package com.opencode.questionare.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user_application_form")
public class UserApplicationForm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "application_form_id")
    private Long applicationFormId;

    @OneToMany(mappedBy = "userApplicationForm", cascade = CascadeType.ALL)
    private List<UserAnswer> userAnswers;

    public void addUserAnswer(UserAnswer userAnswer) {
        if (this.userAnswers == null) {
            this.userAnswers = new ArrayList<UserAnswer>();
        }
        userAnswers.add(userAnswer);
        userAnswer.setUserApplicationForm(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getApplicationFormId() {
        return applicationFormId;
    }

    public void setApplicationFormId(Long applicationFormId) {
        this.applicationFormId = applicationFormId;
    }

    public List<UserAnswer> getUserAnswers() {
        return userAnswers;
    }

    public void setUserAnswers(List<UserAnswer> userAnswers) {
        this.userAnswers = userAnswers;
    }
}
