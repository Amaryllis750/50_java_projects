package com.quizapp.entities;

import java.io.Serializable;
import java.util.Arrays;

public class Question implements Serializable{
    String question;
    String[] options;
    String answer;
    int numOfOptions;

    public Question(String question, String correctOption, String[] otherOptions){
        this.question = question;
        this.answer =correctOption;
        this.options = otherOptions;
        this.numOfOptions = otherOptions.length + 1;
    }

    public String getQuestion(){
        return this.question;
    }

    public String[] getOptions(){
        String[] allOptions = Arrays.copyOf(this.options, this.options.length + 1);
        allOptions[allOptions.length - 1] = answer;
        return allOptions;
    }

    public String getAnswer(){
        return this.answer;
    }

    public int getNumberOfQuestions(){
        return this.numOfOptions;
    }
}
