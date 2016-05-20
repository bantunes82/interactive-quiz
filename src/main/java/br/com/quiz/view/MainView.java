package br.com.quiz.view;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.quiz.player.domain.entity.ScorePlayer;
import br.com.quiz.player.domain.service.IScorePlayerService;
import br.com.quiz.player.domain.service.ScorePlayerService;
import br.com.quiz.useful.Constants;

public class MainView {
	
	private Scanner scanner;
	private QuizView quizView;
	private IScorePlayerService scorePlayerService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MainView.class);
	

	public MainView(Scanner scanner) {
		super();
		this.scanner = scanner;
		this.quizView = new QuizView(scanner);
		this.scorePlayerService= new ScorePlayerService();
	}

	public void menu(ScorePlayer scorePlayer){
		LOGGER.info(scorePlayer.getPlayerName() +" please choice a option in the menu");
		LOGGER.info(Constants.NUMBER_TO_PLAY+" - To Play");
		LOGGER.info(Constants.NUMBER_TO_RESUME+" - Resume");
		LOGGER.info(Constants.NUMBER_TO_SAVE+" - Save");
		LOGGER.info(Constants.NUMBER_TO_EXIT+" - Exit");
    
		String line;
		int code=0;
		try {
			line = scanner.nextLine();
			code = Integer.parseInt(line);
		} catch (NumberFormatException exception) {
			LOGGER.info("Please type a number for the correct option");
			menu(scorePlayer);
		}

		switch (code) {
		case 1:
			quizView.choiceQuizType(scorePlayer);
			menu(scorePlayer);
			break;
		case 2:
			LOGGER.info("Resume: "+Constants.LINE_SEPARATOR+scorePlayer);
			menu(scorePlayer);
			break;
		case 3:
			scorePlayerService.saveScorePayer(scorePlayer);
			LOGGER.info("Saved successfully !!!");
			menu(scorePlayer);
			break;
		case 4:
			LOGGER.info("Good Bye !!!");
			return;
		default:
			LOGGER.info("Please type a correct option");
			break;
		}
	}

}
