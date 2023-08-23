package com.bunny.quizapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bunny.quizapp.Question;
import com.bunny.quizapp.doa.QuestionDao;

@Service
public class QuestionService {

	@Autowired
	QuestionDao questionDoa;

	public List<Question> getAllQuestions() {
		return questionDoa.findAll();

	}

	public List<Question> getQuestionByCategory(String category) {
		return questionDoa.findByCategory(category);
	}

	public String addQuestion(Question question) {
		questionDoa.save(question);
		return "Data Successfully Save";
	}

	public String deletQuestionById(Integer id) {
		questionDoa.deleteById(id);
		return "Data Successfully Delete "+id;
	}

}
