package domain;

import java.util.HashSet;

public interface QuestionSelectionbehaviour {
	public final int iterations = 200;
	public HashSet<Exercise> selectQuestions(int amount) throws DomainException;

}