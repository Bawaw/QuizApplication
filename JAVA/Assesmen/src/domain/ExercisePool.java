package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;

public class ExercisePool {
	private ArrayList<Exercise> exercises;
	public ExercisePool() {
		exercises = new ArrayList<Exercise>();
	}
	
	//nodig?
	public void editExercise(Exercise oldExercise,Exercise newExercise) throws DomainException{
		if(newExercise == null)
			throw new DomainException("value can not be null");
		removeExercise(oldExercise);
		addExercise(newExercise);
		Collections.sort(exercises);
	}
	
	public void removeWithCategory(Category category) throws DomainException{
		Iterator<Exercise> it=getExercisePool().iterator();
		while(it.hasNext()){
			Exercise ex=it.next();
			if(ex.getCategory().equals(category)){
				it.remove();
			}
		}
	}
	
	public void removeFeedback(Feedback feedback) throws DomainException{
		for (Exercise e : getExercisePool()) {
			if(e.getFeedback().equals(feedback))
				e.setFeedback(new Feedback(Feedback.STANDARD_FEEDBACK));
		}
	}
	
	public void addExercise(Exercise exercise) throws DomainException{
		if(exercise == null)
			throw new DomainException("value can not be null");
		exercises.add(exercise);
		Collections.sort(exercises);
	}
	
	
	public void removeExercise(Exercise exercise) throws DomainException{
		int index=this.getIndexExercise(exercise);
		this.exercises.remove(index);
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
	
	public HashSet<Category> getCategories(){
		HashSet<Category> returnList = new HashSet<Category>();
		for (Exercise exercise : getExercisePool()) {
				returnList.add(exercise.getCategory());
		}
		return returnList;
	}
	
	public ArrayList<Exercise> getExerciseByQuestion(String question){
		ArrayList<Exercise> retList = new ArrayList<Exercise>();
		for(Exercise ex: getExercisePool()){
			if(ex.getQuestion().getQuestion().equals(question)){
			retList.add(ex);
			}
		}
		return retList;
	}
	
	public void sortList(){
		Collections.sort(this.getExercisePool());
	}
	
	private int getIndexExercise(Exercise ex) throws DomainException{
		int output=-1;
		boolean found=false;
		for(int i=0;i<exercises.size() && !found ; i++){
			if(ex.uniqueEquals(exercises.get(i))){
				output=i;
				found=true;
			}
		}
		if(output==-1){
			throw new DomainException("Exercise not found");
		}
		return output;
	}
	
	public HashSet<Exercise> getUniqueExerciseSet(){
		return new HashSet<Exercise>(getExercisePool());
	} 
	
}
