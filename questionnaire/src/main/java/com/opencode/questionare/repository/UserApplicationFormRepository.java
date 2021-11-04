package com.opencode.questionare.repository;

import com.opencode.questionare.entity.UserApplicationForm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserApplicationFormRepository extends JpaRepository<UserApplicationForm, Long> {
}
