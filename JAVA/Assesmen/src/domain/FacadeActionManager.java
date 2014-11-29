package domain;

import domain.strategy.questionSelection.QuestionSelectionFactory;

public class FacadeActionManager {
	FeedbackPool feedbackPool;
	ParticipationPool participations;
	ExercisePool exercisePool;
	AnswerPool answerPool;
	Evaluation activeEvalutaion;
	QuestionSelectionFactory questionSelectionFactory;
	CategoryPool categoryPool;
	int timer;

	// config handler

	public FacadeActionManager() {

	}
	
	public void addCategory(Category category){
		getCategoryPool().AddAnswer(category);
	}

	public CategoryPool getCategoryPool() {
		return categoryPool;
	}

	public void setCategoryPool(CategoryPool categoryPool) {
		this.categoryPool = categoryPool;
	}

	public FeedbackPool getFeedbackPool() {
		return feedbackPool;
	}

	private void setFeedbackPool(FeedbackPool feedbackPool) {
		this.feedbackPool = feedbackPool;
	}

	public ParticipationPool getParticipations() {
		return participations;
	}

	private void setParticipations(ParticipationPool participations) {
		this.participations = participations;
	}

	public ExercisePool getExercisePool() {
		return exercisePool;
	}

	private void setExercisePool(ExercisePool exercisePool) {
		this.exercisePool = exercisePool;
	}

	public AnswerPool getAnswerPool() {
		return answerPool;
	}

	private void setAnswerPool(AnswerPool answerPool) {
		this.answerPool = answerPool;
	}

	public Evaluation getActiveEvalutaion() {
		return activeEvalutaion;
	}

	public void setActiveEvalutaion(Evaluation activeEvalutaion) {
		this.activeEvalutaion = activeEvalutaion;
	}

	public QuestionSelectionFactory getQuestionSelectionFactory() {
		return questionSelectionFactory;
	}

	private void setQuestionSelectionFactory(
			QuestionSelectionFactory questionSelectionFactory) {
		this.questionSelectionFactory = questionSelectionFactory;
	}

	public int getTimer() {
		return timer;
	}

	private void setTimer(int timer) {
		this.timer = timer;
	}

}
