package br.com.quiz.player.domain.entity;

import java.io.Serializable;

import br.com.quiz.domain.valueobject.Level;

public class QuizResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -587400187129754112L;
	
	private Level level = Level.BASIC;

	private int correctAnswers;

	private int questionsNumber;
	
	public QuizResult(Level level, int correctAnswers, int questionsNumber) {
		super();
		this.level = level;
		this.correctAnswers = correctAnswers;
		this.questionsNumber = questionsNumber;
	}
	
	public QuizResult(Level level, int questionsNumber) {
		super();
		this.level = level;
		this.questionsNumber = questionsNumber;
	}

	public QuizResult() {
		super();
	}

	public Level getLevel() {
		return level;
	}
	
	public int getCorrectAnswers() {
		return correctAnswers;
	}

	public void incrementCorrectAnswers() {
		this.correctAnswers++;
	}
	
	public int getQuestionsNumber() {
		return questionsNumber;
	}

	public Level getNextLevel(){
		Status status = status();
		if(level.equals(Level.BASIC) && status.equals(Status.WIN)){
			return Level.INTERMEDIATE;
		}else if(level.equals(Level.INTERMEDIATE) && status.equals(Status.WIN)){
			return Level.ADVANCED;
		}else if(level.equals(Level.ADVANCED) && status.equals(Status.WIN)){
			return Level.BASIC;
		}
		
		return level;
	}
		
	public Status status() {
		if (correctAnswers==questionsNumber && questionsNumber!=0) {
			return Status.WIN;
		} else {
			return Status.LOSE;
		}
	}
	
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		builder.append(" Quiz Level = ").append(level)
			   .append(", correct answers = ").append(correctAnswers);
		
		if(questionsNumber > 0){
			builder.append(", Status = ").append(status());
		}
		
		return builder.toString();
				
	}
	

}
