package domain;

import java.util.ArrayList;
import java.util.Map.Entry;

import domain.enums.ScoreBehaviourType;

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

	public ScoreBehaviour getScoreBehaviour() {
		return scoreBehaviour;
	}

	public void setScoreBehaviour(ScoreBehaviourType scoreBehaviourType) throws DomainException {
		this.scoreBehaviour = ScoreBehaviourFactory.create(this,scoreBehaviourType);
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

	public int calculateScore() {
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

}