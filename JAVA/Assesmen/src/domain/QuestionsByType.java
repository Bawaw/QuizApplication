package domain;

import java.util.HashSet;

public class QuestionsByType extends ExercisePoolHandler implements QuestionSelectionbehaviour {
	private QuestionType questionType;

	public QuestionsByType(ExercisePool exercisePool,QuestionType questionType) throws DomainException {
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
	public HashSet<Question> selectQuestions(int amount) {
		// TODO IMPLEMENTATION
		return null;
	}

}
