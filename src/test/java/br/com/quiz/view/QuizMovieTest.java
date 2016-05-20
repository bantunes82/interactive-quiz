package br.com.quiz.view;

import static br.com.quiz.domain.valueobject.QuizTypeHelper.setFileName;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.quiz.domain.valueobject.Level;
import br.com.quiz.domain.valueobject.QuizType;
import br.com.quiz.player.domain.entity.QuizResult;
import br.com.quiz.player.domain.entity.ScorePlayer;
import br.com.quiz.player.domain.repository.IScorePlayerDAO;
import br.com.quiz.player.domain.repository.ScorePlayerDAO;
import br.com.quiz.player.infrastructure.ScorePlayerFile;
import br.com.quiz.useful.Constants;
import br.com.quiz.useful.TestConstants;

public class QuizMovieTest {
	
	private Scanner scanner;
	private static LoginView loginView;
	private static IScorePlayerDAO scorePlayerDAO;
	private static HashMap<String, ScorePlayer> scorePlayers;
	
	@BeforeClass
	public static void initialSetup(){
		ScorePlayerFile.setScorePlayersFile("src/test/resources/br/com/quiz/view/scorePlayer.txt");
		setFileName(QuizType.MOVIE,TestConstants.MOVIE_QUESTIONS_FILE);
		scorePlayerDAO = new ScorePlayerDAO();
		
		scorePlayers = new HashMap<>();
		
		HashMap<QuizType, QuizResult> quizesJohnPlayer = new HashMap<>();
		quizesJohnPlayer.put(QuizType.SOCCER, new QuizResult());
		quizesJohnPlayer.put(QuizType.MOVIE, new QuizResult(Level.BASIC,1,1));
		ScorePlayer scoreJohnPlayer = new ScorePlayer(TestConstants.JOHN_PLAYER,quizesJohnPlayer);
		
		HashMap<QuizType, QuizResult> quizesRobertPlayer = new HashMap<>();
		quizesRobertPlayer.put(QuizType.SOCCER, new QuizResult());
		quizesRobertPlayer.put(QuizType.MOVIE, new QuizResult(Level.INTERMEDIATE,1,1));
		ScorePlayer scoreRobertPlayer = new ScorePlayer(TestConstants.ROBERT_PLAYER,quizesRobertPlayer);
		
		HashMap<QuizType, QuizResult> quizesPaulPlayer = new HashMap<>();
		quizesPaulPlayer.put(QuizType.SOCCER, new QuizResult());
		quizesPaulPlayer.put(QuizType.MOVIE, new QuizResult(Level.ADVANCED,1,1));
		ScorePlayer scorePaulPlayer = new ScorePlayer(TestConstants.PAUL_PLAYER,quizesPaulPlayer);
		
		scorePlayers.put(TestConstants.JOHN_PLAYER,scoreJohnPlayer);
		scorePlayers.put(TestConstants.ROBERT_PLAYER, scoreRobertPlayer);
		scorePlayers.put(TestConstants.PAUL_PLAYER, scorePaulPlayer);
	}
	
	@Before
	public void initializeTest() throws IOException{
		scorePlayerDAO.saveScorePayers(scorePlayers);
	}
	
