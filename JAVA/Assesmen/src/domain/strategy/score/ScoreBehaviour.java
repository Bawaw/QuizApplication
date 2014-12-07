package domain.strategy.score;

import java.util.ArrayList;
import java.util.Map.Entry;

import domain.Answer;
import domain.DomainException;
import domain.Evaluation;
import domain.Exercise;

public abstract class ScoreBehaviour {
	private Evaluation test;
	private final int SCOREON=20;
	
	public ScoreBehaviour(Evaluation test) throws DomainException {
		this.setTest(test);
	}

	public Evaluation getTest() {
		return test;
	}

	private void setTest(Evaluation test) throws DomainException {
		if(test==null){
			throw new DomainException("Test cannot be null");
		}
		this.test = test;
	}

	public int getScoreOn(){
		return this.SCOREON;
	}
	
	
	public final int calculateScore(){
		ArrayList<Entry<Exercise, Answer>> exercises=this.getTest().getExercises();
		int totalScore=0;
		int Userscore=0;
		
		
		for(Entry<Exercise, Answer> exerciseEntry: exercises){
			Answer answer=exerciseEntry.getValue();
			Exercise exercise=exerciseEntry.getKey();
			int score=exercise.getScore();
			totalScore+=score;
			//Indien vraag juist beantwoord: tel de score op.
			if(exercise.getQuestion().getRightAnswer().equals(answer)){
				Userscore+=scoreWhenCorrect(exercise);
			}
			else{
				Userscore+=penaltyOnError();
			}
		}
		
		Userscore+=bonusPoints();
		
		int result= (int)Math.round((Userscore/(totalScore*1.0))*this.getScoreOn());

		if(result<0){
			result=0;
		}
		else if(result>this.getScoreOn()){
			result=getScoreOn();
		}
		return result;
	}
	

	public int scoreWhenCorrect(Exercise ex) {
		return ex.getScore();
	}

	
	public int penaltyOnError(){
		return 0;
	}
	
	public int bonusPoints(){
		return 0;
	}
}
