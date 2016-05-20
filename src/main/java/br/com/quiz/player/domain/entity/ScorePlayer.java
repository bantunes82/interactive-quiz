package br.com.quiz.player.domain.entity;

import java.io.Serializable;
import java.util.EnumMap;
import java.util.Map;

import br.com.quiz.domain.valueobject.QuizType;
import br.com.quiz.useful.Constants;

public class ScorePlayer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5242290194680796827L;
	
	
	private String playerName;
	
	private Map<QuizType, QuizResult> quizes = new EnumMap<>(QuizType.class);
	
	public ScorePlayer(String playerName, Map<QuizType, QuizResult> quizes) {
		super();
		this.playerName = playerName;
		this.quizes = quizes;
	}
	
	public ScorePlayer(String playerName) {
		super();
		this.playerName = playerName;
		quizes.put(QuizType.SOCCER, new QuizResult());
		quizes.put(QuizType.MOVIE, new QuizResult());
	}
	
	public Map<QuizType, QuizResult> getQuizes() {
		return quizes;
	}
	
	public String getPlayerName() {
		return playerName;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		builder.append(Constants.LINE_SEPARATOR)
				.append("Player: ")
				.append(playerName)
				.append(Constants.LINE_SEPARATOR)
				.append(QuizType.SOCCER).append(quizes.get(QuizType.SOCCER))
				.append(Constants.LINE_SEPARATOR)
				.append(QuizType.MOVIE).append(quizes.get(QuizType.MOVIE))
				.append(Constants.LINE_SEPARATOR);
		
		return builder.toString();
				
	}

}
