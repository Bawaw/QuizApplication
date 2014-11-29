package domain.strategy.questionSelection;

import java.util.HashSet;

import domain.DomainException;
import domain.Exercise;

public interface QuestionSelectionBehaviour {
	public final int iterations = 200;
	public HashSet<Exercise> selectQuestions(int amount) throws DomainException;

}
