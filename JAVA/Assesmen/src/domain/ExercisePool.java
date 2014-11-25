package domain;

import java.util.ArrayList;
import java.util.Collections;

public class ExercisePool {
	ArrayList<Exercise> exercises;

	public ExercisePool() {
		exercises = new ArrayList<Exercise>();
	}
	
	//nodig?
	public void editExercise(Exercise oldExercise,Exercise newExercise) throws DomainException{
		if(newExercise == null)
			throw new DomainException("value can not be null");
		exercises.remove(oldExercise);
		exercises.add(newExercise);
		Collections.sort(exercises);
	}
	
	public void addExercise(Exercise exercise) throws DomainException{
		if(exercise == null)
			throw new DomainException("value can not be null");
		if(exercises.contains(exercise))
			throw new DomainException("already exists");
		exercises.add(exercise);
		Collections.sort(exercises);
	}
	
	//problem
	public boolean removeExercise(Exercise exercise){
		return exercises.remove(exercise);
	}
	
	public ArrayList<Exercise> getExercisePool(){
		return exercises;
	}
	
	public ArrayList<Exercise> getListByCategory(Category category){
		ArrayList<Exercise> returnList = new ArrayList<Exercise>();
		for (Exercise exercise : getExercisePool()) {
			if(exercise.getCategory().equals(category))
				returnList.add(exercise);
		}
		return returnList;
	}
	
	public ArrayList<Category> getCategories(){
		ArrayList<Category> returnList = new ArrayList<Category>();
		for (Exercise exercise : getExercisePool()) {
				returnList.add(exercise.getCategory());
		}
		return returnList;
	}
}
