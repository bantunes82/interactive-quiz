package br.com.quiz.player.infrastructure;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.EnumMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import br.com.quiz.domain.valueobject.Level;
import br.com.quiz.domain.valueobject.QuizType;
import br.com.quiz.player.domain.entity.QuizResult;
import br.com.quiz.player.domain.entity.ScorePlayer;
import br.com.quiz.player.exception.FileProblemException;
import br.com.quiz.useful.Constants;

public class ScorePlayerFile {

	private static final String HEADER_FILE = "PlayerName,SoccerLevel,CorrectAnswers,QuestionsNumber,MovieLevel,CorrectAnswers,QuestionsNumber";
	private static String scorePlayersFile = "src/main/resources/scorePlayers.txt";
	
	@SuppressWarnings("common-java:InsufficientBranchCoverage")
	private ScorePlayerFile(){
	}
	
	public static void setScorePlayersFile(String fileName){
		scorePlayersFile = fileName;
	}

	public static Map<String, ScorePlayer> findScorePlayers() {
		try(Stream<String> stream = Files.lines(Paths.get(scorePlayersFile)))
		{
			return stream
				   .skip(1)
				   .collect(Collectors.mapping(ScorePlayerFile::convertStringToScorePlayer, Collectors.toMap(ScorePlayer::getPlayerName, Function.identity())));
		} catch (IOException e) {
			throw new FileProblemException("Error reading the file: " + scorePlayersFile, e);
		}	
	}

	public static void saveScorePlayers(Map<String, ScorePlayer> scorePlayers){
		try {
			StringBuilder contentFile = new StringBuilder();
			contentFile.append(HEADER_FILE);
			
			scorePlayers.values()
						.stream()
						.forEach(scorePlayer -> contentFile.append(Constants.LINE_SEPARATOR)
														   .append(convertScorePlayerToString(scorePlayer)));

			Files.write(Paths.get(scorePlayersFile), contentFile.toString().getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
			
		} catch (IOException e) {
			throw new FileProblemException("Error writing the file: " + scorePlayersFile, e);
		}	
	}

	private static String convertScorePlayerToString(ScorePlayer scorePlayer) {

		StringBuilder line = new StringBuilder();
			line.append(scorePlayer.getPlayerName())
					.append(Constants.CHARACTER_SEPARATOR)
					.append(scorePlayer.getQuizes().get(QuizType.SOCCER)
							.getLevel().toString())
					.append(Constants.CHARACTER_SEPARATOR)
					.append(scorePlayer.getQuizes()
							.get(QuizType.SOCCER).getCorrectAnswers())
					.append(Constants.CHARACTER_SEPARATOR)
					.append(scorePlayer.getQuizes()
							.get(QuizType.SOCCER).getQuestionsNumber())
					.append(Constants.CHARACTER_SEPARATOR)
					.append(scorePlayer.getQuizes().get(QuizType.MOVIE)
							.getLevel().toString())
					.append(Constants.CHARACTER_SEPARATOR)
					.append(scorePlayer.getQuizes()
							.get(QuizType.MOVIE).getCorrectAnswers())
					.append(Constants.CHARACTER_SEPARATOR)
					.append(scorePlayer.getQuizes()
							.get(QuizType.MOVIE).getQuestionsNumber());
		return line.toString();
	}
	
	@SuppressWarnings("squid:UnusedPrivateMethod")
	private static ScorePlayer convertStringToScorePlayer(String line) {
		String[] elements = line.split(Constants.CHARACTER_SEPARATOR);

		QuizResult quizResultSoccer = new QuizResult(
				Level.valueOf(elements[1]), Integer.valueOf(elements[2]),
				Integer.valueOf(elements[3]));
		QuizResult quizResultMovie = new QuizResult(Level.valueOf(elements[4]),
				Integer.valueOf(elements[5]), Integer.valueOf(elements[6]));

		Map<QuizType, QuizResult> quizes = new EnumMap<>(QuizType.class);
		quizes.put(QuizType.SOCCER, quizResultSoccer);
		quizes.put(QuizType.MOVIE, quizResultMovie);

		return new ScorePlayer(elements[0], quizes);

	}

}
