package com.sapient.quiz.model;

import java.util.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QuizResponse {
	public List<Quiz> quiz;
}