package domain;

import java.util.ArrayList;
import java.util.Map.Entry;

//TODO we hebben hier ook een time remaining nodig denk ik

public abstract class Evaluation {
	ArrayList<Entry<Exercise, Answer>> exercises;
	int indexCurrentExercise;
	int timeAllowed;
	ScoreBehaviour scoreBehaviour;

	public Evaluation(ArrayList<Entry<Exercise, Answer>> exercises)
			throws DomainException {
		setExercises(exercises);
		setIndexCurrentExercise(0);
		setTimeAllowed(0); // time allowed = sum of all times? Ik vermoed van wel.
	}

	public ScoreBehaviour getScoreBehaviour() {
		return scoreBehaviour;
	}

	public void setScoreBehaviour(ScoreBehaviour scoreBehaviour) {
		this.scoreBehaviour = scoreBehaviour;
	}

	public boolean hasNextQuestion() {
		return (indexCurrentExercise + 1 <= exercises.size());
	}

	public boolean hasPreviousQuestion() {
		return (indexCurrentExercise - 1 >= 0);
	}

	public Exercise getNextExercise() throws DomainException {
		if (!hasNextQuestion())
			throw new DomainException("Out of bound");
		indexCurrentExercise++;
		return getExercises().get(indexCurrentExercise).getKey();
	}

	public int getNumberOfQuestions() {
		return getExercises().size();
	}

	public Exercise getPreviousExercise() throws DomainException {
		if (!hasNextQuestion())
			throw new DomainException("Out of bound");
		indexCurrentExercise++;
		return getExercises().get(indexCurrentExercise).getKey();
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

	
	
	public int getTimeAllowed() {
		return timeAllowed;
	}

	public void setTimeAllowed(int timeAllowed) {
		this.timeAllowed = timeAllowed;
	}

	public abstract String getSpecificFeedback();

	public String getFeedback() {
		return null; // handle feedback
	}
	
	public int calculateScore(){
		return scoreBehaviour.calculateScore(); //lokaal handelen (params mee geven) of in controller? Ik zou vanuit de controller 
												// op de test roepen: checkTest(), en de test genereert dan een objectje met daarin
												// de score, de feedback, het aantal juiste vragen op bepaalde category etc.
	}
	
	//generateResult
	

}
