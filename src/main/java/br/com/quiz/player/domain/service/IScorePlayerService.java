package br.com.quiz.player.domain.service;

import br.com.quiz.player.domain.entity.ScorePlayer;

public interface IScorePlayerService {
	
	public ScorePlayer findScorePlayer(String playerName);
	
	public void saveScorePayer(ScorePlayer scorePlayer);

}
