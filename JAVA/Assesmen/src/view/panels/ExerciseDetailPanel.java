package view.panels;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.swing.Action;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import view.CheckBoxList;
import view.ViewException;
import controller.CategorySelectionListener;
import domain.Answer;
import domain.Category;
import domain.DomainException;
import domain.Exercise;
import domain.Feedback;
import domain.Question;
import domain.enums.QuestionType;
import domain.factory.QuestionFactory;

public class ExerciseDetailPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private GridBagConstraints constraints = new GridBagConstraints();
	private JButton btnSelectCorrectOption, btnRemoveOption, btnAddOption,
			btnRemoveExercise, btnAddToCategory, btnSave, btnCancel;
	private JTextField question, answer, scoreDisplay, newOption;
	private JSpinner score,time;
	private JTable categories;
	private ExerciseDetailTableModel exerciseDetailTableModel;
	private JComboBox<String> typeQuestion, yesOrNoAnswer, categoryOptions,
			feedbackOptions;
	private CheckBoxList optionSelector;
	private ArrayList<JCheckBox> optionModel;
	private ArrayList<Exercise> commonExercises;
	private List<Answer> options;
	private List<Category> categorieList;
	private List<Feedback> feedbacks;
	private JPanel optionPanel;
	private boolean isYesNoType;
	private boolean isEdit;


	public ExerciseDetailPanel(Action action,
			CategorySelectionListener categorySelectionListener,
			Action action3, Action action4, ActionListener action5,
			Action action6) {

		
		setLayout(new GridBagLayout());
		initConstraints();
		int rij = 0;
		initQuestionType(++rij, action5);
		initQuestionTitle(++rij);
		initAnswerTitle(++rij);
		initTime(++rij);
		initOptionPanel(++rij);
		initOptionSelector(++rij);
		rij = rij + 3;
		initAddOption(rij, action6);
		initOptionButtons(++rij, action6);
		initCategoryTable(++rij);
		initRemoveCategory(++rij, action);
		initCategoryOptions(++rij, categorySelectionListener);
		setFeedbackOptions(++rij);
		setInitScore(++rij);
		addCatButton(++rij, action3);
		cancelAndSaveButtons(++rij, action4);
		// hideNewElements();
	}

	public void setExerciseDetailTableModel(
			ExerciseDetailTableModel exerciseDetailTableModel) {
		this.exerciseDetailTableModel = exerciseDetailTableModel;
	}

	private void cancelAndSaveButtons(int rij, Action a) {
		btnCancel = new JButton();
		changeConstraints(1, 1, 1, rij);
		btnCancel.setAction(a);
		btnCancel.setActionCommand("CancelEdit");
		btnCancel.setText("Cancel");
		addToPanel(btnCancel);
		btnSave = new JButton();
		changeConstraints(1, 1, 2, rij);
		btnSave.setAction(a);
		btnSave.setActionCommand("SaveNewExercise");
		btnSave.setText("Save");
		addToPanel(btnSave);
	}

	private void addCatButton(int rij, Action a) {
		btnAddToCategory = new JButton("Add");
		changeConstraints(1, 2, 1, rij);
		btnAddToCategory.setAction(a);
		btnAddToCategory.setText("Add");
		addToPanel(btnAddToCategory);
	}

	private void setInitScore(int rij) {
		changeConstraints(1, 1, 0, rij);
		addToPanel(new JLabel("Score: "));
		changeConstraints(1, 2, 1, rij);
		score = new JSpinner();
		score.setModel(new SpinnerNumberModel(3, 1, 10, 1));
		((DefaultEditor) score.getEditor()).getTextField().setEditable(false);
		addToPanel(score);

	}
	
	public int getSelectedExerciseIndex(){
		return categories.getSelectedRow();
	}

	public List<Feedback> getFeedbacks() {
		return feedbacks;
	}
	
	public void removeExerciseLocally(int i){
		getCommonExercises().remove(i);
	}
	
	public void setFeedbacks(List<Feedback> feedbacks) {
		this.feedbacks = feedbacks;
	}

	public List<Category> getCategorieList() {
		return categorieList;
	}

	public void setCategorieList(List<Category> categorieList) {
		this.categorieList = categorieList;
	}

	public Category getSelectedCategory() {
		for (Category category : categorieList) {
			if (category.getName().equals(categoryOptions.getSelectedItem()))
				return category;
		}
		return null;
	}

	private void setFeedbackOptions(int rij) {
		changeConstraints(1, 1, 0, rij);
		addToPanel(new JLabel("Feedback: "));
		feedbackOptions = new JComboBox<String>();
		changeConstraints(1, 2, 1, rij);
		addToPanel(feedbackOptions);
	}

	private void initCategoryOptions(int rij, ActionListener a) {
		changeConstraints(1, 1, 0, rij);
		addToPanel(new JLabel("Category: "));
		categoryOptions = new JComboBox<String>();
		changeConstraints(1, 2, 1, rij);
		categoryOptions.addActionListener(a);
		addToPanel(categoryOptions);
	}

	private void initRemoveCategory(int rij, Action action) {
		btnRemoveExercise = new JButton("Remove");
		changeConstraints(1, 2, 1, rij);
		btnRemoveExercise.setAction(action);
		btnRemoveExercise.setText("Remove");
		addToPanel(btnRemoveExercise);
	}

	private void initCategoryTable(int rij) {
		changeConstraints(1, 1, 0, rij);
		addToPanel(new JLabel("Exercises: "));
		changeConstraints(1, 2, 1, rij);
		categories = new JTable();
		categories.setModel(new ExerciseDetailTableModel(
				new ArrayList<Exercise>()));
		JScrollPane pane = new JScrollPane(categories);
		pane.setPreferredSize(new Dimension(225, 200));
		addToPanel(pane);
	}

	private void initOptionSelector(int rij) {
		changeConstraints(1, 1, 0, rij);
		addToInnerPanel(new JLabel("Options:"));
		changeConstraints(3, 2, 1, rij);
		optionSelector = new CheckBoxList();
		addToInnerPanel(new JScrollPane(optionSelector));
	}

	private void initAddOption(int rij, Action a) {
		changeConstraints(1, 1, 1, rij);
		newOption = new JTextField();
		addToInnerPanel(newOption);
		changeConstraints(1, 1, 2, rij);
		btnAddOption = new JButton("Add");
		btnAddOption.setAction(a);
		btnAddOption.setActionCommand("AddOption");
		btnAddOption.setText("Add");
		addToInnerPanel(btnAddOption);
	}

	private void initOptionButtons(int rij, Action a) {
		changeConstraints(1, 1, 1, rij);
		btnRemoveOption = new JButton("Remove");
		btnRemoveOption.setAction(a);
		btnRemoveOption.setActionCommand("RemoveOption");
		btnRemoveOption.setText("Remove");
		addToInnerPanel(btnRemoveOption);
		changeConstraints(1, 1, 2, rij);
		btnSelectCorrectOption = new JButton("Select Correct");
		btnSelectCorrectOption.setAction(a);
		btnSelectCorrectOption.setActionCommand("SelectOption");
		btnSelectCorrectOption.setText("Select Correct");
		addToInnerPanel(btnSelectCorrectOption);
	}

	private void initOptionPanel(int i) {
		changeConstraints(1, 3, 0, i);
		constraints.insets = new Insets(0, 0, 0, 0);
		optionPanel = new JPanel();
		optionPanel.setLayout(new GridBagLayout());
		this.addToPanel(optionPanel);
		initConstraints();
	}

	protected void initTime(int rij){
		changeConstraints(1, 1, 0, rij);
		addToPanel(new JLabel("Time: "));
		changeConstraints(1, 2, 1, rij);
		time = new JSpinner();
		time.setModel(new SpinnerNumberModel(20, 5, 60, 5));
		((DefaultEditor) time.getEditor()).getTextField().setEditable(false);
		addToPanel(time);
	}
	
	
	protected void initQuestionType(int rij, ActionListener a) {
		changeConstraints(1, 1, 0, rij);
		addToPanel(new JLabel("Type: "));
		typeQuestion = new JComboBox<String>();
		changeConstraints(1, 2, 1, rij);
		typeQuestion.addActionListener(a);
		addToPanel(typeQuestion);
	}

	protected void initQuestionTitle(int rij) {
		changeConstraints(1, 1, 0, rij);
		addToPanel(new JLabel("Question: "));

		changeConstraints(1, 2, 1, rij);
		question = new JTextField();
		addToPanel(question);
	}

	protected void initAnswerTitle(int rij) {
		changeConstraints(1, 1, 0, rij);
		addToPanel(new JLabel("Answer: "));
		changeConstraints(1, 2, 1, rij);
		answer = new JTextField();
		answer.setEditable(false);
		addToPanel(answer);
		yesOrNoAnswer = new JComboBox<String>();
		addToPanel(yesOrNoAnswer);
	}

	public ArrayList<Exercise> getCommonExercises() {
		return commonExercises;
	}

	public void setCommonExercises(ArrayList<Exercise> commonExercises) {
		this.commonExercises = commonExercises;
		Exercise ex = commonExercises.get(0);
		Question q = ex.getQuestion();
		ArrayList<Answer> options = new ArrayList<Answer>(commonExercises.get(0)
				.getQuestion().getOptions());
		String[] opt = new String[options.size()];

		for (int i = 0; i < options.size(); i++) {
			opt[i] = options.get(i).getAnswer();
		}

		setAnswer(ex, opt);

		setQuestionText(q.getQuestion());
		updateForEdit();

	}

	private void setAnswer(Exercise ex, String[] options) {
		if (isYesNoType) {
			setAnswerList(options, ex.getQuestion().getRightAnswer()
					.getAnswer());
		} else {
			setAnswerText(ex.getQuestion().getRightAnswer().getAnswer());
		}
	}
	
	private String getAnswer(){
		if (isYesNoType) {
			return (String)this.yesOrNoAnswer.getSelectedItem();
		} else {
			return answer.getText();
		}
	}
	
	private Set<Answer> getAnswers() throws DomainException{
		Set<Answer> out=new HashSet<Answer>();
		
		
			for(JCheckBox box:optionModel){
					if (box.isSelected()){
						out.add(new Answer(box.getText()));
				}
			  }
			
		return out;
	}

	public void setAnswerText(String text) {
		this.answer.setText(text);
	}

	private void initConstraints() {
		constraints.insets = new Insets(10 / 2, 20 / 2, 10 / 2, 20 / 2);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx = 1.0;
		constraints.weighty = 1.0;
	}

	protected GridBagConstraints getConstraints() {
		return constraints;
	}

	public void setAnswerList(String[] selectList, String current) {
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(
				selectList);
		this.yesOrNoAnswer.setModel(model);
		int index = getComboBoxIndex(selectList, current);
		this.yesOrNoAnswer.setSelectedIndex(index);
	}


	public void setTypeList(String[] selectList, String current) {
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(
				selectList);
		this.typeQuestion.setModel(model);
		int index = getComboBoxIndex(selectList, current);
		this.typeQuestion.setSelectedIndex(index);
	}

	private int getComboBoxIndex(String[] list, String item) {

		for (int i = 0; i < list.length; i++) {
			if (list[i].equals(item)) {
				return i;
			}
		}
		return 0;
	}

	protected void addToPanel(Component component) {
		add(component, getConstraints());
	}

	protected void addToInnerPanel(Component component) {
		this.optionPanel.add(component, getConstraints());
	}

	protected void changeConstraints(int height, int width, int gridx, int gridy) {
		constraints.gridheight = height;
		constraints.gridwidth = width;
		constraints.gridx = gridx;
		constraints.gridy = gridy;
	}

	public void setEdit() {
		isEdit=true;
		//duid antwoorden voor deze vraag aan!
		this.setCheckedOptions();
		this.typeQuestion.setEnabled(false);
	}

	public void setNew(){
		this.commonExercises=new ArrayList<Exercise>();
		isEdit=false;
		this.typeQuestion.setEnabled(true);
		answer.setText("");
		question.setText("");
		initYesNoDropdown();
		question.setEditable(true);
		this.setOptionSelector(this.getOptions());
	}
	
	public void initYesNoDropdown(){
		String[] options=new String[2];
		options[0]="No";
		options[1]="Yes";
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(
				options);
		this.yesOrNoAnswer.setModel(model);
		int index = 0;
		this.yesOrNoAnswer.setSelectedIndex(index);
	}
	
	public String getSelectedType() {
		return (String) this.typeQuestion.getSelectedItem();
	}

	//
	public void lockForYesNo() {
		this.optionPanel.setVisible(false);
		answer.setVisible(false);
		yesOrNoAnswer.setVisible(true);
		isYesNoType = true;
	}

	private void updateForEdit() {
		//duid antwoorden aan die bij de vraag horen
		//setCheckedOptions();
		categories.setModel(new ExerciseDetailTableModel(getCommonExercises()));
		populateCategoryList(getCategorieList());
		updateFeedback();
	}
	
	private void updateforNew(){
		categories.setModel(new ExerciseDetailTableModel(getCommonExercises()));
		populateCategoryList(getCategorieList());
		updateFeedback();
		setDefaultCategory();
	}
	
	public void update(){
		if(isEdit){
			updateForEdit();
		}
		else{
			updateforNew();
		}
	}
	
	public void updateFeedback() {
		if(getFeedbacks() != null)
		populateFeedbackField(getFeedbacks());
	}
	
	public void setDefaultCategory(){
		categoryOptions.setSelectedIndex(0);
	}

	public void populateFeedbackField(List<Feedback> feedbacks) {
		String[] feedText = new String[feedbacks.size()];
		for (int i = 0; i < feedbacks.size(); i++) {
			feedText[i] = feedbacks.get(i).getText();
		}
		DefaultComboBoxModel model = new DefaultComboBoxModel(feedText);
		feedbackOptions.setModel(model);
	}

	public void populateCategoryList(List<Category> categories) {
		String[] catNames = new String[categories.size()];
		for (int i = 0; i < categories.size(); i++) {
			catNames[i] = categories.get(i).getName();
		}
		DefaultComboBoxModel model = new DefaultComboBoxModel(catNames);
		this.categoryOptions.setModel(model);
	}

	
	//SetOptionSelector opgesplitst in setOptionSelector en setCheckedOptions, zodat deze herbruikbaar is bij niet-edit
	public void setOptionSelector(List<Answer> options) {  
		if (options != null) {
			optionModel = new ArrayList<JCheckBox>();
			for (Answer a : options) {
				JCheckBox box = new JCheckBox(a.getAnswer());
				optionModel.add(box);

			}
			optionSelector.setListData(optionModel.toArray());
		}
	}
	
	
	public void setCheckedOptions(){
		if(optionModel != null){
		Question q = commonExercises.get(0).getQuestion();
	
		for(JCheckBox box:optionModel){
			try {
				if (q.getOptions().contains(new Answer(box.getText()))){
					box.setSelected(true);
				}
			} catch (DomainException e) {
				e.printStackTrace();
			}
		  }
		}
	}
	



	public List<Answer> getOptions() {
		return options;
	}

	public void setOptions(List<Answer> options) {
		this.options = options;
	}

	public void lockForMultipleChoice() {
		this.optionPanel.setVisible(true);
		answer.setVisible(true);
		yesOrNoAnswer.setVisible(false);
		isYesNoType = false;
	}

	public void setQuestionText(String text) {
		question.setText(text);
		question.setEditable(false);
	}
	
	public String getQuestion(){
		return question.getText();
	}

	public String getNewOption() {
		return newOption.getText();
	}
	
	public int getNumberOfExercises(){
		return commonExercises.size();
	}

	public String getSelectedValueOption() {
		return ((JCheckBox) optionSelector.getSelectedValue()).getText();
	}

	public int getScoreValue(){
		return (Integer)this.score.getValue();
	}

	public int getTimeValue(){
		return (Integer)this.time.getValue();
	}
	public Feedback getSelectedFeedback() {
		for (Feedback feedback : feedbacks) {
			if (feedback.getText().equals(feedbackOptions.getSelectedItem()))
				return feedback;
		}
		return null;
	}

	public void addExerciseLocally(Exercise ex) throws ViewException {
		for(Exercise exer:getCommonExercises()){
			if(exer.getCategory().equals(ex.getCategory())){
				throw new ViewException("Exercise with this category already exists!");
			}
		}
		getCommonExercises().add(ex);
		
	}
	
	public ArrayList<Exercise> getCreatedExercises() throws DomainException{
		ArrayList<Exercise> exercises=this.getCommonExercises();
		Question q=null;
		QuestionType qt=QuestionType.getQuestionTypeByDescription(getSelectedType());
		if(qt == QuestionType.YesNoQuestions){
			 q=QuestionFactory.create(qt,getQuestion(),new Answer(getAnswer()),getTimeValue());
		}
		else{
			q=QuestionFactory.create(qt,getQuestion(),new Answer(getAnswer()),getAnswers(),getTimeValue());
		}
	
		for(Exercise ex:exercises){
			ex.setQuestion(q);
		}
		
		return exercises;
	}

	public void addOptionLocally(String s) {
		JCheckBox box = new JCheckBox(s);
		optionModel.add(box);
		optionSelector.setListData(optionModel.toArray());
	}
	
	public void removeOptionlocally(String s){
		Iterator<JCheckBox> it = optionModel.iterator();
		boolean continu=true;
		while(it.hasNext() && continu){
			JCheckBox c=it.next();
			if(c.getText().equals(s)){
				it.remove();
				continu=false;
			}
		}
		optionSelector.setListData(optionModel.toArray());
	}
}
