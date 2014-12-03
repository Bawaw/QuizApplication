package view.panels;

import java.awt.Checkbox;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.Action;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListModel;

import view.CheckBoxList;
import domain.Category;
import domain.DomainException;
import domain.Feedback;


public class CategoryDetailPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private GridBagConstraints constraints = new GridBagConstraints();
	private JButton btnOK, btnCancel;
	private JTextField titleField, descriptionField; 
	private CheckBoxList feedbackField;
	private Category category;
	private List<Category> categories;
	private List<Feedback> feedbacks;
	
	public CategoryDetailPanel(Action action) {
		setCategory(category);
		setCategories(categories);
		setFeedbacks(feedbacks);
		
		setLayout(new GridBagLayout());
		initConstraints();
		int rij = 0;
		initTitle(++rij);
		initDescription(++rij);
		initStandardFeedback(++rij);
		initButtons(++rij, action);
	}

	protected void initTitle(int rij) {
		changeConstraints(1, 1, 0, rij);
		addToPanel(new JLabel("Title: "));

		changeConstraints(1, 1, 1, rij);
		titleField = new JTextField();
		addToPanel(titleField);
	}

	protected void initDescription(int rij) {
		changeConstraints(1, 1, 0, rij);
		addToPanel(new JLabel("Description: "));

		changeConstraints(1, 1, 1, rij);
		descriptionField = new JTextField();
		addToPanel(descriptionField);
	}

	protected void initStandardFeedback(int rij) {
		changeConstraints(1, 1, 0, rij);
		addToPanel(new JLabel("Feedback: "));

		changeConstraints(1, 1, 1, rij);
		feedbackField = new CheckBoxList();
		addToPanel(feedbackField);
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
		changeConstraints(1, 1, 1, rij);
		btnOK.setAction(action);
		btnOK.setActionCommand("Save");
		btnOK.setText("Save");
		addToPanel(btnOK);
	}

	private void initConstraints() {
		constraints.insets = new Insets(10, 10, 0, 10);
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

	public void setCategory(Category category) {
		this.category = category;
		update();
	}
	
	public Category getCreatedCategory(){
		
		try {
			getCategory().setName(titleField.getText());
			getCategory().setDescription(descriptionField.getText());
			ListModel<JCheckBox> boxes = feedbackField.getModel();
			ArrayList<Feedback> selectedFeedbacks = new ArrayList<Feedback>();
			for (int i = 0; i < boxes.getSize(); i++) {
				if(boxes.getElementAt(i).isSelected())
					selectedFeedbacks.add(getFeedbackByName(boxes.getElementAt(i).getText()));
			}
			getCategory().setFeedbacks(selectedFeedbacks);
		} catch (DomainException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
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

}