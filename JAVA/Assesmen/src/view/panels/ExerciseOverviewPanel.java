package view.panels;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.List;

import javax.swing.Action;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import view.ViewException;
import domain.Category;
import domain.Exercise;

public class ExerciseOverviewPanel extends JPanel{
	private JButton btnNew,btnRemove;
	private JTable table;
	private JComboBox<String> categories;
	private GridBagConstraints constraints = new GridBagConstraints();
	private List<Exercise> exercises;
	private TableModel tableModel;
	
	public ExerciseOverviewPanel(MouseAdapter editAction, Action newAction,
			Action removeAction,ActionListener acl) throws ViewException{
		setLayout(new GridBagLayout());
		initConstraints();
		int row = 0;
		initCategories(row,acl);
		initListTitle(row);
		initList(++row, editAction);
		row += 10;
		initButtons(row, newAction, removeAction);
	}
	
	private void initListTitle(int rij) {
		changeConstraints(1, 1, 0, rij);
		addToPanel(new JLabel("Exercises:"));

	}

	private void initCategories(int row,ActionListener acl){
		
		changeConstraints(1, 3, 0, row);
		categories=new JComboBox<String>();
		categories.addActionListener(acl);
		addToPanel(categories);
	}
	
	private void initList(int row, MouseAdapter action) {
		table = new JTable();
		table.addMouseListener(action);
		changeConstraints(6, 3, 0, row);
		addToPanel(new JScrollPane(table));
	}

	private void initButtons(int row, Action newAction, Action removeAction) {
		btnNew = new JButton("New");
		changeConstraints(1, 1, 1, row);
		btnNew.setAction(newAction);
		btnNew.setActionCommand("NewExercise");
		btnNew.setText("New");
		addToPanel(btnNew);

		btnRemove = new JButton("Remove");
		changeConstraints(1, 1, 2, row);
		btnRemove.setAction(removeAction);
		btnRemove.setActionCommand("RemoveExercise");
		btnRemove.setText("Remove");
		addToPanel(btnRemove);
	}

	public void update() throws ViewException {
		tableModel = new ExerciseTableModel(getExercises());
		table.setModel(tableModel);
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
	
	public int getSelectedRow(){
		return table.getSelectedRow();
	}

	protected void changeConstraints(int height, int width, int gridx, int gridy) {
		constraints.gridheight = height;
		constraints.gridwidth = width;
		constraints.gridx = gridx;
		constraints.gridy = gridy;
	}

	public List<Exercise> getExercises() {
		return exercises;
	}

	public void setExercises(List<Exercise> exercises) {
		this.exercises = exercises;
	}
	
	public ExerciseTableModel getModel(){
		return (ExerciseTableModel)this.tableModel;
	}
	
	
	public void setCategoriesOptions(List<Category> AllCat,int index){
		String[] catNames = new String[AllCat.size()+1];
		catNames[0]="All categories";
		for (int i = 0; i < AllCat.size(); i++) {
			catNames[i+1] = AllCat.get(i).getName();
		}
		DefaultComboBoxModel model = new DefaultComboBoxModel(catNames);
		this.categories.setModel(model);
		this.categories.setSelectedIndex(index);
	}

	public int getSelectedCategoryIndex(){
		return this.categories.getSelectedIndex();
	}
	
	public String getSelectedCategory(){
		return (String)this.categories.getSelectedItem();
	}
}
