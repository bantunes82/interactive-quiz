package br.com.quiz.view;

import static br.com.quiz.domain.valueobject.QuizTypeHelper.setFileName;

import java.io.IOException;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.quiz.domain.valueobject.QuizType;
import br.com.quiz.player.exception.FileProblemException;
import br.com.quiz.player.infrastructure.ScorePlayerFile;
import br.com.quiz.useful.Constants;
import br.com.quiz.useful.TestConstants;

public class InvalidQuizesAndScorePlayerFileTest {
	
	private Scanner scanner;
	private static LoginView loginView;
	

	@Before
	public void initializeTest() throws IOException{
		ScorePlayerFile.setScorePlayersFile("src/test/resources/br/com/quiz/view/scorePlayer.txt");
		setFileName(QuizType.MOVIE,TestConstants.MOVIE_QUESTIONS_FILE);
		setFileName(QuizType.SOCCER,TestConstants.SOCCER_QUESTIONS_FILE);
	}
	
	@After
	public void finalizeTest() throws IOException{
		scanner.close();
	}
	
	@Test(expected=FileProblemException.class)
	public void testInvalidMovieQuizFile() throws IOException{
		setFileName(QuizType.MOVIE,"src/test/resources/br/com/quiz/view/movieQuestionsssss.txt");
		StringBuilder builder = new StringBuilder("John")
				.append(Constants.LINE_SEPARATOR)
				.append(Constants.NUMBER_TO_PLAY)
				.append(Constants.LINE_SEPARATOR)
				.append(Constants.NUMBER_TO_QUIZ_MOVIE);
		scanner = new Scanner(builder.toString());
		
		loginView.setScanner(scanner);
		loginView.main(null);
	}
	
	@Test(expected=FileProblemException.class)
	public void testInvalidSoccerQuizFile() throws IOException{
		setFileName(QuizType.SOCCER,"src/test/resources/br/com/quiz/view/soccerQuestionsssss.txt");
		StringBuilder builder = new StringBuilder("John")
				.append(Constants.LINE_SEPARATOR)
				.append(Constants.NUMBER_TO_PLAY)
				.append(Constants.LINE_SEPARATOR)
				.append(Constants.NUMBER_TO_QUIZ_SOCCER);
		scanner = new Scanner(builder.toString());
		
		loginView.setScanner(scanner);
		loginView.main(null);
	}
	
	@Test(expected=FileProblemException.class)
	public void testInvalidScorePlayerFile() throws IOException{
		ScorePlayerFile.setScorePlayersFile("src/test/resources/br/com/quiz/view/scorePlayerrrrr.txt");
		StringBuilder builder = new StringBuilder("John")
				.append(Constants.LINE_SEPARATOR)
				.append(Constants.NUMBER_TO_SAVE)
				.append(Constants.LINE_SEPARATOR)
				.append(Constants.NUMBER_TO_QUIZ_SOCCER);

		scanner = new Scanner(builder.toString());
		
		loginView.setScanner(scanner);
		loginView.main(null);
	}
	
}
