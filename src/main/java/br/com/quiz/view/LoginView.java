package br.com.quiz.view;

import java.io.IOException;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.quiz.player.domain.entity.ScorePlayer;
import br.com.quiz.player.domain.service.IScorePlayerService;
import br.com.quiz.player.domain.service.ScorePlayerService;

public class LoginView {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginView.class);
	private static Scanner scanner = new Scanner(System.in);
	
	@SuppressWarnings("common-java:InsufficientBranchCoverage")
	private LoginView(){
	}
	
	public static void main(String[] args) throws IOException {
		try {
			login();
		} catch (Exception e) {
			LOGGER.error("Sorry a unexpected error occurred!!! Restart the quiz again \n" +e);
			throw e;
		}
	}

	private static void login(){
		MainView principalView = new MainView(scanner);
		IScorePlayerService scorePlayerService = new ScorePlayerService();
		
		LOGGER.info("Please type your name: ");

		String playerName = scanner.nextLine();
		ScorePlayer scorePlayer = scorePlayerService.findScorePlayer(playerName.toUpperCase());
		principalView.menu(scorePlayer);
	}
	
	protected static void setScanner(Scanner scanner) {
		LoginView.scanner = scanner;
	}
	
	

}
