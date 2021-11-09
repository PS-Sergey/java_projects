package com.opencode.questionare.repository;

import com.opencode.questionare.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    @Query(value = "select u.id from USER u where u.username = ?1",
            nativeQuery = true)
    Long findUserIdByUsername(String username);
}
