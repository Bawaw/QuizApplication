package view.panels;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import view.ViewException;
import domain.Participation;

public class ParticipationPanel extends JPanel {
	private GridBagConstraints constraints = new GridBagConstraints();
	private List<Participation> participations;
	private JTable table;
	private JButton btnParticipation;
	private TableModel tableModel;
	
	public ParticipationPanel(Action a) {
		setLayout(new GridBagLayout());
		initConstraints();
		int row = 0;
		initList(row);
		row=row+6;
		intiNewButton(a,row);
	}
	
	private void intiNewButton(Action a,int row){
		btnParticipation = new JButton("New");
		changeConstraints(1, 1, 1, row);
		btnParticipation.setAction(a);
		btnParticipation.setActionCommand("new");
		btnParticipation.setText("New test");
		addToPanel(btnParticipation);
	}
	
	private void initList(int row) {
		table = new JTable();
		changeConstraints(6, 3, 0, row);
		addToPanel(new JScrollPane(table));
	}
	
	public void update() throws ViewException {
		tableModel = new ParticipationTableModel(getParticipations());
		table.setModel(tableModel);
		updateRowHeights();
	}
	
	public ParticipationTableModel getModel() {
		return (ParticipationTableModel) table.getModel();
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


	public List<Participation> getParticipations() {
		return participations;
	}


	public void setParticipations(List<Participation> participations) {
		this.participations = participations;
	}

	//http://stackoverflow.com/questions/1783607/auto-adjust-the-height-of-rows-in-a-jtable
		private void updateRowHeights()
		{
		    try
		    {
		        for (int row = 0; row < table.getRowCount(); row++)
		        {
		            int rowHeight = table.getRowHeight();

		            for (int column = 2; column < table.getColumnCount(); column++)
		            {
		                Component comp = table.prepareRenderer(table.getCellRenderer(row, column), row, column);
		                rowHeight = Math.max(rowHeight, comp.getPreferredSize().height);
		            }
		            table.setRowHeight(row, rowHeight);
		        }
		    }
		    catch(ClassCastException e) {}
		}
	
	
}
