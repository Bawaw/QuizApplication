package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExerciseoverviewCategoryListener implements ActionListener {
	private ExerciseOverviewAction exerciseOverviewAction;

	@Override
	public void actionPerformed(ActionEvent arg0) {
		this.getExerciseOverviewAction().setIndex();
		this.getExerciseOverviewAction().setExercisesBasedOnSelector();
		this.getExerciseOverviewAction().update();

	}

	public ExerciseOverviewAction getExerciseOverviewAction() {
		return exerciseOverviewAction;
	}

	public void setExerciseOverviewAction(
			ExerciseOverviewAction exerciseOverviewAction) {
		this.exerciseOverviewAction = exerciseOverviewAction;
	}

}
