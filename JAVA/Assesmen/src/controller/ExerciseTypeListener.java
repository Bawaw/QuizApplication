package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.panels.ExerciseDetailPanel;

public class ExerciseTypeListener implements ActionListener {
	private ExerciseDetailPanel exerciseDetailpanel;
	
	public ExerciseTypeListener() {

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String type=this.getExerciseDetailpanel().getSelectedType();
		
		if(type.equals("Yes Or No Question")){
			this.getExerciseDetailpanel().lockForYesNo();

		}
		else{
			this.getExerciseDetailpanel().lockForMultipleChoice();
		}
	}

	public ExerciseDetailPanel getExerciseDetailpanel() {
		return exerciseDetailpanel;
	}

	public void setExerciseDetailpanel(ExerciseDetailPanel exerciseDetailpanel) {
		this.exerciseDetailpanel = exerciseDetailpanel;
	}

	

	
	
}