	@After
	public void finalizeTest() throws IOException{
		scanner.close();
	}
	
	
	@Test()
	public void testBasicLevelWin() throws IOException{
		StringBuilder builder = new StringBuilder(TestConstants.PETER_PLAYER)
								.append(Constants.LINE_SEPARATOR).append(Constants.NUMBER_TO_PLAY).append(Constants.LINE_SEPARATOR)
								.append(Constants.NUMBER_TO_QUIZ_MOVIE).append(Constants.LINE_SEPARATOR).append(3)
								.append(Constants.LINE_SEPARATOR).append(Constants.NUMBER_TO_COME_BACK)
								.append(Constants.LINE_SEPARATOR).append(Constants.NUMBER_TO_RESUME)
								.append(Constants.LINE_SEPARATOR).append(Constants.NUMBER_TO_SAVE)
								.append(Constants.LINE_SEPARATOR).append(Constants.NUMBER_TO_EXIT);
		scanner = new Scanner(builder.toString());
		
		loginView.setScanner(scanner);
		loginView.main(null);
		ScorePlayer scorePlayer = scorePlayerDAO.findScorePlayer(TestConstants.PETER_PLAYER).get();
		
		Assert.assertNotNull(scorePlayer);
		Assert.assertEquals(TestConstants.PETER_PLAYER, scorePlayer.getPlayerName());
		
		QuizResult quizSoccerResult = scorePlayer.getQuizes().get(QuizType.SOCCER);
		Assert.assertEquals(Level.BASIC, quizSoccerResult.getLevel());
		Assert.assertEquals(0, quizSoccerResult.getCorrectAnswers());
		Assert.assertEquals(0, quizSoccerResult.getQuestionsNumber());
		
		QuizResult quizMovieResult = scorePlayer.getQuizes().get(QuizType.MOVIE);
		Assert.assertEquals(Level.BASIC, quizMovieResult.getLevel());
		Assert.assertEquals(1, quizMovieResult.getCorrectAnswers());
		Assert.assertEquals(1, quizMovieResult.getQuestionsNumber());
	}
	
	@Test()
	public void testBasicLevelLose() throws IOException{
		StringBuilder builder = new StringBuilder(TestConstants.PETER_PLAYER)
								.append(Constants.LINE_SEPARATOR).append(Constants.NUMBER_TO_PLAY).append(Constants.LINE_SEPARATOR)
								.append(Constants.NUMBER_TO_QUIZ_MOVIE).append(Constants.LINE_SEPARATOR).append(1)
								.append(Constants.LINE_SEPARATOR).append(Constants.NUMBER_TO_COME_BACK)
								.append(Constants.LINE_SEPARATOR).append(Constants.NUMBER_TO_RESUME)
								.append(Constants.LINE_SEPARATOR).append(Constants.NUMBER_TO_SAVE)
								.append(Constants.LINE_SEPARATOR).append(Constants.NUMBER_TO_EXIT);
		
		scanner = new Scanner(builder.toString());
		
		loginView.setScanner(scanner);;
	
		loginView.main(null);
		
		ScorePlayer scorePlayer = scorePlayerDAO.findScorePlayer(TestConstants.PETER_PLAYER).get();
		
		Assert.assertNotNull(scorePlayer);
		Assert.assertEquals(TestConstants.PETER_PLAYER, scorePlayer.getPlayerName());
		
		QuizResult quizSoccerResult = scorePlayer.getQuizes().get(QuizType.SOCCER);
		Assert.assertEquals(Level.BASIC, quizSoccerResult.getLevel());
		Assert.assertEquals(0, quizSoccerResult.getCorrectAnswers());
		Assert.assertEquals(0, quizSoccerResult.getQuestionsNumber());
		
		QuizResult quizMovieResult = scorePlayer.getQuizes().get(QuizType.MOVIE);
		Assert.assertEquals(Level.BASIC, quizMovieResult.getLevel());
		Assert.assertEquals(0, quizMovieResult.getCorrectAnswers());
		Assert.assertEquals(1, quizMovieResult.getQuestionsNumber());
	}
	
