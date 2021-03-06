package controller;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import view.panels.ExerciseDetailPanel;
import domain.FacadeActionManager;

public class ExerciseCategoryRemoveAction extends AbstractTestAction {
	private static final long serialVersionUID = 1L;
	private ExerciseDetailPanel exerciseDetailPanel;

	public ExerciseCategoryRemoveAction(FacadeActionManager service) {
		super(service, "New");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try{
		if(this.getExerciseDetailPanel().getNumberOfExercises()>1){
		getExerciseDetailPanel().removeExerciseLocally(getExerciseDetailPanel().getSelectedExerciseIndex());
		getExerciseDetailPanel().update();
		}
		else{
			JOptionPane.showMessageDialog(super.getView(),"You need at least one exercise!","Error",JOptionPane.ERROR_MESSAGE);
		}
		}
		catch(Exception ex){
			JOptionPane.showMessageDialog(super.getView(),ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
		}
	}

	public ExerciseDetailPanel getExerciseDetailPanel() {
		return exerciseDetailPanel;
	}

	public void setExerciseDetailPanel(ExerciseDetailPanel exerciseDetailPanel) {
		this.exerciseDetailPanel = exerciseDetailPanel;
	}

}
