package controller;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import view.ViewException;
import view.panels.ExerciseOverviewPanel;
import view.panels.ExerciseTableModel;
import domain.DomainException;
import domain.Exercise;
import domain.FacadeActionManager;

public class ExerciseRemoveAction extends AbstractTestAction {
	private static final long serialVersionUID = 1L;
	private ExerciseOverviewPanel exerciseOverviewPanel;

	public ExerciseRemoveAction(FacadeActionManager service) {
		super(service,"Remove");
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ExerciseTableModel tablem = getExerciseOverviewPanel().getModel();
		Exercise clickedExercise = (Exercise) tablem.getExerciseAt(getExerciseOverviewPanel().getSelectedRow());
		try {
			getService().removeSimilarExercises(clickedExercise);
			
			List<Exercise> exercises = new ArrayList<Exercise>(getService().getUniqueExercises());
			
			getExerciseOverviewPanel().setExercises(exercises);
			getExerciseOverviewPanel().update();
		}
		 catch (ViewException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

	public ExerciseOverviewPanel getExerciseOverviewPanel() {
		return exerciseOverviewPanel;
	}

	public void setExerciseOverviewPanel(ExerciseOverviewPanel exerciseOverviewPanel) {
		this.exerciseOverviewPanel = exerciseOverviewPanel;
	}

	
	

}
