package com.bunny.quizapp.doa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bunny.quizapp.model.Quiz;

public interface QuizDao extends JpaRepository <Quiz,Integer> {

}
