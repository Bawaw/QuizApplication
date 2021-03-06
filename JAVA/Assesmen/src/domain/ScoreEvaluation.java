package domain;

import java.util.ArrayList;
import java.util.Map.Entry;

import domain.enums.ScoreBehaviourType;

public class ScoreEvaluation extends Evaluation {
	private static final long serialVersionUID = 1L;

	public ScoreEvaluation(ArrayList<Entry<Exercise, Answer>> exercises,
			ScoreBehaviourType scoreBehaviourType) throws DomainException {
		super(exercises, scoreBehaviourType);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getSpecificFeedback() {
		return super.getEvaluationReport().toString();
	}

}
