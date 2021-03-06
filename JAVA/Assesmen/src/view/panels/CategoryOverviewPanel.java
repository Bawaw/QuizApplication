package view.panels;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.util.List;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import view.ViewException;

import domain.Category;

public class CategoryOverviewPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private GridBagConstraints constraints = new GridBagConstraints();
	private List<Category> categories;
	private JTable table;
	private JButton btnNew, btnRemove;
	private TableModel tableModel;

	public CategoryOverviewPanel(MouseAdapter editAction, Action newAction,
			Action removeAction) throws ViewException {
		setLayout(new GridBagLayout());
		initConstraints();
		int row = 0;
		initListTitle(row);
		initList(++row, editAction);
		row += 10;
		initButtons(row, newAction, removeAction);
	}

	private void initListTitle(int rij) {
		changeConstraints(1, 1, 0, rij);
		addToPanel(new JLabel("Categories:"));

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
		btnNew.setActionCommand("NewCategory");
		btnNew.setText("New");
		addToPanel(btnNew);

		btnRemove = new JButton("Remove");
		changeConstraints(1, 1, 2, row);
		btnRemove.setAction(removeAction);
		btnRemove.setActionCommand("RemoveCategory");
		btnRemove.setText("Remove");
		addToPanel(btnRemove);
	}

	public void update() throws ViewException {
		tableModel = new CategoryTableModel(getCategories());
		table.setModel(tableModel);
	}

	public CategoryTableModel getModel() {
		return (CategoryTableModel) table.getModel();
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

	private List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

}
