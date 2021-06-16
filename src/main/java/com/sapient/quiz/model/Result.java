package com.sapient.quiz.model;

import java.util.*;

public class Result{
    public String type;
    public String difficulty;
    public String question;
    public List<String> all_answers;
    public String correct_answer;
	
    public Result() {
		super();
	}

	public Result(String type, String difficulty, String question, List<String> all_answers, String correct_answer) {
		super();
		this.type = type;
		this.difficulty = difficulty;
		this.question = question;
		this.all_answers = all_answers;
		this.correct_answer = correct_answer;
	}
    
}