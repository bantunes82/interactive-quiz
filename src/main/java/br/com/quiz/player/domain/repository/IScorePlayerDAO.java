package br.com.quiz.player.domain.repository;

import java.util.Map;
import java.util.Optional;

import br.com.quiz.player.domain.entity.ScorePlayer;

public interface IScorePlayerDAO {
	
	public Optional<ScorePlayer> findScorePlayer(String playerName);
	
	public Map<String, ScorePlayer> findScorePlayers();
	
	public void saveScorePayers(Map<String, ScorePlayer> scorePlayers);

}
