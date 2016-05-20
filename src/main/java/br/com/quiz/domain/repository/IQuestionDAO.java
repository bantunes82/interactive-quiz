package br.com.quiz.domain.repository;

import java.util.List;

import br.com.quiz.domain.valueobject.Level;
import br.com.quiz.domain.valueobject.QuestionVO;
import br.com.quiz.domain.valueobject.QuizType;

@FunctionalInterface
public interface IQuestionDAO {
	
	public List<QuestionVO> findQuestionVOs(QuizType quizType, Level level);
	
}
