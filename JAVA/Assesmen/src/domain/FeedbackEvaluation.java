package domain;

import java.util.ArrayList;
import java.util.Map.Entry;

import domain.enums.ScoreBehaviourType;

public class FeedbackEvaluation extends Evaluation {
	private static final long serialVersionUID = 1L;

	public FeedbackEvaluation(ArrayList<Entry<Exercise, Answer>> exercises,
			ScoreBehaviourType scoreBehaviourType) throws DomainException {
		super(exercises, scoreBehaviourType);
	}

	@Override
	public String getSpecificFeedback() {
		String output="You made some mistakes:\n\n";
		for(Entry<Exercise, Answer> couple : super.getExercises()){
			Exercise ex=couple.getKey();
			Answer rightA	= ex.getQuestion().getRightAnswer();
			Answer userAnswer = couple.getValue();
			if(!rightA.equals(userAnswer)){
				output+=ex.getQuestion().getQuestion() +": " +ex.getFeedback().getText()+"\n";
			}
		}
		if(output=="You made some mistakes:\n\n"){
			output="You made no errors!";
		}
		return output;
	}

}
