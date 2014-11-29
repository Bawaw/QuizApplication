package domain.strategy.score;

import domain.DomainException;
import domain.Evaluation;

public class TimeWeightedScoreCalculator extends ScoreBehaviour {

	public TimeWeightedScoreCalculator(Evaluation test) throws DomainException {
		super(test);
		
	}

	public int bonusPoints(){
		//if remainingtime> totaltime/2 -> return 2 else 0;
		return 0;
	}

}