	@Test()
	public void testIntermediateLevelWin() throws IOException{
		
		StringBuilder builder = new StringBuilder(TestConstants.JOHN_PLAYER)
								.append(Constants.LINE_SEPARATOR).append(Constants.NUMBER_TO_PLAY).append(Constants.LINE_SEPARATOR)
								.append(Constants.NUMBER_TO_QUIZ_MOVIE).append(Constants.LINE_SEPARATOR).append(3)
								.append(Constants.LINE_SEPARATOR).append(Constants.NUMBER_TO_COME_BACK)
								.append(Constants.LINE_SEPARATOR).append(Constants.NUMBER_TO_RESUME)
								.append(Constants.LINE_SEPARATOR).append(Constants.NUMBER_TO_SAVE)
								.append(Constants.LINE_SEPARATOR).append(Constants.NUMBER_TO_EXIT);
		scanner = new Scanner(builder.toString());
		
		loginView.setScanner(scanner);
	
		loginView.main(null);
		
		ScorePlayer scorePlayer = scorePlayerDAO.findScorePlayer(TestConstants.JOHN_PLAYER).get();
		
		Assert.assertNotNull(scorePlayer);
		Assert.assertEquals(TestConstants.JOHN_PLAYER, scorePlayer.getPlayerName());
		
		QuizResult quizSoccerResult = scorePlayer.getQuizes().get(QuizType.SOCCER);
		Assert.assertEquals(Level.BASIC, quizSoccerResult.getLevel());
		Assert.assertEquals(0, quizSoccerResult.getCorrectAnswers());
		Assert.assertEquals(0, quizSoccerResult.getQuestionsNumber());
		
		QuizResult quizMovieResult = scorePlayer.getQuizes().get(QuizType.MOVIE);
		Assert.assertEquals(Level.INTERMEDIATE, quizMovieResult.getLevel());
		Assert.assertEquals(1, quizMovieResult.getCorrectAnswers());
		Assert.assertEquals(1, quizMovieResult.getQuestionsNumber());
	}
	
	@Test()
	public void testIntermediateLevelLose() throws IOException{
		
		StringBuilder builder = new StringBuilder(TestConstants.JOHN_PLAYER)
								.append(Constants.LINE_SEPARATOR).append(Constants.NUMBER_TO_PLAY).append(Constants.LINE_SEPARATOR)
								.append(Constants.NUMBER_TO_QUIZ_MOVIE).append(Constants.LINE_SEPARATOR).append(2)
								.append(Constants.LINE_SEPARATOR).append(Constants.NUMBER_TO_COME_BACK)
								.append(Constants.LINE_SEPARATOR).append(Constants.NUMBER_TO_RESUME)
								.append(Constants.LINE_SEPARATOR).append(Constants.NUMBER_TO_SAVE)
								.append(Constants.LINE_SEPARATOR).append(Constants.NUMBER_TO_EXIT);
		scanner = new Scanner(builder.toString());
		
		loginView.setScanner(scanner);
	
		loginView.main(null);
		
		ScorePlayer scorePlayer = scorePlayerDAO.findScorePlayer(TestConstants.JOHN_PLAYER).get();
		
		Assert.assertNotNull(scorePlayer);
		Assert.assertEquals(TestConstants.JOHN_PLAYER, scorePlayer.getPlayerName());
		
		QuizResult quizSoccerResult = scorePlayer.getQuizes().get(QuizType.SOCCER);
		Assert.assertEquals(Level.BASIC, quizSoccerResult.getLevel());
		Assert.assertEquals(0, quizSoccerResult.getCorrectAnswers());
		Assert.assertEquals(0, quizSoccerResult.getQuestionsNumber());
		
		QuizResult quizMovieResult = scorePlayer.getQuizes().get(QuizType.MOVIE);
		Assert.assertEquals(Level.INTERMEDIATE, quizMovieResult.getLevel());
		Assert.assertEquals(0, quizMovieResult.getCorrectAnswers());
		Assert.assertEquals(1, quizMovieResult.getQuestionsNumber());
	}
	
