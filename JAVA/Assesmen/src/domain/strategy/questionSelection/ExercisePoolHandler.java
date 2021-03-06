package domain.strategy.questionSelection;

import domain.DomainException;
import domain.ExercisePool;

public class ExercisePoolHandler {
	private ExercisePool exercisePool;
	
	public ExercisePoolHandler(ExercisePool exercisePool) throws DomainException{
		this.setExercisePool(exercisePool);
	}

	public ExercisePool getExercisePool() {
		return exercisePool;
	}

	private void setExercisePool(ExercisePool exercisePool) throws DomainException {
		if(exercisePool == null){
			throw new DomainException("exercisePool cannot be null!");
		}
		this.exercisePool = exercisePool;
	}
}
