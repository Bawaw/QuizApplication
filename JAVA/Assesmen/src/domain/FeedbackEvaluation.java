package domain;

import java.util.ArrayList;
import java.util.Map.Entry;

import domain.enums.ScoreBehaviourType;

public class FeedbackEvaluation extends Evaluation {

	public FeedbackEvaluation(ArrayList<Entry<Exercise, Answer>> exercises,
			ScoreBehaviourType scoreBehaviourType) throws DomainException {
		super(exercises, scoreBehaviourType);
	}

	@Override
	public String getSpecificFeedback() {
		// TODO Auto-generated method stub
		//TODO write implementation
		return null;
	}

}
