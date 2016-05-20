package br.com.quiz.domain.valueobject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.quiz.useful.Constants;


public class QuestionVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8304708070968415301L;
	
	private String description;
	
	private List<String> possibleAnswers= new ArrayList<>();
	
	private int answer;
	
	public QuestionVO(String description,int answer) {
		super();
		this.description = description;
		this.answer = answer;
	}

	public void addPossibleAnswer(String possibleAnswer){
		possibleAnswers.add(possibleAnswer);
	}

	public boolean correctAnswer(int answerReceived){
		return answerReceived==answer;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(description);
		
		possibleAnswers.stream()
					   .forEach(s -> builder.append(Constants.LINE_SEPARATOR)
						.append(s));

	    return builder.toString();
		
	}
	
}
