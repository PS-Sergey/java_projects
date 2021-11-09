package com.opencode.questionare.repository;

import com.opencode.questionare.entity.UserApplicationForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserApplicationFormRepository extends JpaRepository<UserApplicationForm, Long> {

    @Query(value = "select u.application_form_id from USER_APPLICATION_FORM u where u.user_id = ?1",
            nativeQuery = true)
    List<Long> findApplicationFormIdByUserId(Long userId);



    UserApplicationForm findUserApplicationFormByUserIdAndApplicationFormId(Long userId, Long applicationFormId);

    void deleteAllByApplicationFormId(Long applicationFormId);
}
