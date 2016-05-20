package br.com.quiz.domain.valueobject;

public enum QuizType {
	
	SOCCER("src/main/resources/soccerQuestions.txt"),

	MOVIE("src/main/resources/movieQuestions.txt");
	
	private String fileName;
	
	private QuizType(String fileName){
		this.fileName = fileName;
	}

	public String getFileName() {
		return fileName;
	}

	void setFileName(String fileName) {
		this.fileName = fileName;
	}
		

}
