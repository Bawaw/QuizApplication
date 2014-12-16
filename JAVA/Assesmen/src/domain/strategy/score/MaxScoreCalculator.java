package domain.strategy.score;

import domain.DomainException;
import domain.Evaluation;


public class MaxScoreCalculator  extends ScoreBehaviour{

	
	
	public MaxScoreCalculator(Evaluation test) throws DomainException {
		super(test);
	}

	
}
