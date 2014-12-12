package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import domain.enums.QuestionType;
import view.panels.ExerciseDetailPanel;

public class ExerciseTypeListener implements ActionListener {
	private ExerciseDetailPanel exerciseDetailpanel;
	
	public ExerciseTypeListener() {

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String type=this.getExerciseDetailpanel().getSelectedType();
		
		if(QuestionType.getQuestionTypeByDescription(type) == QuestionType.YesNoQuestions){
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