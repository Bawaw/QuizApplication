package view.panels;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import domain.Exercise;

public class ExerciseTableModel extends AbstractTableModel{
	
	private List<Exercise> exercises;
	private String[] columnNames = { "QuestionType", "Question" };

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return exercises.size();
	}

	@Override
	public Object getValueAt(int row, int column) {
		Exercise exercise=null;
		exercise=this.exercises.get(row);
		switch (column) {
		case 0:
			return exercise.getClass().toString();
		case 1:
			return exercise.getQuestion().getQuestion();
	
		default:
			return "";
		}

	}
}
