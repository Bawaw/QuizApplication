package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.MainViewAdmin;
import view.panels.ExerciseDetailPanel;
import domain.Category;
import domain.FacadeActionManager;

public class CategorySelectionListener implements ActionListener {
	private ExerciseDetailPanel exerciseDetailPanel;
	private FacadeActionManager service;
	private MainViewAdmin view;

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
			this.getView().pack();
		}
	}

	
	
	public MainViewAdmin getView() {
		return view;
	}

	public void setView(MainViewAdmin view) {
		this.view = view;
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