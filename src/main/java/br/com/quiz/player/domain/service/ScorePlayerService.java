package br.com.quiz.player.domain.service;

import java.util.Map;

import br.com.quiz.player.domain.entity.ScorePlayer;
import br.com.quiz.player.domain.repository.IScorePlayerDAO;
import br.com.quiz.player.domain.repository.ScorePlayerDAO;

public class ScorePlayerService implements IScorePlayerService {
	
	private IScorePlayerDAO scorePlayerDAO = new ScorePlayerDAO();

	@Override
	public ScorePlayer findScorePlayer(String playerName){
		return scorePlayerDAO.findScorePlayer(playerName).
							  orElse(new ScorePlayer(playerName));
	}

	@Override
	public void saveScorePayer(ScorePlayer scorePlayer){
		Map<String, ScorePlayer> scorePlayers = scorePlayerDAO.findScorePlayers();
		
		scorePlayers.put(scorePlayer.getPlayerName(), scorePlayer);
		
		scorePlayerDAO.saveScorePayers(scorePlayers);
		
	}

}
