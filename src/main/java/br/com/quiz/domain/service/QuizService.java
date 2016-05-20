package br.com.quiz.domain.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.com.quiz.domain.repository.IQuestionDAO;
import br.com.quiz.domain.repository.QuestionDAO;
import br.com.quiz.domain.valueobject.Level;
import br.com.quiz.domain.valueobject.QuestionVO;
import br.com.quiz.domain.valueobject.QuizType;
import br.com.quiz.domain.valueobject.QuizVO;

public class QuizService implements IQuizService{
	
	private IQuestionDAO quesionRepository = new QuestionDAO();
	

	@Override
	public QuizVO generateQuiz(QuizType quizType, Level level){
		List<QuestionVO> questionVOs = quesionRepository.findQuestionVOs(quizType,level);
		return raffleQuiz(quizType,level,questionVOs);
	}
	
	private QuizVO raffleQuiz(QuizType quizType, Level level, List<QuestionVO> questions){
		int questionNumber = 5;
		
	  	List<QuestionVO> raffleQuestions = new ArrayList<>();
		
		questionNumber = questionNumber<=questions.size()?questionNumber:questions.size();
		
		Random random = new Random();
		for(int i=0;i<questionNumber;i++){
			int randomNumber = random.nextInt(questions.size());
			raffleQuestions.add(questions.remove(randomNumber));
		}
		
		return new QuizVO(quizType, level, raffleQuestions);
	}
	

}
