package domain.strategy.questionSelection;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

import domain.DomainException;
import domain.Exercise;
import domain.ExercisePool;
import domain.YesNoQuestion;
import domain.enums.QuestionType;

public class QuestionsByType extends ExercisePoolHandler implements
		QuestionSelectionBehaviour {
	private QuestionType questionType;

	public QuestionsByType(ExercisePool exercisePool, QuestionType questionType)
			throws DomainException {
		super(exercisePool);
		this.setQuestionType(questionType);
	}

	public QuestionType getQuestionType() {
		return questionType;
	}

	public void setQuestionType(QuestionType questionType) {
		this.questionType = questionType;
	}

	@Override
	public HashSet<Exercise> selectQuestions(int amount) throws DomainException {
		if (getExercisePool().getUniqueExerciseSet().size() < amount)
			throw new DomainException("You need atleast: " + amount
					+ " unique questions before you can create an evaluation");

		Random r = new Random();
		HashSet<Exercise> retSet = new HashSet<Exercise>();
		ArrayList<Exercise> typeList = new ArrayList<Exercise>();

		for (Exercise exercise : getExercisePool().getExercisePool()) {
			
		
			
			if(exercise.getQuestion().getClass().getName().equals(questionType.getFQDN())){
				typeList.add(exercise);
			}
				
		}

		for (int i = 0; i < amount; i++) {
			boolean valid = false;
			for (int j = 0; j < QuestionSelectionBehaviour.iterations && !valid; j++) {
				int index = r.nextInt(getExercisePool().getExercisePool()
						.size()); // not sure if works since internal clock
				if (!retSet.contains(typeList.get(index))) {
					retSet.add(typeList.get(index));
					valid = true;
				}
			}
		}
		return retSet;
	}

}
