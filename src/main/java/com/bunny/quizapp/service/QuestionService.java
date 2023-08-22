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

}
