package domain;

import java.util.HashSet;

public class AnswerPool {
	private static AnswerPool singleton;
	private HashSet<Answer> answers=new HashSet<Answer>();
	//We willen geen dubbele antwoorden opslaan, vandaar hashset
	
	
	public static AnswerPool getInstance(){
		if(singleton == null){
			singleton=new AnswerPool();
		}
		return singleton;
	}
	
	



	public HashSet<Answer> getAnswers() {
		return answers;
	}



	private void setAnswers(HashSet<Answer> answers) {
		this.answers = answers;
	}



	private AnswerPool() {
	
	}
	
	
	public void AddAnswer(Answer answer){
		this.getAnswers().add(answer);
	}
	
	public void removeAnswer(Answer answer){
		this.getAnswers().remove(answer);
	}

}
