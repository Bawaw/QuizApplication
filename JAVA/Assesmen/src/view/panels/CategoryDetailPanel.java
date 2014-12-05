package view.panels;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.event.DocumentListener;

import view.CheckBoxList;
import domain.Category;
import domain.DomainException;
import domain.Feedback;


public class CategoryDetailPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private GridBagConstraints constraints = new GridBagConstraints();
	private JButton btnOK, btnCancel,btnRemoveFeedback,btnAddFeedback;
	private JLabel alreadyExist;
	private JTextField titleField, descriptionField,newFeedbackField; 
	private CheckBoxList feedbackField;
	private Category category;
	private List<Category> categories;
	private List<Feedback> feedbacks;
	private boolean editScreen;
	
	public CategoryDetailPanel(Action action,Action feedbackAction,DocumentListener catNameListener) {
		setCategory(category);
		setCategories(categories);
		setFeedbacks(feedbacks);
		
		setLayout(new GridBagLayout());
		initConstraints();
		int rij = 0;
		initTitle(++rij,catNameListener);
		initLabel(++rij);
		initDescription(++rij);
		initStandardFeedback(++rij);
		initbtnRemoveFeedback(++rij, feedbackAction);
		initAddFeedback(++rij,feedbackAction);
		initButtons(++rij, action);
	}
	
	public String getNewFeedbackFieldText(){
		return newFeedbackField.getText();
	}
	public void initLabel(int rij){
		alreadyExist=new JLabel(" ");
		changeConstraints(1, 2, 1, rij);		
		addToPanel(alreadyExist);
	}
	
	public void overrideText(){
		this.alreadyExist.setForeground(Color.RED);
		this.alreadyExist.setText("Category will be overriden!");
	}
	
	public void notOverrideText(){
		this.alreadyExist.setForeground(Color.BLUE);
		this.alreadyExist.setText("Category doesn't exist yet!");
		
	}
	
	public void clearfeedbackFieldText(){
		newFeedbackField.setText("");
	}
	
	protected void initAddFeedback(int rij, Action action) {
		btnAddFeedback = new JButton("Add");
		changeConstraints(1, 1, 2, rij);		
		btnAddFeedback.setAction(action);
		btnAddFeedback.setActionCommand("AddFeedback");
		btnAddFeedback.setText("Add");
		addToPanel(btnAddFeedback);
		newFeedbackField = new JTextField();
		changeConstraints(1, 1, 1, rij);
		addToPanel(newFeedbackField);
	}

	
	protected void initbtnRemoveFeedback(int rij, Action action) {
		btnRemoveFeedback = new JButton("Remove");
		changeConstraints(1, 2, 1, rij);		
		btnRemoveFeedback.setAction(action);
		btnRemoveFeedback.setActionCommand("RemoveFeedback");
		btnRemoveFeedback.setText("Remove");
		addToPanel(btnRemoveFeedback);
	}


	protected void initTitle(int rij,DocumentListener a) {
		changeConstraints(1, 1, 0, rij);
		addToPanel(new JLabel("Title: "));

		changeConstraints(1, 2, 1, rij);
		titleField = new JTextField();
		titleField.getDocument().addDocumentListener(a);
		addToPanel(titleField);
	}

	protected void initDescription(int rij) {
		changeConstraints(1, 1, 0, rij);
		addToPanel(new JLabel("Description: "));

		changeConstraints(1, 2, 1, rij);
		descriptionField = new JTextField();
		addToPanel(descriptionField);
	}

	protected void initStandardFeedback(int rij) {
		changeConstraints(1, 1, 0, rij);
		addToPanel(new JLabel("Feedback (select all that apply): "));

		changeConstraints(1, 2, 1, rij);
		feedbackField = new CheckBoxList();
		addToPanel(new JScrollPane(feedbackField));
	}

	protected void initButtons(int rij, Action action) {
		btnCancel = new JButton("Cancel");
		changeConstraints(1, 1, 0, rij);		
		btnCancel.setAction(action);
		btnCancel.setActionCommand("Cancel");
		btnCancel.setText("Cancel");
		addToPanel(btnCancel);

		btnOK = new JButton("Save");
		btnOK.isDefaultButton();		
		changeConstraints(1, 2, 1, rij);
		btnOK.setAction(action);
		btnOK.setActionCommand("Save");
		btnOK.setText("Save");
		addToPanel(btnOK);
	}

	private void initConstraints() {
		constraints.insets = new Insets(10, 20, 10, 20);
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 1.0;
		constraints.weighty = 1.0;
	}

	protected GridBagConstraints getConstraints() {
		return constraints;
	}
	
	protected void addToPanel(Component component) {
		add(component, getConstraints());
	}

	protected void changeConstraints(int height, int width, int gridx, int gridy) {
		constraints.gridheight = height;
		constraints.gridwidth = width;
		constraints.gridx = gridx;
		constraints.gridy = gridy;
	}

	private Category getCategory() {
		return category;
	}
	
	public String getSelectedValue(){
		return ((JCheckBox) feedbackField.getSelectedValue()).getText();
	}

	public void setCategory(Category category) {
		this.category = category;
		update();
	}
	
	public Category getCreatedCategory() throws DomainException{

			getCategory().setName(titleField.getText());
			getCategory().setDescription(descriptionField.getText());
			ListModel<JCheckBox> boxes = feedbackField.getModel();
			ArrayList<Feedback> selectedFeedbacks = new ArrayList<Feedback>();
			for (int i = 0; i < boxes.getSize(); i++) {
				if(boxes.getElementAt(i).isSelected())
					selectedFeedbacks.add(getFeedbackByName(boxes.getElementAt(i).getText()));
			}
			getCategory().setFeedbacks(selectedFeedbacks);
				
		return getCategory();
	}

	public Feedback getFeedbackByName(String text){
		for (Feedback f : getFeedbacks()) {
			if(f.getText().equals(text))
				return f;
		}
		return null;
	}

	private List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
		update();
	}
	
	public List<Feedback> getFeedbacks(){
		return feedbacks;
	}
	
	public void setFeedbacks(List<Feedback> feedbacks){
		this.feedbacks = feedbacks;
		update();
	}

	
	private void update() {
		if (getFeedbacks() != null) {
			ArrayList<JCheckBox> feedModel = new ArrayList<JCheckBox>();
			for (Feedback f : getFeedbacks()) {
				JCheckBox box = new JCheckBox(f.getText());

				if(getCategory().getFeedbacks().contains(f))
					box.setSelected(true);
				feedModel.add(box);
					
			}
			feedbackField.setListData(feedModel.toArray());;
		}

		if (getCategory() != null) {
			titleField.setText(getCategory().getName());
			descriptionField.setText(getCategory().getDescription());
		}
	}

	public String getCategoryTitle(){
		return titleField.getText();
	}

	public void LockTitleField(){
		this.titleField.setBackground(new Color(205, 201, 201));
		this.titleField.setEditable(false);
	}
	
	public void unlockTitleField(){
		this.titleField.setBackground(Color.WHITE);
		this.titleField.setEditable(true);
	}
	
	
	public boolean isEditScreen() {
		return editScreen;
	}

	public void setEditScreen(boolean editScreen) {
		this.editScreen = editScreen;
	}
	
	public void setEdit(){
		setEditScreen(true);
		LockTitleField();
	}
	public void setNewScreen(){
		setEditScreen(false);
		unlockTitleField();
	}
}
