package com.sapient.exercise.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sapient.quiz.model.Quiz;
import com.sapient.quiz.model.QuizResponse;
import com.sapient.quiz.model.Result;

@Service
public class QuizServiceImpl implements QuizService {
	
	@Override
	public QuizResponse getOpenDb(List<String> urls) throws Exception {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		
		ResponseHandler<String> responseHandler = response -> {
			int status = response.getStatusLine().getStatusCode();
			if (status >= 200 && status < 300) {
				HttpEntity entity = response.getEntity();
				return entity != null ? EntityUtils.toString(entity) : null;
			} else {
				throw new ClientProtocolException("Unexpected response status: " + status);
			}
		};
		
		List<String> openDbresponses = new ArrayList<String>();
		
		for (String url : urls) {
			
			HttpGet httpget = new HttpGet(url);

			try {
				String responseBody = httpclient.execute(httpget, responseHandler);
				openDbresponses.add(responseBody);
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
		}
		
		QuizResponse quizResponse = ConvertOpenDbToQuiz(openDbresponses);
		return quizResponse;
	}
	
	public QuizResponse ConvertOpenDbToQuiz(List<String> openDbresponses)
	{
		QuizResponse quizResponse = new QuizResponse();
		List<Quiz> quizies = new ArrayList<Quiz>();

		for (String openDbresponse : openDbresponses) {
			Quiz quiz = new Quiz();
			JsonObject rootObj = JsonParser.parseString(openDbresponse).getAsJsonObject();
			JsonArray resultsArray = rootObj.getAsJsonArray("results");

			for (JsonElement resultData : resultsArray) {
				JsonObject result = resultData.getAsJsonObject();
				quiz.category = result.get("category").getAsString();
				if (quiz.results == null || quiz.results.size() == 0)
				{
					quiz.results = new ArrayList<Result>();
				}
				String type = result.get("type").getAsString();
				String difficulty = result.get("difficulty").getAsString();
				String question = result.get("question").getAsString();
				String correct_answer = result.get("correct_answer").getAsString();

				JsonArray allAnswers = result.get("incorrect_answers").getAsJsonArray();
				List<String> all_answers = new ArrayList<String>();
				all_answers.add(correct_answer);
				for (JsonElement allAnswer : allAnswers) {
					all_answers.add(allAnswer.getAsString());
				}

				quiz.results.add(new Result(type, difficulty, question, all_answers, correct_answer));
			}
			quizies.add(quiz);
		}

		quizResponse.quiz = quizies;
		return quizResponse;
	}
}
