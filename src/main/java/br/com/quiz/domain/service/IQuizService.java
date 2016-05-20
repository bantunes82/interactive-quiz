package br.com.quiz.domain.service;

import br.com.quiz.domain.valueobject.Level;
import br.com.quiz.domain.valueobject.QuizType;
import br.com.quiz.domain.valueobject.QuizVO;

@FunctionalInterface
public interface IQuizService {
	
	public QuizVO generateQuiz(QuizType quizType,Level level);

}
