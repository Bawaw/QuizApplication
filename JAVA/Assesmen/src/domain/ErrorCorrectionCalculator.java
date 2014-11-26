package domain;

import java.util.ArrayList;
import java.util.Map.Entry;

public class ErrorCorrectionCalculator extends TestHandler implements
		ScoreBehaviour {
	private final int penaltyOnerror=1;

	public ErrorCorrectionCalculator(Evaluation test) throws DomainException {
		super(test);
	}

	
	
	public int getPenaltyOnerror() {
		return penaltyOnerror;
	}



	@Override
	public int calculateScore() {
		ArrayList<Entry<Exercise, Answer>> exercises=super.getTest().getExercises();
		int totalScore=0;
		int userscore=0;
		
		
		for(Entry<Exercise, Answer> exerciseEntry: exercises){
			Answer answer=exerciseEntry.getValue();
			Exercise exercise=exerciseEntry.getKey();
			int score=exercise.getScore();
			totalScore+=score;
			//Indien vraag juist beantwoord: tel de score op, anders trek 1 punt af.
			if(exercise.getQuestion().getRightAnswer().equals(answer)){
				userscore+=score;
			}
			else{
				userscore=userscore-this.getPenaltyOnerror();
			}
		}
		
		int result=Math.max((userscore/totalScore)*super.getScoreOn(),0);
		
		return result;
	}

}
