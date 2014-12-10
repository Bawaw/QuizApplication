package controller;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import view.ViewException;
import view.panels.CategoryDetailPanel;
import view.panels.CategoryOverviewPanel;
import view.panels.CategoryTableModel;
import view.panels.ExerciseDetailPanel;
import domain.Category;
import domain.DomainException;
import domain.FacadeActionManager;
import domain.Feedback;

public class ExerciseCategoryRemoveAction extends AbstractTestAction {
	private static final long serialVersionUID = 1L;
	private ExerciseDetailPanel exerciseDetailPanel;

	public ExerciseCategoryRemoveAction(FacadeActionManager service) {
		super(service, "New");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(this.getExerciseDetailPanel().getNumberOfExercises()>1){
		getExerciseDetailPanel().removeExerciseLocally(getExerciseDetailPanel().getSelectedExerciseIndex());
		getExerciseDetailPanel().updateForEdit();
		}
		else{
			JOptionPane.showMessageDialog(super.getView(),"You need at least one exercise!","Error",JOptionPane.ERROR_MESSAGE);
		}
	}

	public ExerciseDetailPanel getExerciseDetailPanel() {
		return exerciseDetailPanel;
	}

	public void setExerciseDetailPanel(ExerciseDetailPanel exerciseDetailPanel) {
		this.exerciseDetailPanel = exerciseDetailPanel;
	}

}
