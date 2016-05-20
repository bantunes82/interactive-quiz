package br.com.quiz.domain.repository;

import java.util.List;

import br.com.quiz.domain.valueobject.Level;
import br.com.quiz.domain.valueobject.QuestionVO;
import br.com.quiz.domain.valueobject.QuizType;
import br.com.quiz.infrastructure.QuestionVOFile;

public class QuestionDAO implements IQuestionDAO{
	
	@Override
	public List<QuestionVO> findQuestionVOs(QuizType quizType,Level level){
		return QuestionVOFile.findQuestionVOs(quizType,level);
	}

}
