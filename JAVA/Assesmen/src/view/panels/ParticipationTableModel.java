package view.panels;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import domain.Evaluation;
import domain.Participation;

public class ParticipationTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;

	private List<Participation> participations;
	private String[] columnNames = { "Datum", "ScoreOn20","Overview" };
	
	public ParticipationTableModel(List<Participation> participations) {
		this.participations=participations;
	}
	
	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return participations.size();
	}

	@Override
	public Object getValueAt(int row, int column) {
		Participation participation = null;
		participation = participations.get(row);

		switch (column) {
		case 0:
			return participation.getDate();
		case 1:
			return participation.getScore();
		case 2:
			return participation.getErrorsFormatted();
			
		default:
			return "";
		}
	}
	

	
	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}

}
