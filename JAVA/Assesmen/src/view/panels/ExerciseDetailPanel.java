package view.panels;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

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
import domain.Answer;
import domain.Exercise;
import domain.Feedback;
import domain.Question;

public class ExerciseDetailPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private GridBagConstraints constraints = new GridBagConstraints();
	private JButton btnSelectCorrectOption, btnRemoveOption, btnAddOption,
			btnRemoveExercise, btnAddToCategory, btnSave, btnCancel;
	private JTextField question, answer, scoreDisplay, newOption;
	private JSpinner score;
	private JTable categories;
	private JComboBox<String> typeQuestion, yesOrNoAnswer, categoryOptions,
			feedbackOptions;
	private CheckBoxList optionSelector;

	private List<Exercise> commonExercises;
	private List<Answer> options;
	private JPanel optionPanel;
	private boolean isYesNoType;

	public ExerciseDetailPanel(Action action, Action action2, Action action3,
			Action action4,ActionListener action5,Action action6) {
		//setCommonExercises(commonExercises);
		setLayout(new GridBagLayout());
		initConstraints();
		int rij = 0;
		initQuestionType(++rij,action5);
		initQuestionTitle(++rij);
		initAnswerTitle(++rij);
		initOptionPanel(++rij);
		initOptionSelector(++rij);
		rij = rij + 3;
		initAddOption(rij,action6);
		initOptionButtons(++rij,action6);
		initCategoryTable(++rij);
		initRemoveCategory(++rij, action);
		initCategoryOptions(++rij, action2);
		setFeedbackOptions(++rij);
		setInitScore(++rij);
		addCatButton(++rij, action3);
		addAndRemoveButtons(++rij, action4);
		//hideNewElements();
	}

	private void addAndRemoveButtons(int rij, Action a) {
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
		score.setModel(new SpinnerNumberModel(1, 1, 10, 1));
		((DefaultEditor) score.getEditor()).getTextField().setEditable(false);
		addToPanel(score);

	}

	private void setFeedbackOptions(int rij) {
		changeConstraints(1, 1, 0, rij);
		addToPanel(new JLabel("Feedback: "));
		feedbackOptions = new JComboBox<String>();
		changeConstraints(1, 2, 1, rij);
		addToPanel(feedbackOptions);
	}

	private void initCategoryOptions(int rij, Action a) {
		changeConstraints(1, 1, 0, rij);
		addToPanel(new JLabel("Category: "));
		categoryOptions = new JComboBox<String>();
		changeConstraints(1, 2, 1, rij);
		categoryOptions.setAction(a);
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

	private void initAddOption(int rij,Action a) {
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

	private void initOptionButtons(int rij,Action a) {
		changeConstraints(1, 1, 1, rij);
		btnRemoveOption = new JButton("Remove");
		btnRemoveOption.setAction(a);
		btnRemoveOption.setActionCommand("RemoveOption");
		btnRemoveOption.setText("Remove");
		addToInnerPanel(btnRemoveOption);
		changeConstraints(1, 1, 2, rij);
		btnSelectCorrectOption = new JButton("Select Correct");
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

	protected void initQuestionType(int rij,ActionListener a) {
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

	public List<Exercise> getCommonExercises() {
		return commonExercises;
	}

	public void setCommonExercises(List<Exercise> commonExercises) {
		this.commonExercises = commonExercises;
		Exercise ex=commonExercises.get(0);
		Question q=ex.getQuestion();

		
		ArrayList<Answer> options=new ArrayList(commonExercises.get(0).getQuestion().getOptions());
		String[] opt=new String[options.size()];
		
		for(int i =0 ; i<options.size();i++){
			opt[i]=options.get(i).getAnswer();
		}
		
		
		setAnswer(ex,opt);
		
		
		setQuestionText(q.getQuestion());
		
	}

	private void setAnswer(Exercise ex,String[] options){
		if(isYesNoType){
			setAnswerList(options,ex.getQuestion().getRightAnswer().getAnswer());
		}
		else{
			setAnswerText(ex.getQuestion().getRightAnswer().getAnswer());
		}
	}
	
	private void setAnswerText(String text){
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
	
	
	public void setAnswerList(String[] selectList,String current){
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(selectList);
		this.yesOrNoAnswer.setModel(model);
		int index=getComboBoxIndex(selectList, current);
		this.yesOrNoAnswer.setSelectedIndex(index);
	}
	
	public void setTypeList(String[] selectList,String current){
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(selectList);
		this.typeQuestion.setModel(model);
		int index=getComboBoxIndex(selectList, current);
		this.typeQuestion.setSelectedIndex(index);
	}
	
	
	private int getComboBoxIndex(String[] list,String item){

		for(int i=0;i<list.length;i++){
			if(list[i].equals(item)){
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
		this.typeQuestion.setEnabled(false);
	}

	public String getSelectedType() {
		return (String)this.typeQuestion.getSelectedItem();
	}
	
	//
	public void lockForYesNo() {
		this.optionPanel.setVisible(false);
		answer.setVisible(false);
		yesOrNoAnswer.setVisible(true);
		isYesNoType = true;
	}

	public void update(){
		this.setOptionSelector(this.getOptions());
	}
	
	
	public void setOptionSelector(List<Answer> options){
		Question q= commonExercises.get(0).getQuestion();
		if (options != null) {
			ArrayList<JCheckBox> optionModel = new ArrayList<JCheckBox>();
			for (Answer a : options) {
				JCheckBox box = new JCheckBox(a.getAnswer());

				if(q.getOptions().contains(a))
					box.setSelected(true);
				optionModel.add(box);
					
			}
			optionSelector.setListData(optionModel.toArray());;
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
	
	public void setQuestionText(String text){
		question.setText(text);
		question.setEditable(false);
	}
	
	public String getNewOption(){
		return newOption.getText();
	}
	
	public String getSelectedValueOption(){
		return ((JCheckBox) optionSelector.getSelectedValue()).getText();
	}

}
