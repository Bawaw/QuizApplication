package domain;

import java.util.HashSet;

public class MultipleChoiceQuestion extends Question {
	private static final String type = "Multiple Choice Question";

	// Hier MOET Integer staan ipv int, als we willen werken met de factory met
	// variabel aantal parameters
	// (nodig omdat YesNoQuestion een andere constructor heeft als
	// MultipleChoiceQuestions);
	public MultipleChoiceQuestion(String question, Answer rightAnswer,
			HashSet<Answer> option, Integer time) throws DomainException {
		super(question, rightAnswer, option, time);
	}

	public void addAnswer(Answer a) throws DomainException {
		if (!this.getOptions().contains(a) && this.getOptions().size() + 1 > 5) {
			throw new DomainException("Only 5 options allowed");
		}
		a.increaseTimesPicked();
		this.getOptions().add(a);
	}

	@Override
	public String getType() {
		return type;
	}

}
