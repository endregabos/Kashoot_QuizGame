package com.project.kashoot.model;

import javax.persistence.*;

@Entity
@Table(name = "question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long question_id;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "category_id", nullable = false)
    private Category categoryId;

    @Column(name = "question_name", nullable = false)
    private String question_name;
    @Column(name = "question_answer1", nullable = false)
    private String question_answer1;
    @Column(name = "question_answer2", nullable = false)
    private String question_answer2;
    @Column(name = "question_answer3", nullable = false)
    private String question_answer3;
    @Column(name = "question_answer4", nullable = false)
    private String question_answer4;
    @Column(name = "question_correct", nullable = false)
    private String question_correct;

    public Question(){super();}

    public Question(Category category_id, String question_name, String question_answer1, String question_answer2, String question_answer3, String question_answer4, String question_correct) {
        this.categoryId = category_id;
        this.question_name = question_name;
        this.question_answer1 = question_answer1;
        this.question_answer2 = question_answer2;
        this.question_answer3 = question_answer3;
        this.question_answer4 = question_answer4;
        this.question_correct = question_correct;
    }

    public long getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(long question_id) {
        this.question_id = question_id;
    }

    public Category getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Category category_id) {
        this.categoryId = category_id;
    }

    public String getQuestion_name() {
        return question_name;
    }

    public void setQuestion_name(String question_name) {
        this.question_name = question_name;
    }

    public String getQuestion_answer1() {
        return question_answer1;
    }

    public void setQuestion_answer1(String question_answer1) {
        this.question_answer1 = question_answer1;
    }

    public String getQuestion_answer2() {
        return question_answer2;
    }

    public void setQuestion_answer2(String question_answer2) {
        this.question_answer2 = question_answer2;
    }

    public String getQuestion_answer3() {
        return question_answer3;
    }

    public void setQuestion_answer3(String question_answer3) {
        this.question_answer3 = question_answer3;
    }

    public String getQuestion_answer4() {
        return question_answer4;
    }

    public void setQuestion_answer4(String question_answer4) {
        this.question_answer4 = question_answer4;
    }

    public String getQuestion_correct() {
        return question_correct;
    }

    public void setQuestion_correct(String question_correct) {
        this.question_correct = question_correct;
    }

    @Override
    public String toString(){
        return "Question [question_id=" + question_id + ", category_id=" + categoryId.getCategory_id() + ", question_name=" + question_name + ", question_answer1=" + question_answer1+ ", question_answer2=" + question_answer2+ ", question_answer3=" + question_answer3+ ", question_answer4=" + question_answer4+ ", question_correct=" + question_correct + " ]";
    }
}
