package br.com.quiz.view;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.quiz.domain.service.IQuizService;
import br.com.quiz.domain.service.QuizService;
import br.com.quiz.domain.valueobject.QuestionVO;
import br.com.quiz.domain.valueobject.QuizType;
import br.com.quiz.domain.valueobject.QuizVO;
import br.com.quiz.player.domain.entity.QuizResult;
import br.com.quiz.player.domain.entity.ScorePlayer;
import br.com.quiz.useful.Constants;

public class QuizView {

	private Scanner scanner;
	private IQuizService quizService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(QuizView.class);
	
	public QuizView(Scanner scanner) {
		super();
		this.scanner = scanner;
		this.quizService = new QuizService();
	}

	public void choiceQuizType(ScorePlayer scorePlayer){
		LOGGER.info(Constants.LINE_SEPARATOR+"Please choice the Quiz type you want to play ");
		LOGGER.info(Constants.NUMBER_TO_QUIZ_SOCCER +" - "+ QuizType.SOCCER);
		LOGGER.info(Constants.NUMBER_TO_QUIZ_MOVIE +" - "+ QuizType.MOVIE);
		LOGGER.info(Constants.NUMBER_TO_COME_BACK+" - Come back");

		int code = 3;
		String line;
		try {
			line = scanner.nextLine();
			code = Integer.parseInt(line);
		} catch (NumberFormatException exception) {
			LOGGER.info("Please type a number for the correct option");
			choiceQuizType(scorePlayer);
		}

		switch (code) {
		case 1:
			prepareToPlay(scorePlayer,QuizType.SOCCER);
			break;
		case 2:
			prepareToPlay(scorePlayer,QuizType.MOVIE);
			break;
		case 3:
			return;
		default:
			LOGGER.info("Please type a correct option");
			choiceQuizType(scorePlayer);
			break;
		}
		
	}

	private void prepareToPlay(ScorePlayer scorePlayer, QuizType quizType){
		QuizResult quizSoccerPlayerResult = scorePlayer.getQuizes().get(quizType);
		QuizVO quiz = quizService.generateQuiz(quizType,quizSoccerPlayerResult.getNextLevel());
		
		QuizResult quizResult = play(quiz);
		scorePlayer.getQuizes().put(quizType, quizResult);
		
		choiceQuizType(scorePlayer);
	}

	private QuizResult play(QuizVO quiz) {
		
		QuizResult newQuizResult = new QuizResult(quiz.getQuizLevel(),quiz.getQuestions().size());
		
		StringBuilder builder = new StringBuilder();
		builder.append(Constants.LINE_SEPARATOR).append(quiz.getQuizType()).append(" quiz level ").append(quiz.getQuizLevel()).append(" will start "+Constants.LINE_SEPARATOR);
		LOGGER.info(builder.toString());
		
		int indexQuestionNumber=1;
		quiz.getQuestions().stream()
						   .forEach(questionVO -> showQuestion(newQuizResult, questionVO, indexQuestionNumber));
		
		builder = new StringBuilder();
		builder.append("You ").append(newQuizResult.status()).append(" ").append(quiz.getQuizType()).append(" quiz level ").append(newQuizResult.getLevel()).
				append(" !!!!! you hit ").append(newQuizResult.getCorrectAnswers()).append(" question(s) "+Constants.LINE_SEPARATOR);
		LOGGER.info(builder.toString());
		
		return newQuizResult;
		
	}

	private void showQuestion(QuizResult quizResult,QuestionVO question, int indexQuestionNumber)
	{
		LOGGER.info(indexQuestionNumber +" - " +question);

		int code=0;
		try {
			code = Integer.parseInt(scanner.nextLine());
		} catch (NumberFormatException exception) {
			LOGGER.info("Please type a number for your answer");
			showQuestion(quizResult, question,indexQuestionNumber);
		} 

		if(question.correctAnswer(code)){
			LOGGER.info("Correct Answer !!!!!!  :)   "+Constants.LINE_SEPARATOR);
			quizResult.incrementCorrectAnswers();
		}else{
			LOGGER.info("Wrong Answer !!!!!!  :(  "+Constants.LINE_SEPARATOR);
		}	
	}
}
