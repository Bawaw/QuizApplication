package domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class RandomQuestions extends ExercisePoolHandler implements
		QuestionSelectionbehaviour {

	public RandomQuestions(ExercisePool exercisePool) throws DomainException {
		super(exercisePool);
	}

	@Override
	public HashSet<Exercise> selectQuestions(int amount) throws DomainException {
		if(getExercisePool().getExercisePool().size() < amount)
			throw new DomainException("You need atleast: " + amount + " questions before you can create an evaluation");
		Random r = new Random();
		HashSet<Exercise> retSet = new HashSet<Exercise>();

		for (int i = 0; i < amount; i++){
			boolean valid = false;
			for (int j = 0; j < QuestionSelectionbehaviour.iterations && !valid; j++) {
				int index = r.nextInt(getExercisePool().getExercisePool().size()); //not sure if works since internal clock 
				Exercise e = getExercisePool().getExercisePool().get(index);
				if(!retSet.contains(e)){
					retSet.add(e);
					e.increaseTimesPicked();
					valid = true;
				}
			}	
		}
		return retSet;
	}

}
