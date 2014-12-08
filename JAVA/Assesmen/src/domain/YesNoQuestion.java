package domain;

import java.util.Arrays;
import java.util.HashSet;

public class YesNoQuestion extends Question {
	private static final String type = "Yes Or No Question";
	//Hier MOET Integer staan ipv int, als we willen werken met de factory met variabel aantal parameters
	//(nodig omdat YesNoQuestion een andere constructor heeft als MultipleChoiceQuestions);
	public YesNoQuestion(String question, Answer rightAnswer, Integer time) throws DomainException {
		super(question, rightAnswer, new HashSet<Answer>(Arrays.asList(new Answer("Yes"), new Answer("No"))), time);
	}

	@Override
	public String getType() {
		return type;
	}

}
