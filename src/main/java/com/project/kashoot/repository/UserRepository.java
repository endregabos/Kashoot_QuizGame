package com.project.kashoot.repository;

import com.project.kashoot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long>, UserCustomRepository{

}
