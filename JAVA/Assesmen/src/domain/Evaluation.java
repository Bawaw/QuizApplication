package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import domain.enums.ScoreBehaviourType;
import domain.factory.ScoreBehaviourFactory;
import domain.strategy.score.ScoreBehaviour;

public abstract class Evaluation {
	ArrayList<Entry<Exercise, Answer>> exercises;
	int indexCurrentExercise;
	int timeAllowed;
	ScoreBehaviour scoreBehaviour;

	public Evaluation(ArrayList<Entry<Exercise, Answer>> exercises,
			ScoreBehaviourType scoreBehaviourType) throws DomainException {
		setExercises(exercises);
		setIndexCurrentExercise(0);
		setScoreBehaviour(scoreBehaviourType);
		setTimeAllowed();
	}
	
	public void increaseIndex(){
		if(hasNextQuestion()){
			this.setIndexCurrentExercise(this.getIndexCurrentExercise()+1);
		}
	}
	
	public void decreaseIndex(){
		if(hasPreviousQuestion()){
			this.setIndexCurrentExercise(this.getIndexCurrentExercise()-1);
		}
	}
	
	
	private Exercise getCurrentExercise(){
		return exercises.get(indexCurrentExercise).getKey();
	}

	public Answer getCurrentSelectedAnswer(){
		return exercises.get(indexCurrentExercise).getValue();
	}
	
	public List<Answer> getAnswers(){
		return new ArrayList<Answer>(this.getCurrentExercise().getQuestion().getOptions());
	}
	
	public String getCurrentQuestion(){
		return getCurrentExercise().getQuestion().getQuestion();
	}
	
	public ScoreBehaviour getScoreBehaviour() {
		return scoreBehaviour;
	}

	public void setScoreBehaviour(ScoreBehaviourType scoreBehaviourType) throws DomainException {
		this.scoreBehaviour = ScoreBehaviourFactory.create(this,scoreBehaviourType);
	}

	public boolean hasNextQuestion() {
		return (indexCurrentExercise <= exercises.size()-2);
	}

	public boolean hasPreviousQuestion() {
		return (indexCurrentExercise - 1 >= 0);
	}

	public int getNumberOfQuestions() {
		return getExercises().size();
	}


	public ArrayList<Entry<Exercise, Answer>> getExercises() {
		return exercises;
	}

	private void setExercises(ArrayList<Entry<Exercise, Answer>> exercises)
			throws DomainException {
		if (exercises == null)
			throw new DomainException("exercises can not be null");
		if (exercises.size() == 0)
			throw new DomainException("Atleast one exercises expected");
		this.exercises = exercises;
	}

	public int getIndexCurrentExercise() {
		return indexCurrentExercise;
	}

	private void setIndexCurrentExercise(int indexCurrentExercise) {
		this.indexCurrentExercise = indexCurrentExercise;
	}

	public int questionNumber(){
		return this.getIndexCurrentExercise()+1;
	}
	
	public void answerCurrentQuestion(Answer a){
		exercises.get(indexCurrentExercise).setValue(a);
	}
	
	public int getTimeAllowed() {
		return timeAllowed;
	}

	public void setTimeAllowed() {
		int totalTime = 0;
		for (Entry<Exercise, Answer> ex : getExercises()) {
			totalTime += ex.getKey().getQuestion().getTime();
		}
	}

	public abstract String getSpecificFeedback();

	public String getFeedback() {
		return null; // handle feedback
	}

	public long calculateScore() {

		return scoreBehaviour.calculateScore(); // lokaal handelen (params mee
												// geven) of in controller? Ik
												// zou vanuit de controller
												// op de test roepen:
												// checkTest(), en de test
												// genereert dan een objectje
												// met daarin
												// de score, de feedback, het
												// aantal juiste vragen op
												// bepaalde category etc.
	}

	// generateResult

	
	@Override
	public String toString(){
		String output ="";
		for(Entry<Exercise, Answer> entry:exercises){
			String question=entry.getKey().getQuestion().getQuestion();
			Answer a=entry.getValue();
			output+=question + " You answered : "+a+"\n";
		}
		
		return output;
	}
}
