package domain;

import java.util.ArrayList;
import java.util.Map.Entry;

public class TimeWeightedScoreCalculator extends TestHandler implements
		ScoreBehaviour {

	public TimeWeightedScoreCalculator(Evaluation test) throws DomainException {
		super(test);
		
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
			//Indien vraag juist beantwoord: tel de score op.
			if(exercise.getQuestion().getRightAnswer().equals(answer)){
				userscore+=score;
			}
		}
		
		/*if(   super.getTest().timeRemaining()>(super.getTest().getTimeAllowed() / 2)){
			userscore=userscore+2;
		}*/
	
		
		int result=Math.min((userscore/totalScore)*super.getScoreOn(),super.getScoreOn());
		
		return result;
	}

}