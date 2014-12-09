package controller;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import view.ViewException;
import view.panels.ExerciseOverviewPanel;
import domain.Category;
import domain.Exercise;
import domain.FacadeActionManager;

public class ExerciseOverviewAction extends AbstractTestAction {
	private static final long serialVersionUID = 1L;
	private ExerciseOverviewPanel exerciseOverviewPanel;

	public ExerciseOverviewAction(FacadeActionManager service) {
		super(service, "Exercises");
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// Listener to get all categories from service...
		List<Exercise> exercises = new ArrayList<Exercise>(getService().getUniqueExercises());
		
		getExerciseOverviewPanel().setExercises(exercises);
		try {
			getExerciseOverviewPanel().update();
		} catch (ViewException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		setPanelAsContentForView(getExerciseOverviewPanel());

	}

	public ExerciseOverviewPanel getExerciseOverviewPanel() {
		return exerciseOverviewPanel;
	}

	public void setExerciseOverviewPanel(
			ExerciseOverviewPanel exererciseOverviewPanel) {
		this.exerciseOverviewPanel = exererciseOverviewPanel;
	}

}
