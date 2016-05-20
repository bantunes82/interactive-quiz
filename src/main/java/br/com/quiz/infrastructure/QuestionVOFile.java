package br.com.quiz.infrastructure;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import br.com.quiz.domain.valueobject.Level;
import br.com.quiz.domain.valueobject.QuestionVO;
import br.com.quiz.domain.valueobject.QuizType;
import br.com.quiz.player.exception.FileProblemException;
import br.com.quiz.useful.Constants;

public class QuestionVOFile {
	
	@SuppressWarnings("common-java:InsufficientBranchCoverage")
	private QuestionVOFile(){
	}

	public static List<QuestionVO> findQuestionVOs(QuizType quizType,
			Level level){
		try(Stream<String> stream = Files.lines(Paths.get(quizType.getFileName()))) 
		{		
			return	 stream
			 	 	 .filter(s -> s.contains(level.toString()))
			 	 	 .collect(Collectors.mapping(QuestionVOFile::convertStringToQuestionVO, Collectors.toList()));
		} catch (IOException e) {
			throw new FileProblemException("Error reading the file: " + quizType.getFileName(), e);			 
		} 
	}
	
	@SuppressWarnings("squid:UnusedPrivateMethod")
	private static QuestionVO convertStringToQuestionVO(String line) {
		String[] elements = line.split(Constants.CHARACTER_SEPARATOR);

		QuestionVO questionVO = new QuestionVO(elements[0],Integer.valueOf(elements[2]));
		
		try(Stream<String> stream = Arrays.stream(elements))
		{
			   stream	
			  .skip(3)
			  .forEach(questionVO::addPossibleAnswer);
		
			return questionVO;	
		}
	}

}
