package domain.strategy.score;

import domain.DomainException;
import domain.Evaluation;


public class ErrorCorrectionCalculator extends ScoreBehaviour {
	

	public ErrorCorrectionCalculator(Evaluation test) throws DomainException {
		super(test);
	}

	@Override
	public int penaltyOnError(){
		return -1;
	}

}
