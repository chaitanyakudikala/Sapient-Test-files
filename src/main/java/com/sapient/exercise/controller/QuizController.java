package com.sapient.exercise.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.exercise.service.QuizService;
import com.sapient.exercise.util.GlobalProperties;
import com.sapient.quiz.model.QuizResponse;

@RestController
public class QuizController {

	@Autowired
	QuizService quizService;
	
	@Autowired
	GlobalProperties globalProperties;
	
	@RequestMapping(value = "/coding/exercise/quiz")
	public ResponseEntity<Object> getProduct() {
		
		List<String> categoryList = Arrays.asList(globalProperties.getCategoryList().split(","));
		
		List<String> urls = new ArrayList<String>();
		for (String category : categoryList) {
			urls.add(globalProperties.getOpenDbUri() + "/api.php"
					+ "?amount=" + globalProperties.getAmount() 
					+ "&category=" + category);
		}
		
		try {
			QuizResponse quizResponse = quizService.getOpenDb(urls);
			return new ResponseEntity<>(quizResponse, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Internal Error Occured: " + e.getMessage(), HttpStatus.FAILED_DEPENDENCY);
		}
	}
}