package com.opencode.questionare.repository;

import com.opencode.questionare.entity.ApplicationForm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationFormRepository extends JpaRepository<ApplicationForm, Long> {

    List<ApplicationForm> findApplicationFormsByIdIn(List<Long> ids);
}
