package domain;

import java.io.File;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import config.ConfigException;
import config.InitConfigHandler;
import database.DBDataHandler;
import database.DBException;
import database.DBHandler;
import database.ExcelReader;
import database.ExerciseDataHandler;
import domain.enums.EvaluationType;
import domain.enums.QuestionSelectionBehaviourType;
import domain.enums.QuestionType;
import domain.enums.ScoreBehaviourType;
import domain.factory.EvaluationFactory;
import domain.factory.QuestionFactory;
import domain.factory.QuestionSelectAlgFactory;
import domain.strategy.questionSelection.QuestionSelectionBehaviour;

public class FacadeActionManager {
	private FeedbackPool feedbackPool;
	private ParticipationPool participations;
	private ExercisePool exercisePool;
	private QuestionFactory questionFactory;
	private AnswerPool answerPool;
	private Evaluation activeEvaluation;
	private CategoryPool categoryPool;
	private InitConfigHandler initConfigHandler;
	private int timer;
	
	private DBHandler dbHandler;
	private DBDataHandler dataHandler;

	public FacadeActionManager() {		
		LinkedList<Participation> participations=new LinkedList<Participation>();
		try{
			HashMap<String,PointCouple> mp=new HashMap<String,PointCouple>();
			PointCouple pt=new PointCouple();
			pt.setCorrectQ(3);
			pt.setTotalQ(5);
			mp.put("Category 1", pt);
			pt=new PointCouple();
			pt.setCorrectQ(1);
			pt.setTotalQ(3);
			mp.put("Category 2", pt);
			participations.add(new Participation(10, mp));
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		
		this.setParticipations(new ParticipationPool(participations));
		
		setAnswerPool(new AnswerPool());
		setExercisePool(new ExercisePool());
		setCategoryPool( new CategoryPool());
		setFeedbackPool(new FeedbackPool());
		setInitConfigHandler(InitConfigHandler.getInstance());
		
		dataHandler = new ExerciseDataHandler(this);
		dbHandler = new ExcelReader();
		
		// temp
		/*try {
			Category cat1 = new Category();
			Category cat2 = new Category();
			Category cat3 = new Category();
			Category cat4 = new Category();
			
			Feedback feed = new Feedback("Feedback 1");
			Feedback feed2 = new Feedback("Feedback 2");
			Feedback feed3 = new Feedback("Feedback 3");
			Feedback feed4 = new Feedback("Feedback 4");
			Feedback feed5 = new Feedback("Feedback 5");
			
			Question q1=QuestionFactory.create(QuestionType.YesNoQuestions, "Question 1", new Answer("Yes"), 30);
			Question q2=QuestionFactory.create(QuestionType.YesNoQuestions, "Question 2", new Answer("Yes"), 30);
			Question q3=QuestionFactory.create(QuestionType.YesNoQuestions, "Question 3", new Answer("Yes"), 30);
			Question q4=QuestionFactory.create(QuestionType.YesNoQuestions, "Question 4", new Answer("Yes"), 30);
			Question q5=QuestionFactory.create(QuestionType.YesNoQuestions, "Question 5", new Answer("Yes"), 30);
			
			
			HashSet<Answer> a=new HashSet<Answer>();
			Answer a1=new Answer("no");
			Answer a2=new Answer("nooooo");
			Answer a3=new Answer("noonononon");
			Answer a4=new Answer("ba");
			Answer a5=new Answer("none");
			addAnswer(a1);
			addAnswer(a2);
			addAnswer(a3);
			addAnswer(a4);
			addAnswer(a5);
			addAnswer(new Answer("nog een answer"));
			a.add(a1);
			a.add(a2);
			a.add(a3);
			a.add(a4);
			a.add(a5);
			Question q6=QuestionFactory.create(QuestionType.MultipleChoiceQuestions, "my multiplechoicequestion is very very very long lalalalala", a4,a, 30);
			Question q7=QuestionFactory.create(QuestionType.MultipleChoiceQuestions, "my multiplechoicequestion is very very very long lalalalala", a4,a, 30);
			
			
			
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
			cat2.addFeedback(feed3);
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
			
			
			
			Exercise e1=new Exercise(q1, cat1, feed, 3);
			Exercise e2=new Exercise(q2, cat1, feed2, 2);
			Exercise e3=new Exercise(q3, cat3, feed2, 1);
			Exercise e4=new Exercise(q4, cat4, feed4, 4);
			Exercise e5=new Exercise(q5, cat4, feed5, 6);
			Exercise e6= new Exercise(q6, cat4, feed4, 3);
			Exercise e7= new Exercise(q7, cat3, feed2, 3);
			
			this.getExercisePool().addExercise(e1);
			this.getExercisePool().addExercise(e2);
			this.getExercisePool().addExercise(e3);
			this.getExercisePool().addExercise(e4);
			this.getExercisePool().addExercise(e5);
			this.getExercisePool().addExercise(e6);
			this.getExercisePool().addExercise(e7);
		} catch (DomainException e) {
			e.printStackTrace();
		}*/
		
	}
	
	public void readFromExcel(File file) throws DBException{
		dbHandler.read(file, dataHandler);
	}
	
	public Question createQuestion(QuestionType qt, Object... args) throws DomainException{
		return QuestionFactory.create(qt, args);
	}
	
	public void addAnswer(String s) throws DomainException{
		Answer a=new Answer(s);
		addAnswer(a);
	}
	
	public void addAnswer(Answer answer){
		this.getAnswerPool().AddAnswer(answer);
	}
	
	
	public void removeAnswer(String s) throws DomainException{
		Answer a =new Answer(s);
		this.getAnswerPool().removeAnswer(a);
	}
	
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
	
	public ArrayList<Exercise> getExercisesByQuestion(Exercise exercise){
		return getExercisePool().getExerciseByQuestion(exercise.getQuestion().getQuestion());
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

	public Set<Exercise> getUniqueExercises(){
		return this.getExercisePool().getUniqueExerciseSet();
	}
	
	public Set<Exercise> getUniqueExercisesByCategory(String catName) throws DomainException{
	Category c=new Category(catName);
	return this.getExercisePool().getUniqueExerciseSetWithCategory(c);
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

	public void addExercise(Exercise e) throws DomainException{
		exercisePool.addExercise(e);
	}
	
	public void createEvaluation() throws DomainException, ConfigException{
		QuestionSelectionBehaviour questionSelector =QuestionSelectAlgFactory.createStandard(this.getExercisePool());
		HashSet<Exercise> exerciseList=questionSelector.selectQuestions(this.getInitConfigHandler().getDefaultEvaluationSize());
		ArrayList<Entry<Exercise, Answer>> exercises=new ArrayList<Entry<Exercise, Answer>>(exerciseList.size());
		for(Exercise e:exerciseList){
			Entry<Exercise, Answer> entry=new AbstractMap.SimpleEntry<Exercise, Answer>(e,null);
			exercises.add(entry);
		}
		
		Evaluation eval=EvaluationFactory.create(exercises, ScoreBehaviourType.valueOf(this.getInitConfigHandler().getScoreBehaviour()));
		this.setActiveEvaluation(eval);
	}




	public int getTimer() {
		return timer;
	}

	private void setTimer(int timer) {
		this.timer = timer;
	}
	
	public void addParticipation(EvaluationReport eval) throws DomainException{
		Participation p = new Participation(eval.getScore(),eval.getCatScore());
		this.getParticipations().addParticipation(p);
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
	
	public String currentQuestionType() throws ConfigException{
		return initConfigHandler.getQuestionType();
	}
	
	public void saveQuestionType(String questionType) throws ConfigException{
		this.initConfigHandler.saveQuestionType(questionType);
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

	public List<Participation> getParticipationList() {
		return this.getParticipations().getParticipationPool();
	}

	public Evaluation getActiveEvaluation() {
		return activeEvaluation;
	}

	public void setActiveEvaluation(Evaluation activeEvaluation) {
		this.activeEvaluation = activeEvaluation;
	}

	public String[] getAllQuestionTypes() {
		return QuestionType.toStringArrayUI();
	}
	
	public String[] getAllQuestionTypesAdmin(){
		return QuestionType.toStringArray();
	}


	public void removeSimilarExercises(Exercise clickedExercise) {
		this.getExercisePool().removeSimilarExercise(clickedExercise);
	}
	
	public int getTotalTimeEvaluation(){
		return this.getActiveEvaluation().getTimeAllowed();
	}
	
	public int getRemainingTime(){
		return this.getActiveEvaluation().getRemainingTime();
	}
	
	public void decreaseRemainingTime(){
		this.getActiveEvaluation().decreaseRemainingTime();
	}


	public QuestionFactory getQuestionFactory() {
		return questionFactory;
	}


	public void setQuestionFactory(QuestionFactory questionFactory) {
		this.questionFactory = questionFactory;
	}
	
	
	
	public void updateExercises(List<Exercise> newExercises) throws DomainException{
		this.getExercisePool().updateExercise(newExercises);
	}

}

