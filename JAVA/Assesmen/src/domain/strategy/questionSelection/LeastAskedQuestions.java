package domain.strategy.questionSelection;

import java.util.HashSet;

import domain.DomainException;
import domain.Exercise;
import domain.ExercisePool;

public class LeastAskedQuestions extends ExercisePoolHandler implements
		QuestionSelectionBehaviour {

	public LeastAskedQuestions(ExercisePool exercisePool)throws DomainException {
		super(exercisePool);
	}
	
	@Override
	public HashSet<Exercise> selectQuestions(int amount) throws DomainException {
		if(getExercisePool().getUniqueExerciseSet().size() < amount)
			throw new DomainException("You need atleast: " + amount + " unique questions before you can create an evaluation");
		HashSet<Exercise> retSet = new HashSet<Exercise>(getExercisePool().getExercisePool().subList(getExercisePool().getExercisePool().size() - amount, getExercisePool().getExercisePool().size()));
		for (Exercise exercise : retSet) {
			exercise.increaseTimesPicked();
		}
		super.getExercisePool().sortList();
		return retSet;
	}

}
