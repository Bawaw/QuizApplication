package domain;

import java.util.ArrayList;

import config.ConfigException;
import config.InitConfigHandler;
import domain.enums.QuestionSelectionBehaviourType;
import domain.enums.ScoreBehaviourType;
import domain.strategy.questionSelection.QuestionSelectionFactory;

public class FacadeActionManager {
	private FeedbackPool feedbackPool;
	private ParticipationPool participations;
	private ExercisePool exercisePool;
	private AnswerPool answerPool;
	private Evaluation activeEvalutaion;
	private QuestionSelectionFactory questionSelectionFactory;
	private CategoryPool categoryPool;
	private InitConfigHandler initConfigHandler;
	private int timer;

	// config handler

	public FacadeActionManager() {
		exercisePool = new ExercisePool();
		categoryPool = new CategoryPool();
		feedbackPool = new FeedbackPool();
		initConfigHandler = new InitConfigHandler();
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
	
	public void removeCategory(Category c) throws DomainException{
		categoryPool.removeCategory(c);
		exercisePool.removeWithCategory(c);
	}
	
	public void removeFeedback(String s) throws DomainException{
		Feedback feedback = new Feedback(s);
		getFeedbackPool().removeFeedback(feedback);
		getExercisePool().removeFeedback(feedback);
	}

	public void removeExercise(Exercise exercise) {

	}

	public boolean doesCatExist(String name){
		return this.getCategoryPool().catNameAlreadyInPool(name);
	}
	public void addCategory(Category category) throws DomainException {
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

	public void addFeedback(String text) throws DomainException {
		feedbackPool.addFeedback(new Feedback(text));	
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

	public String[] getAllScoreBehaviours(){
		return ScoreBehaviourType.toStringArray();
	}
	
	public String[] getAllQuestionSelectionBehaviours(){
		return QuestionSelectionBehaviourType.toStringArray();
	}
	
	
	public int getNumberofQuestionForEvaluation() throws ConfigException{
		return initConfigHandler.getDefaultEvaluationSize();
	}
	
	public String currentScoreBehaviourName() throws ConfigException{
		return initConfigHandler.getScoreBehaviour();
	}
	
	public String currentQuestionSelectionBehaviour() throws ConfigException{
		return initConfigHandler.getQuestionSelectionBehaviour();
	}
	
	public void saveScoreBehaviour(String scoreBehaviour) throws ConfigException{
		this.initConfigHandler.saveScoreBehaviour(scoreBehaviour);
	}
	
	public void saveSelectionBehaviour(String selectionBehaviour) throws ConfigException{
		this.initConfigHandler.saveQuestionSelectionBehaviour(selectionBehaviour);
	}
	
	public void saveNumberofQuestions(int number) throws ConfigException{
		this.initConfigHandler.saveDefaultEvaluationSize(number);
	}
}