	@Test()
	public void testAdvancedLevelWin() throws IOException{
		
		StringBuilder builder = new StringBuilder(TestConstants.ROBERT_PLAYER)
								.append(Constants.LINE_SEPARATOR).append(Constants.NUMBER_TO_PLAY).append(Constants.LINE_SEPARATOR)
								.append(Constants.NUMBER_TO_QUIZ_MOVIE).append(Constants.LINE_SEPARATOR).append(2)
								.append(Constants.LINE_SEPARATOR).append(Constants.NUMBER_TO_COME_BACK)
								.append(Constants.LINE_SEPARATOR).append(Constants.NUMBER_TO_RESUME)
								.append(Constants.LINE_SEPARATOR).append(Constants.NUMBER_TO_SAVE)
								.append(Constants.LINE_SEPARATOR).append(Constants.NUMBER_TO_EXIT);
		scanner = new Scanner(builder.toString());
		
		loginView.setScanner(scanner);
	
		loginView.main(null);
		
		ScorePlayer scorePlayer = scorePlayerDAO.findScorePlayer(TestConstants.ROBERT_PLAYER).get();
		
		Assert.assertNotNull(scorePlayer);
		Assert.assertEquals(TestConstants.ROBERT_PLAYER, scorePlayer.getPlayerName());
		
		QuizResult quizSoccerResult = scorePlayer.getQuizes().get(QuizType.SOCCER);
		Assert.assertEquals(Level.BASIC, quizSoccerResult.getLevel());
		Assert.assertEquals(0, quizSoccerResult.getCorrectAnswers());
		Assert.assertEquals(0, quizSoccerResult.getQuestionsNumber());
		
		QuizResult quizMovieResult = scorePlayer.getQuizes().get(QuizType.MOVIE);
		Assert.assertEquals(Level.ADVANCED, quizMovieResult.getLevel());
		Assert.assertEquals(1, quizMovieResult.getCorrectAnswers());
		Assert.assertEquals(1, quizMovieResult.getQuestionsNumber());
	}
	
	@Test()
	public void testAdvancedLevelLose() throws IOException{
		
		StringBuilder builder = new StringBuilder(TestConstants.ROBERT_PLAYER)
								.append(Constants.LINE_SEPARATOR).append(Constants.NUMBER_TO_PLAY).append(Constants.LINE_SEPARATOR)
								.append(Constants.NUMBER_TO_QUIZ_MOVIE).append(Constants.LINE_SEPARATOR).append(3)
								.append(Constants.LINE_SEPARATOR).append(Constants.NUMBER_TO_COME_BACK)
								.append(Constants.LINE_SEPARATOR).append(Constants.NUMBER_TO_RESUME)
								.append(Constants.LINE_SEPARATOR).append(Constants.NUMBER_TO_SAVE)
								.append(Constants.LINE_SEPARATOR).append(Constants.NUMBER_TO_EXIT);		
		scanner = new Scanner(builder.toString());
		
		loginView.setScanner(scanner);
	
		loginView.main(null);
		
		ScorePlayer scorePlayer = scorePlayerDAO.findScorePlayer(TestConstants.ROBERT_PLAYER).get();
		
		Assert.assertNotNull(scorePlayer);
		Assert.assertEquals(TestConstants.ROBERT_PLAYER, scorePlayer.getPlayerName());
		
		QuizResult quizSoccerResult = scorePlayer.getQuizes().get(QuizType.SOCCER);
		Assert.assertEquals(Level.BASIC, quizSoccerResult.getLevel());
		Assert.assertEquals(0, quizSoccerResult.getCorrectAnswers());
		Assert.assertEquals(0, quizSoccerResult.getQuestionsNumber());
		
		QuizResult quizMovieResult = scorePlayer.getQuizes().get(QuizType.MOVIE);
		Assert.assertEquals(Level.ADVANCED, quizMovieResult.getLevel());
		Assert.assertEquals(0, quizMovieResult.getCorrectAnswers());
		Assert.assertEquals(1, quizMovieResult.getQuestionsNumber());
	}
	
