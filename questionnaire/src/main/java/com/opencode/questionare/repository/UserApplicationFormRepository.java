package com.opencode.questionare.repository;

import com.opencode.questionare.entity.UserApplicationForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserApplicationFormRepository extends JpaRepository<UserApplicationForm, Long> {

    @Query(value = "select u.application_form_id from USER_APPLICATION_FORM u where u.username = ?1",
            nativeQuery = true)
    List<Long> findApplicationFormIdByUsername(String username);

    UserApplicationForm findUserApplicationFormByUsernameAndApplicationFormId(String username, Long applicationFormId);
}
