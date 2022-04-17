package com.project.kashoot.repository;

import com.project.kashoot.model.User;

import java.util.List;

public interface UserCustomRepository {
    List<User> findByName(String name);
}
