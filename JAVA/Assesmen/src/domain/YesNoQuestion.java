package domain;

import java.util.Arrays;
import java.util.HashSet;

public class YesNoQuestion extends Question {

	
	public YesNoQuestion(String question, Answer rightAnswer, int time) throws DomainException {
		super(question, rightAnswer, new HashSet<Answer>(Arrays.asList(new Answer("yes"), new Answer("no"))), time);
	}

}
