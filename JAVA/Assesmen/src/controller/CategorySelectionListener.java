package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import domain.Category;
import domain.FacadeActionManager;
import view.panels.ExerciseDetailPanel;
import view.panels.SettingsOverviewPanel;

public class CategorySelectionListener implements ActionListener {
	private ExerciseDetailPanel exerciseDetailPanel;
	private FacadeActionManager service;

	public CategorySelectionListener(FacadeActionManager service) {
		setService(service);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Category selectedCategory = getExerciseDetailPanel()
				.getSelectedCategory();
		if (selectedCategory != null) {
			getExerciseDetailPanel().setFeedbacks(
					selectedCategory.getFeedbacks());
			getExerciseDetailPanel().updateFeedback();
		}
	}

	public ExerciseDetailPanel getExerciseDetailPanel() {
		return exerciseDetailPanel;
	}

	public void setExerciseDetailPanel(ExerciseDetailPanel exerciseDetailPanel) {
		this.exerciseDetailPanel = exerciseDetailPanel;
	}

	public FacadeActionManager getService() {
		return service;
	}

	public void setService(FacadeActionManager service) {
		this.service = service;
	}

}