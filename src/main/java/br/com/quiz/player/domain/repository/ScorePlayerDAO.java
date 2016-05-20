package br.com.quiz.player.domain.repository;

import java.util.Map;
import java.util.Optional;

import br.com.quiz.player.domain.entity.ScorePlayer;
import br.com.quiz.player.infrastructure.ScorePlayerFile;

public class ScorePlayerDAO implements IScorePlayerDAO {
	

	@Override
	public Optional<ScorePlayer> findScorePlayer(String playerName) {
		Map<String,ScorePlayer> scorePlayers = ScorePlayerFile.findScorePlayers();
			return Optional.ofNullable(scorePlayers.get(playerName));
						   
	}

	@Override
	public Map<String, ScorePlayer> findScorePlayers() {
		return ScorePlayerFile.findScorePlayers();
	}

	@Override
	public void saveScorePayers(Map<String, ScorePlayer> scorePlayers) {
		ScorePlayerFile.saveScorePlayers(scorePlayers);
	}
}
