package view.panels;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import domain.Exercise;

public class ExerciseTableModel extends AbstractTableModel {

	private List<Exercise> exercises;
	private String[] columnNames = { "QuestionType", "Question" };

	public ExerciseTableModel(List<Exercise> exercises2) {
		setExercises(exercises2);
	}

	public List<Exercise> getExercises() {
		return exercises;
	}

	public void setExercises(List<Exercise> exercises) {
		this.exercises = exercises;
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
	
	public Exercise getExerciseAt(int rowIndex){
		Exercise exercise = null;
		exercise = exercises.get(rowIndex);
		return exercise;
	}

	@Override
	public Object getValueAt(int row, int column) {
		Exercise exercise = null;
		exercise = this.exercises.get(row);
		switch (column) {
		case 0:
			return exercise.getQuestion().getType();
		case 1:
			return exercise.getQuestion().getQuestion();

		default:
			return "";
		}

	}
	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}
}
