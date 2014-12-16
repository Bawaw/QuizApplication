package view.panels;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import domain.Category;
import domain.Exercise;

public class ExerciseDetailTableModel extends AbstractTableModel {

	private List<Exercise> exercises;
	private String[] columnNames = { "Category", "Feedback","Score" };

	public ExerciseDetailTableModel(List<Exercise> exercises2) {
		setExercises(exercises2);
	}

	public List<Exercise> getExercises() {
		return exercises;
	}

	public void setExercises(List<Exercise> exercises) {
		this.exercises = exercises;
	}
	
	public Exercise getExerciseAt(int rowIndex){
		Exercise exercise = null;
		exercise = exercises.get(rowIndex);
		return exercise;
	}

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
		Exercise exercise = null;
		exercise = this.exercises.get(row);
		switch (column) {
		case 0:
			return exercise.getCategory().getName();
		case 1:
			return exercise.getFeedback().getText();
		case 2: 
			return exercise.getScore();
		default:
			return "";
		}

	}
	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}
}
