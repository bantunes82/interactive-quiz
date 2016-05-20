package br.com.quiz.domain.valueobject;

import java.io.Serializable;
import java.util.List;



public class QuizVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2724925804763849627L;
	
	private QuizType quizType;
	
	private Level quizLevel;
	
	private List<QuestionVO> questions;
	
	public QuizVO(QuizType quizType, Level quizLevel, List<QuestionVO> questions) {
		super();
		this.quizType = quizType;
		this.quizLevel = quizLevel;
		this.questions = questions;
	}

	public QuizType getQuizType() {
		return quizType;
	}

	public Level getQuizLevel() {
		return quizLevel;
	}

	public List<QuestionVO> getQuestions() {
		return questions;
	}
	
}
