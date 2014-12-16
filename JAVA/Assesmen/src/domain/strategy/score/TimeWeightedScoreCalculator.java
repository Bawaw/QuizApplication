package domain.strategy.score;

import domain.DomainException;
import domain.Evaluation;

public class TimeWeightedScoreCalculator extends ScoreBehaviour {

	public TimeWeightedScoreCalculator(Evaluation test) throws DomainException {
		super(test);
		
	}

	public int bonusPoints(){
		int output=0;
		if (super.getTest().getRemainingTime() > (super.getTest().getTimeAllowed()/2)){
			output=2;
		}
		return output;
	}

}
