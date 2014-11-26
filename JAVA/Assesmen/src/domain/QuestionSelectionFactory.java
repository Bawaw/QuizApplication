package domain;

import java.util.HashSet;

public class QuestionSelectionFactory {
	private static QuestionSelectionFactory singleton;
	private QuestionSelectionbehaviour questionSelectionbehaviour;

	public static QuestionSelectionFactory getInstance(
			QuestionSelectionbehaviour qsb) throws DomainException {
		if (singleton == null) {
			singleton = new QuestionSelectionFactory(qsb);
			return singleton;
		} else {
			singleton.setQuestionSelectionbehaviour(qsb);
			return singleton;
		}

	}

	private QuestionSelectionFactory(QuestionSelectionbehaviour qsb)
			throws DomainException {
		this.setQuestionSelectionbehaviour(qsb);
	}

	public QuestionSelectionbehaviour getQuestionSelectionbehaviour() {
		return questionSelectionbehaviour;
	}

	private void setQuestionSelectionbehaviour(
			QuestionSelectionbehaviour questionSelectionbehaviour)
			throws DomainException {
		if (questionSelectionbehaviour == null) {
			throw new DomainException("Invalid questionSelectionbehaviour");
		}
		this.questionSelectionbehaviour = questionSelectionbehaviour;
	}

	// HashSet, we willen niet dat de output dubbels bevat
	public HashSet<Exercise> selectQuestions(int amount) throws DomainException {
		return this.getQuestionSelectionbehaviour().selectQuestions(amount);
	}

}