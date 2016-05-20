package br.com.quiz.view;

import static br.com.quiz.domain.valueobject.QuizTypeHelper.setFileName;

import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;

import org.junit.After;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.quiz.domain.valueobject.QuizType;
import br.com.quiz.player.domain.entity.ScorePlayer;
import br.com.quiz.player.domain.repository.IScorePlayerDAO;
import br.com.quiz.player.domain.repository.ScorePlayerDAO;
import br.com.quiz.player.infrastructure.ScorePlayerFile;
import br.com.quiz.useful.Constants;
import br.com.quiz.useful.TestConstants;

public class InvalidInputQuizTest {
	
	private Scanner scanner;
	private static LoginView loginView;
	private static IScorePlayerDAO scorePlayerDAO;

	
	@BeforeClass
	public static void initialSetup(){
		ScorePlayerFile.setScorePlayersFile("src/test/resources/br/com/quiz/view/scorePlayer.txt");
		setFileName(QuizType.SOCCER,TestConstants.SOCCER_QUESTIONS_FILE);
		scorePlayerDAO = new ScorePlayerDAO();
	}
	@After
	public void finalizeTest() throws IOException{
		scanner.close();
	}
	
	@Test()
	public void testInvalidNumberInputInMainView() throws IOException{
		StringBuilder builder = new StringBuilder(TestConstants.PETER_PLAYER)
								.append(Constants.LINE_SEPARATOR).append(9999).append(Constants.LINE_SEPARATOR)
								.append(Constants.NUMBER_TO_EXIT);
		scanner = new Scanner(builder.toString());
		
		loginView.setScanner(scanner);
		loginView.main(null);
		
		Optional<ScorePlayer> scorePlayerOptional = scorePlayerDAO.findScorePlayer(TestConstants.PETER_PLAYER);
		Assert.assertFalse(scorePlayerOptional.isPresent());
	}

	@Test()
	public void testStringInputInMainView() throws IOException{
		StringBuilder builder = new StringBuilder(TestConstants.PETER_PLAYER)
								.append(Constants.LINE_SEPARATOR).append("TESTTTT").append(Constants.LINE_SEPARATOR)
								.append(Constants.NUMBER_TO_EXIT);
		scanner = new Scanner(builder.toString());
		
		loginView.setScanner(scanner);
		loginView.main(null);
		
		Optional<ScorePlayer> scorePlayerOptional = scorePlayerDAO.findScorePlayer(TestConstants.PETER_PLAYER);
		Assert.assertFalse(scorePlayerOptional.isPresent());
	}
	
	@Test()
	public void testInvalidNumberInputInQuizView() throws IOException{
		StringBuilder builder = new StringBuilder(TestConstants.PETER_PLAYER)
								.append(Constants.LINE_SEPARATOR).append(Constants.NUMBER_TO_PLAY).append(Constants.LINE_SEPARATOR)
								.append(999).append(Constants.LINE_SEPARATOR).append(Constants.NUMBER_TO_COME_BACK)
								.append(Constants.LINE_SEPARATOR).append(Constants.NUMBER_TO_EXIT);
		scanner = new Scanner(builder.toString());
		
		loginView.setScanner(scanner);
		loginView.main(null);
		
		Optional<ScorePlayer> scorePlayerOptional = scorePlayerDAO.findScorePlayer(TestConstants.PETER_PLAYER);
		Assert.assertFalse(scorePlayerOptional.isPresent());
	}

	@Test()
	public void testStringInputInQuizView() throws IOException{
		StringBuilder builder = new StringBuilder(TestConstants.PETER_PLAYER)
								.append(Constants.LINE_SEPARATOR).append(Constants.NUMBER_TO_PLAY).append(Constants.LINE_SEPARATOR)
								.append("TESTTT").append(Constants.LINE_SEPARATOR).append(Constants.NUMBER_TO_COME_BACK)
								.append(Constants.LINE_SEPARATOR).append(Constants.NUMBER_TO_EXIT);
		scanner = new Scanner(builder.toString());
		
		loginView.setScanner(scanner);
		loginView.main(null);
		
		Optional<ScorePlayer> scorePlayerOptional = scorePlayerDAO.findScorePlayer(TestConstants.PETER_PLAYER);
		Assert.assertFalse(scorePlayerOptional.isPresent());
	}
	
	@Test()
	public void testStringInputInAnsweringQuestionInQuizView() throws IOException{
		StringBuilder builder = new StringBuilder(TestConstants.PETER_PLAYER)
								.append(Constants.LINE_SEPARATOR).append(Constants.NUMBER_TO_PLAY).append(Constants.LINE_SEPARATOR)
								.append(Constants.NUMBER_TO_QUIZ_SOCCER).append(Constants.LINE_SEPARATOR)
								.append("TESTTTTT").append(Constants.LINE_SEPARATOR)
								.append(2).append(Constants.LINE_SEPARATOR)
								.append(Constants.NUMBER_TO_COME_BACK)
								.append(Constants.LINE_SEPARATOR).append(Constants.NUMBER_TO_EXIT);
		scanner = new Scanner(builder.toString());
		
		loginView.setScanner(scanner);
		loginView.main(null);
		
		Optional<ScorePlayer> scorePlayerOptional = scorePlayerDAO.findScorePlayer(TestConstants.PETER_PLAYER);
		Assert.assertFalse(scorePlayerOptional.isPresent());
	}

}
