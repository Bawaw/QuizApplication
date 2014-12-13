package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import view.MainViewAdmin;
import view.panels.ExerciseDetailPanel;
import domain.enums.QuestionType;

public class ExerciseTypeListener implements ActionListener {
	private ExerciseDetailPanel exerciseDetailpanel;
	private MainViewAdmin view;
	
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
			this.getView().pack();
			this.getView().center();
		}
	}

	public ExerciseDetailPanel getExerciseDetailpanel() {
		return exerciseDetailpanel;
	}

	public void setExerciseDetailpanel(ExerciseDetailPanel exerciseDetailpanel) {
		this.exerciseDetailpanel = exerciseDetailpanel;
	}

	public MainViewAdmin getView() {
		return view;
	}

	public void setView(MainViewAdmin view) {
		this.view = view;
	}

	

	
	
}