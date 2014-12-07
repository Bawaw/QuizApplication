package controller;

import java.awt.event.ActionEvent;

import view.panels.ExerciseOverviewPanel;
import domain.FacadeActionManager;

public class ExerciseOverviewAction extends AbstractTestAction {
	private static final long serialVersionUID = 1L;
	private ExerciseOverviewPanel exererciseOverviewPanel;

	public ExerciseOverviewAction(FacadeActionManager service) {
		super(service,"Exercises");
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

	public ExerciseOverviewPanel getExererciseOverviewPanel() {
		return exererciseOverviewPanel;
	}

	public void setExererciseOverviewPanel(
			ExerciseOverviewPanel exererciseOverviewPanel) {
		this.exererciseOverviewPanel = exererciseOverviewPanel;
	}

	
}
