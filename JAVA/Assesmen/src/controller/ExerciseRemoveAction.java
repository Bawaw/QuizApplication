package controller;

import java.awt.event.ActionEvent;

import view.panels.ExerciseOverviewPanel;
import view.panels.ExerciseTableModel;
import domain.Exercise;
import domain.FacadeActionManager;

public class ExerciseRemoveAction extends AbstractTestAction {
	private static final long serialVersionUID = 1L;
	private ExerciseOverviewPanel exerciseOverviewPanel;
	private ExerciseOverviewAction exerciseOverviewAction;

	public ExerciseRemoveAction(FacadeActionManager service) {
		super(service, "Remove");

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ExerciseTableModel tablem = getExerciseOverviewPanel().getModel();
		Exercise clickedExercise = (Exercise) tablem
				.getExerciseAt(getExerciseOverviewPanel().getSelectedRow());

		getService().removeSimilarExercises(clickedExercise);
		this.getExerciseOverviewAction().setExercisesBasedOnSelector();
		this.getExerciseOverviewAction().update();

	}

	public ExerciseOverviewPanel getExerciseOverviewPanel() {
		return exerciseOverviewPanel;
	}

	public void setExerciseOverviewPanel(
			ExerciseOverviewPanel exerciseOverviewPanel) {
		this.exerciseOverviewPanel = exerciseOverviewPanel;
	}

	public ExerciseOverviewAction getExerciseOverviewAction() {
		return exerciseOverviewAction;
	}

	public void setExerciseOverviewAction(
			ExerciseOverviewAction exerciseOverviewAction) {
		this.exerciseOverviewAction = exerciseOverviewAction;
	}

}
