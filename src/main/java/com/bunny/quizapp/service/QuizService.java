package com.bunny.quizapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bunny.quizapp.doa.QuestionDao;
import com.bunny.quizapp.doa.QuizDao;
import com.bunny.quizapp.model.Question;
import com.bunny.quizapp.model.QuestionWrapper;
import com.bunny.quizapp.model.Quiz;
import com.bunny.quizapp.model.Response;

@Service
public class QuizService {

	@Autowired
	QuizDao quizDao;

	@Autowired
	QuestionDao questionDao;

	public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

		List<Question> questions = questionDao.findRandomQuestionByCategory(category, numQ);

		Quiz quiz = new Quiz();

		quiz.setTitle(title);
		quiz.setQuestions(questions);
		quizDao.save(quiz);

		return new ResponseEntity<>("Successfully create quiz", HttpStatus.CREATED);

	}

	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
		Optional<Quiz> quiz = quizDao.findById(id);
		List<Question> questionFromDb = quiz.get().getQuestions();
		List<QuestionWrapper> questionsForUsers = new ArrayList<>();

		for (Question q : questionFromDb) {
			QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getOption1(), q.getOption2(), q.getOption3(),
					q.getOption4(), q.getLevel(), q.getQuestionTitle());

			questionsForUsers.add(qw);
		}

		return new ResponseEntity<>(questionsForUsers, HttpStatus.OK);
	}

	public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {

		Quiz quiz = quizDao.findById(id).get();
		List<Question> questions = quiz.getQuestions();
		int right = 0;
		int i = 0;
		

		
		
		for (Response resp : responses) {

			if (resp.getResponse().equals(questions.get(i).getAnswer())) {
				right++;
				
			}
			i++;
		}

		return new ResponseEntity<>(right, HttpStatus.OK);

	}

}
