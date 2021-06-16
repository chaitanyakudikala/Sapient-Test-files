package com.sapient.exercise.service;

import java.util.List;

import com.sapient.quiz.model.QuizResponse;

public interface QuizService {
	public QuizResponse getOpenDb(List<String> urls) throws Exception;
}
