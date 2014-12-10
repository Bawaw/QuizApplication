package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import domain.enums.ScoreBehaviourType;
import domain.factory.ScoreBehaviourFactory;
import domain.strategy.score.ScoreBehaviour;

public abstract class Evaluation {
	private ArrayList<Entry<Exercise, Answer>> exercises;
	private int indexCurrentExercise;
	private int timeAllowed;
	private int remainingTime;
	private ScoreBehaviour scoreBehaviour;
	private EvaluationReport evaluationReport;

	public Evaluation(ArrayList<Entry<Exercise, Answer>> exercises,
			ScoreBehaviourType scoreBehaviourType) throws DomainException {
		setExercises(exercises);
		setIndexCurrentExercise(0);
		setScoreBehaviour(scoreBehaviourType);
		setTimeAllowed();
		setRemainingTime(this.getTimeAllowed());
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

	public int getPointsCurrentExercise(){
		return this.getCurrentExercise().getScore();
	}
	
	public String getCategoryCurrentExercise(){
		return this.getCurrentExercise().getCategory().getName();
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
		this.timeAllowed=totalTime;
	}



	public abstract String getSpecificFeedback();

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

	
	private EvaluationReport generateReport(){
		EvaluationReport eval=new EvaluationReport();
		eval.setScore(this.calculateScore());
		eval.setScoreOn(this.getScoreBehaviour().getScoreOn());
		for(Entry<Exercise, Answer> couple : this.getExercises()){
			Exercise ex=couple.getKey();
			Answer rightA	= ex.getQuestion().getRightAnswer();
			Answer userAnswer = couple.getValue();
			eval.addExercise(ex, rightA.equals(userAnswer));
		}
		return eval;
	}
	
	
	
	public void finish(){
		this.setEvaluationReport(generateReport());
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

	public EvaluationReport getEvaluationReport() {
		return evaluationReport;
	}

	public void setEvaluationReport(EvaluationReport evaluationReport) {
		this.evaluationReport = evaluationReport;
	}

	public int getRemainingTime() {
		return remainingTime;
	}

	public void setRemainingTime(int remainingTime) {
		this.remainingTime = remainingTime;
	}

	public void decreaseRemainingTime() {
		this.setRemainingTime(this.getRemainingTime()-1);
	}

	
}
