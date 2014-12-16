package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class ExercisePool {
	private ArrayList<Exercise> exercises;
	public ExercisePool() {
		exercises = new ArrayList<Exercise>();
	}
	
	
	public void updateExercise(List<Exercise> newExercises) throws DomainException{
		Question q=null;
		if(newExercises.size()>0){
		
			q=newExercises.get(0).getQuestion();
		}
	
		ArrayList<Exercise> oldExercises=this.getExerciseByQuestion(q.getQuestion());
		boolean continu=true;
		
		Iterator<Exercise> itOld=oldExercises.iterator();
		Iterator<Exercise> itNew=newExercises.iterator();
		
		while(itOld.hasNext()){
			Exercise currentEx=itOld.next();
			
			while(itNew.hasNext() && continu){
				Exercise ex=itNew.next();
				//if we still want the "old" exercise, update the fields and remove it from the oldExercises-array. (at the end the
				//oldExercises-array will have only exercises that we no longer want.
				
				//Remove the similar question also from the newExercise-array. In the end the newExercise array will contain exercises we still
				//need to add.
				if(currentEx.uniqueEquals(ex)){
					currentEx.setQuestion(ex.getQuestion());
					currentEx.setScore(ex.getScore());
					currentEx.setCategory(ex.getCategory());
					currentEx.setFeedback(ex.getFeedback());
					itOld.remove();
					itNew.remove();
					continu=false;
				}
			}
			continu=true;
		}
		
		//remove from exercisePool all exercises still in oldExercises
		for(Exercise ex:oldExercises){
			this.removeExercise(ex);
		}
		//add to exercisePool all exercises still in newexercise
		for(Exercise ex:newExercises){
			this.addExercise(ex);
		}
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
		sortList();
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
	
	public void removeSimilarExercise(Exercise ex){
		String QuestionString =ex.getQuestion().getQuestion();
		
		Iterator<Exercise> it=this.getExercisePool().iterator();
		while(it.hasNext()){
			Exercise e=it.next();
			String qS=e.getQuestion().getQuestion();
			if(QuestionString.equals(qS)){
				it.remove();
			}
		}
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
		HashSet<Exercise> out=new HashSet<Exercise>(getExercisePool());
		return out;
	} 
	
	public HashSet<Exercise> getUniqueExerciseSetWithCategory(Category c){
		HashSet<Exercise> output=new HashSet<Exercise>();
		for(Exercise ex:this.getExercisePool()){
			if(ex.hasCategory(c)){
				output.add(ex);
			}
		}
		return output;
	}
	

	
}
