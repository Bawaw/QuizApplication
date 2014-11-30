package domain;

import java.util.HashSet;

public class MultipleChoiceQuestion extends Question {

	public MultipleChoiceQuestion(String question, Answer rightAnswer,HashSet<Answer> option, int time) throws DomainException {
		super(question, rightAnswer, option, time);
	}
	
	
	public void addAnswer(Answer a) throws DomainException{
		if(!this.getOptions().contains(a) && this.getOptions().size()+1>5){
			throw new DomainException("Only 5 options allowed");
		}	
		a.increaseTimesPicked();
		this.getOptions().add(a);
	}
	
	
}
