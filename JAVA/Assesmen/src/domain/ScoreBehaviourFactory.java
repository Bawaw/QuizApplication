package domain;

import domain.enums.ScoreBehaviourType;

public class ScoreBehaviourFactory {
	ScoreBehaviourType scorebehaviourType;
	Evaluation evaluation;

	public ScoreBehaviourFactory(ScoreBehaviourType scorebehaviourType,
			Evaluation evaluation) {
		setScorebehaviourType(scorebehaviourType);
		setEvaluation(evaluation);
	}

	public ScoreBehaviourType getScorebehaviourType() {
		return scorebehaviourType;
	}

	public void setScorebehaviourType(ScoreBehaviourType scorebehaviourType) {
		this.scorebehaviourType = scorebehaviourType;
	}

	public Evaluation getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(Evaluation evaluation) {
		this.evaluation = evaluation;
	}

}
