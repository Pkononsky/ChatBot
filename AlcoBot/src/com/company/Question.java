package com.company;

import javax.annotation.Generated;
import javax.persistence.*;

@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long Id;
    private String QuestionText;
    private String Answer;

    public Question(String questionText, String answer) {
        this.Answer = answer;
        this.QuestionText = questionText;
    }

    public Question() {

    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getQuestionText() {
        return QuestionText;
    }

    public String getAnswer() {
        return Answer;
    }

    public void setQuestionText(String questionText) {
        QuestionText = questionText;
    }
}
