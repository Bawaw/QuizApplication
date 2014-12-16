package domain.strategy.questionSelection;

import java.util.HashSet;
import java.util.Random;

import domain.DomainException;
import domain.Exercise;
import domain.ExercisePool;

public class RandomQuestions extends ExercisePoolHandler implements
		QuestionSelectionBehaviour {

	public RandomQuestions(ExercisePool exercisePool) throws DomainException {
		super(exercisePool);
	}

	@Override
	public HashSet<Exercise> selectQuestions(int amount) throws DomainException {
		if(getExercisePool().getUniqueExerciseSet().size() < amount)
			throw new DomainException("You need atleast: " + amount + " unique questions before you can create an evaluation");
		Random r = new Random();
		HashSet<Exercise> retSet = new HashSet<Exercise>();

		for (int i = 0; i < amount; i++){
			boolean valid = false;
			for (int j = 0; j < QuestionSelectionBehaviour.iterations && !valid; j++) {
				int index = r.nextInt(getExercisePool().getExercisePool().size()); 
				Exercise e = getExercisePool().getExercisePool().get(index);
				if(!retSet.contains(e)){
					retSet.add(e);
					e.increaseTimesPicked();
					valid = true;
				}
			}	
		}
		super.getExercisePool().sortList();
		return retSet;
	}

}
