package controller;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;

import view.panels.CategoryTableModel;
import view.panels.ExerciseDetailPanel;
import view.panels.ExerciseTableModel;
import domain.Category;
import domain.Exercise;
import domain.FacadeActionManager;
import domain.Feedback;

public class ExerciseEditAction extends AbstractTestMouseAdapter {
	private ExerciseDetailPanel exerciseDetailPanel;
	
	public ExerciseEditAction(FacadeActionManager service) {
		super(service);
		// TODO Auto-generated constructor stub
	}
	
	public void mouseClicked(MouseEvent evt) {
		if (evt.getClickCount() == 2 && !evt.isConsumed()) {
			JTable table = (JTable) (evt.getSource());
			ExerciseTableModel tablem = (ExerciseTableModel) (table.getModel());
			
			Exercise clickedExercise = (Exercise) tablem.getExerciseAt(table.getSelectedRow());
			ArrayList<Exercise> similiarExercises = this.getService().getExercisesByQuestion(clickedExercise);
			getExerciseDetailPanel().setCommonExercises(similiarExercises);
			
			
			
			getExerciseDetailPanel().setTypeList(this.getService().getAllQuestionTypes(), clickedExercise.getQuestion().getType());
			
			getExerciseDetailPanel().setEdit();
			
			setPanelAsContentForView(getExerciseDetailPanel());
			
			evt.consume();
		}
	}
	public ExerciseDetailPanel getExerciseDetailPanel() {
		return exerciseDetailPanel;
	}
	public void setExerciseDetailPanel(ExerciseDetailPanel exerciseDetailPanel) {
		this.exerciseDetailPanel = exerciseDetailPanel;
	}
	
	
}