	@Test()
	public void testBasicLevelWinFromAdvancedLevel() throws IOException{
		StringBuilder builder = new StringBuilder(TestConstants.PAUL_PLAYER)
					.append(Constants.LINE_SEPARATOR).append(Constants.NUMBER_TO_PLAY).append(Constants.LINE_SEPARATOR)
					.append(Constants.NUMBER_TO_QUIZ_MOVIE).append(Constants.LINE_SEPARATOR).append(3)
					.append(Constants.LINE_SEPARATOR).append(Constants.NUMBER_TO_COME_BACK)
					.append(Constants.LINE_SEPARATOR).append(Constants.NUMBER_TO_RESUME)
					.append(Constants.LINE_SEPARATOR).append(Constants.NUMBER_TO_SAVE)
					.append(Constants.LINE_SEPARATOR).append(Constants.NUMBER_TO_EXIT);
		scanner = new Scanner(builder.toString());
		
		loginView.setScanner(scanner);
	
		loginView.main(null);
		
		ScorePlayer scorePlayer = scorePlayerDAO.findScorePlayer(TestConstants.PAUL_PLAYER).get();
		
		Assert.assertNotNull(scorePlayer);
		Assert.assertEquals(TestConstants.PAUL_PLAYER, scorePlayer.getPlayerName());
		
		QuizResult quizSoccerResult = scorePlayer.getQuizes().get(QuizType.SOCCER);
		Assert.assertEquals(Level.BASIC, quizSoccerResult.getLevel());
		Assert.assertEquals(0, quizSoccerResult.getCorrectAnswers());
		Assert.assertEquals(0, quizSoccerResult.getQuestionsNumber());
		
		QuizResult quizMovieResult = scorePlayer.getQuizes().get(QuizType.MOVIE);
		Assert.assertEquals(Level.BASIC, quizMovieResult.getLevel());
		Assert.assertEquals(1, quizMovieResult.getCorrectAnswers());
		Assert.assertEquals(1, quizMovieResult.getQuestionsNumber());
		
	}
	@Test
	public void testBasicLevelLoseFromAdvancedLevel() throws IOException{
		StringBuilder builder = new StringBuilder(TestConstants.PAUL_PLAYER)
					.append(Constants.LINE_SEPARATOR).append(Constants.NUMBER_TO_PLAY).append(Constants.LINE_SEPARATOR)
					.append(Constants.NUMBER_TO_QUIZ_MOVIE).append(Constants.LINE_SEPARATOR).append(1)
					.append(Constants.LINE_SEPARATOR).append(Constants.NUMBER_TO_COME_BACK)
					.append(Constants.LINE_SEPARATOR).append(Constants.NUMBER_TO_RESUME)
					.append(Constants.LINE_SEPARATOR).append(Constants.NUMBER_TO_SAVE)
					.append(Constants.LINE_SEPARATOR).append(Constants.NUMBER_TO_EXIT);
		scanner = new Scanner(builder.toString());
		
		loginView.setScanner(scanner);
	
		loginView.main(null);
		
		ScorePlayer scorePlayer = scorePlayerDAO.findScorePlayer(TestConstants.PAUL_PLAYER).get();
		
		Assert.assertNotNull(scorePlayer);
		Assert.assertEquals(TestConstants.PAUL_PLAYER, scorePlayer.getPlayerName());
		
		QuizResult quizSoccerResult = scorePlayer.getQuizes().get(QuizType.SOCCER);
		Assert.assertEquals(Level.BASIC, quizSoccerResult.getLevel());
		Assert.assertEquals(0, quizSoccerResult.getCorrectAnswers());
		Assert.assertEquals(0, quizSoccerResult.getQuestionsNumber());
		
		QuizResult quizMovieResult = scorePlayer.getQuizes().get(QuizType.MOVIE);
		Assert.assertEquals(Level.BASIC, quizMovieResult.getLevel());
		Assert.assertEquals(0, quizMovieResult.getCorrectAnswers());
		Assert.assertEquals(1, quizMovieResult.getQuestionsNumber());		
	}
	
}
