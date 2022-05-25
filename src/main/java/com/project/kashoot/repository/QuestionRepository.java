package com.project.kashoot.repository;

import com.project.kashoot.model.Category;
import com.project.kashoot.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findQuestionByCategoryId(Category categoryId);
}
