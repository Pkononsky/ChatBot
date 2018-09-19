package com.company;

public class Question {
    private Long Id;
    private String QuestionText;
    private String Answer;

    public Question(String questionText, String answer) {
        this.Answer = answer;
        this.QuestionText = questionText;
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
