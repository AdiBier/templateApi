package com.ab.templateApi.dao.repository;

import com.ab.templateApi.dao.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByEmail(String email);
    User findUserByPhone(String phone);
    List<User> findUserByRole(String role);
}
