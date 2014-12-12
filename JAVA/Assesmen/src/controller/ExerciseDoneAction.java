package controller;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import view.panels.ExerciseDetailPanel;
import view.panels.ExerciseOverviewPanel;
import domain.Exercise;
import domain.FacadeActionManager;

public class ExerciseDoneAction extends AbstractTestAction {
	private ExerciseDetailPanel exercisedetailPanel;
	private ExerciseOverviewPanel exerciseOverviewPanel;
	
	public ExerciseDoneAction(FacadeActionManager service) {
		super(service);
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getActionCommand().equals("SaveNewExercise")){
			try{
				ArrayList<Exercise> ex=getExercisedetailPanel().getCreatedExercises();
				for(Exercise e:ex){
					
					System.out.println(e.getFeedback().getText());
					System.out.println(e.getQuestion().getQuestion());
				}
			}
			catch (Exception ex){
				ex.printStackTrace();
			}
		}
		else{
			setPanelAsContentForView(getExerciseOverviewPanel());		
		}

	}


	public ExerciseDetailPanel getExercisedetailPanel() {
		return exercisedetailPanel;
	}


	public void setExercisedetailPanel(ExerciseDetailPanel exercisedetailPanel) {
		this.exercisedetailPanel = exercisedetailPanel;
	}


	public ExerciseOverviewPanel getExerciseOverviewPanel() {
		return exerciseOverviewPanel;
	}


	public void setExerciseOverviewPanel(ExerciseOverviewPanel exerciseOverviewPanel) {
		this.exerciseOverviewPanel = exerciseOverviewPanel;
	}
	
	
	

}
