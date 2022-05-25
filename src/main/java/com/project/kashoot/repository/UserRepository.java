package com.project.kashoot.repository;

import com.project.kashoot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{
    Optional<User> findUserByUsernameAndUserpassword(String username, String userpassword);
}
