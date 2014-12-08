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
	private ExerciseOverviewPanel exererciseOverviewPanel;

	public ExerciseOverviewAction(FacadeActionManager service) {
		super(service, "Exercises");
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// Listener to get all categories from service...
		List<Exercise> exercises = new ArrayList<Exercise>(getService().getExercisePool().getUniqueExerciseSet());
		
		getExererciseOverviewPanel().setExercises(exercises);
		try {
			getExererciseOverviewPanel().update();
		} catch (ViewException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		setPanelAsContentForView(getExererciseOverviewPanel());

	}

	public ExerciseOverviewPanel getExererciseOverviewPanel() {
		return exererciseOverviewPanel;
	}

	public void setExererciseOverviewPanel(
			ExerciseOverviewPanel exererciseOverviewPanel) {
		this.exererciseOverviewPanel = exererciseOverviewPanel;
	}

}
