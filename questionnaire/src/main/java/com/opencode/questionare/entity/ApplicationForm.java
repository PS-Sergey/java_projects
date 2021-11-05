package com.opencode.questionare.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "application_form")
public class ApplicationForm {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @OneToMany(mappedBy = "applicationForm", cascade = CascadeType.ALL)
    private List<Question> questions;

    public ApplicationForm(String title) {
        this.title = title;
    }

    public ApplicationForm() {
    }

    public void addQuestion(Question question) {
        if (questions == null) {
            questions = new ArrayList<>();
        }
        questions.add(question);
        question.setApplicationForm(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long application_form_id) {
        this.id = application_form_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
