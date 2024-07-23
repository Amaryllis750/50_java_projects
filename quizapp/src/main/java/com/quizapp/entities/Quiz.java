package com.quizapp.entities;

import java.io.Serializable;
import java.util.ArrayList;

public class Quiz implements Serializable{
    private String quizName;
    private java.util.List<Question> questions = new ArrayList<>();

    public Quiz(String name){
        this.quizName = name;
    }

    public void setQuestions(java.util.List<Question> questions){
        this.questions = questions;
    }

    public java.util.List<Question> getQuestions(){
        return this.questions;
    }

    public String getQuizName(){
        return this.quizName;
    }
}
