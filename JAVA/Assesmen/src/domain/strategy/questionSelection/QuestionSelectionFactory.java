package domain.strategy.questionSelection;

import java.util.HashSet;

import domain.DomainException;
import domain.Exercise;

public class QuestionSelectionFactory {
	private static QuestionSelectionFactory singleton;
	private QuestionSelectionBehaviour questionSelectionbehaviour;

	public static QuestionSelectionFactory getInstance(
			QuestionSelectionBehaviour qsb) throws DomainException {
		if (singleton == null) {
			singleton = new QuestionSelectionFactory(qsb);
			return singleton;
		} else {
			singleton.setQuestionSelectionbehaviour(qsb);
			return singleton;
		}

	}

	private QuestionSelectionFactory(QuestionSelectionBehaviour qsb)
			throws DomainException {
		this.setQuestionSelectionbehaviour(qsb);
	}

	public QuestionSelectionBehaviour getQuestionSelectionbehaviour() {
		return questionSelectionbehaviour;
	}

	private void setQuestionSelectionbehaviour(
			QuestionSelectionBehaviour questionSelectionbehaviour)
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
