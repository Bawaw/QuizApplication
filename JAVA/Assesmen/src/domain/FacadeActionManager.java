package domain;

import java.util.ArrayList;

import config.ConfigException;
import config.InitConfigHandler;
import domain.enums.EvaluationType;
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
	private static FacadeActionManager singleton;
	
	public static synchronized FacadeActionManager getInstance(){
		if(singleton == null){
			singleton=new FacadeActionManager();
		}
		return singleton;
	}

	private FacadeActionManager() {
		setExercisePool(new ExercisePool());
		setCategoryPool( new CategoryPool());
		setFeedbackPool(new FeedbackPool());
		setInitConfigHandler(InitConfigHandler.getInstance());
		// temp
		try {
			Category cat1 = new Category();
			Category cat2 = new Category();
			Category cat3 = new Category();
			Category cat4 = new Category();
			
			Feedback feed = new Feedback("Feedback 1");
			Feedback feed2 = new Feedback("Feedback 2");
			Feedback feed3 = new Feedback("Feedback 3");
			Feedback feed4 = new Feedback("Feedback 4");
			Feedback feed5 = new Feedback("Feedback 5");
			
			feedbackPool.addFeedback(feed);
			feedbackPool.addFeedback(feed2);
			feedbackPool.addFeedback(feed3);
			feedbackPool.addFeedback(feed4);
			feedbackPool.addFeedback(feed5);
			
			cat1.setName("Test Cat 1");
			cat1.setDescription("Description test 1");
			cat1.addFeedback(feed);
			cat1.addFeedback(feed2);
			
			cat2.setName("Test Cat 2");
			cat2.setDescription("Description test 2");
			
			cat3.setName("Test Cat 3");
			cat3.setDescription("Description test 3");
			cat3.addFeedback(feed);
			cat3.addFeedback(feed2);
			cat3.addFeedback(feed3);
			cat3.addFeedback(feed4);
			cat3.addFeedback(feed5);
			
			cat4.setName("Test Cat 4");
			cat4.setDescription("Description test 4");
			cat4.addFeedback(feed4);
			cat4.addFeedback(feed5);
			
			
			categoryPool.AddCategory(cat1);
			categoryPool.AddCategory(cat2);
			categoryPool.AddCategory(cat3);
			categoryPool.AddCategory(cat4);
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
		//remove feedback from feedbackpool
		getFeedbackPool().removeFeedback(feedback);
		//unlink feedback from all categories
		getCategoryPool().removeFeedbackFromAllCat(feedback);
		//remove feedback from all questions
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

	
	public InitConfigHandler getInitConfigHandler() {
		return initConfigHandler;
	}

	public void setInitConfigHandler(InitConfigHandler initConfigHandler) {
		this.initConfigHandler = initConfigHandler;
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
	
	public String[] getAllEvaluationTypes(){
		return EvaluationType.toStringArray();
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
	
	public String currentEvaluationType() throws ConfigException{
		return initConfigHandler.getEvaluationType();
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
	
	public void saveEvaluationType(String evaluation) throws ConfigException{
		this.initConfigHandler.saveEvaluationType(evaluation);
	}
	
}

