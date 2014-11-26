package domain;

import java.util.ArrayList;
import java.util.Map.Entry;

public class MaxScoreCalculator  extends TestHandler implements ScoreBehaviour{

	
	
	public MaxScoreCalculator(Evaluation test) throws DomainException {
		super(test);
	}

	
	
	
	
	//we returnen enkel "ronde punten", dus niets na de komma
	@Override
	public int calculateScore() {
		ArrayList<Entry<Exercise, Answer>> exercises=super.getTest().getExercises();
		int totalScore=0;
		int Userscore=0;
		
		
		for(Entry<Exercise, Answer> exerciseEntry: exercises){
			Answer answer=exerciseEntry.getValue();
			Exercise exercise=exerciseEntry.getKey();
			int score=exercise.getScore();
			totalScore+=score;
			//Indien vraag juist beantwoord: tel de score op.
			if(exercise.getQuestion().getRightAnswer().equals(answer)){
				Userscore+=score;
			}
		}
		
		int result=(Userscore/totalScore)*super.getScoreOn();
		
		return result;
	}
	
	

}
