package domain;

import java.util.ArrayList;

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
		exercisePool = new ExercisePool();
		categoryPool = new CategoryPool();
		feedbackPool = new FeedbackPool();
		
		// temp
		try {
			Category temp = new Category();
			Feedback feed = new Feedback("Swing is so 1996");
			Feedback feed2 = new Feedback("blabla");
			temp.setName("test");
			temp.setDescription("description test");
			feedbackPool.addFeedback(feed);
			feedbackPool.addFeedback(feed2);
			temp.addFeedback(feed);
			temp.addFeedback(feed2);
			categoryPool.AddCategory(temp);
		} catch (DomainException e) {
			e.printStackTrace();
		}
	}

	// public void edit("vraagstelling",[] cat,[] feedbavk,score[]){
	// ArrayList<Exercise>
	// questions=exercisePool.getExerciseByQuestion(vraagstelling);
	// }

	public void removeExercise(Exercise exercise) {

	}

	public void addCategory(Category category) {
		categoryPool.AddCategory(category);
	}

	public ArrayList<Category> getCategoryList() {
		return new ArrayList<Category>(getCategoryPool().getCategoryPool());
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
