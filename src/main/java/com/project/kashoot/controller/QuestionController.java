package com.project.kashoot.controller;

import com.project.kashoot.exception.ResourceNotFoundException;
import com.project.kashoot.model.Category;
import com.project.kashoot.model.Question;
import com.project.kashoot.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/")
public class QuestionController {
    @Autowired
    private QuestionRepository questionRepository;

    //get questions
    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("question")
    public List<Question> getAllQuestions(){
        return this.questionRepository.findAll();
    }

    //save question
    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("question")
    public Question createQuestion(@RequestBody Question question){return this.questionRepository.save(question);}

    //update question
    @CrossOrigin(origins = "http://localhost:8080")
    @PutMapping("question/{id}")
    public ResponseEntity<Question> updateQuestion(@PathVariable(value = "id") Long question_id, @Validated @RequestBody Question questionDetails) throws ResourceNotFoundException {
        Question question = questionRepository.findById(question_id).orElseThrow(() -> new ResourceNotFoundException("Question not found for this id :: " + question_id));

        question.setCategoryId(questionDetails.getCategoryId());
        question.setQuestion_name(questionDetails.getQuestion_name());
        question.setQuestion_answer1(questionDetails.getQuestion_answer1());
        question.setQuestion_answer2(questionDetails.getQuestion_answer2());
        question.setQuestion_answer3(questionDetails.getQuestion_answer3());
        question.setQuestion_answer4(questionDetails.getQuestion_answer4());
        question.setQuestion_correct(questionDetails.getQuestion_correct());

        return ResponseEntity.ok(this.questionRepository.save(question));
    }

    // delete question
    @CrossOrigin(origins = "http://localhost:8080")
    @DeleteMapping("question/{id}")
    public Map<String, Boolean> deleteQuestion(@PathVariable(value = "id") Long question_id) throws ResourceNotFoundException {
        Question question = questionRepository.findById(question_id).orElseThrow(() -> new ResourceNotFoundException("Question not found for this id :: " + question_id));

        this.questionRepository.delete(question);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return response;
    }

    // get question by category id
    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("question/{category_id}")
    public List<Question> getQuestionByCategory(@PathVariable(value = "category_id") Category categoryId){
        return questionRepository.findQuestionByCategoryId(categoryId);
    }
}
