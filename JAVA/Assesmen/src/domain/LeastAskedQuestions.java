package domain;

import java.util.HashSet;

public class LeastAskedQuestions extends ExercisePoolHandler implements
		QuestionSelectionbehaviour {

	public LeastAskedQuestions(ExercisePool exercisePool)throws DomainException {
		super(exercisePool);
	}

	
	@Override
	public HashSet<Question> selectQuestions(int amount) {
		// TODO IMPLEMENTATION
		return null;
	}

}
