package com.project.kashoot.repository;

import com.project.kashoot.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class UserCustomRepositoryImpl implements UserCustomRepository {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<User> findByName(String user_name) {
        String sql = "FROM users WHERE user_name = :user_name";
        final TypedQuery<User> query = entityManager.createQuery(sql, User.class);
        query.setParameter("user_name", user_name);

        return query.getResultList();
    }
}
