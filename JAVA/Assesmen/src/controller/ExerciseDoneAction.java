package controller;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import view.panels.ExerciseDetailPanel;
import view.panels.ExerciseOverviewPanel;
import domain.Exercise;
import domain.FacadeActionManager;

public class ExerciseDoneAction extends AbstractTestAction {
	private ExerciseDetailPanel exercisedetailPanel;
	private ExerciseOverviewPanel exerciseOverviewPanel;
	private ExerciseOverviewAction exerOverviewAction;
	
	
	public ExerciseDoneAction(FacadeActionManager service) {
		super(service);
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getActionCommand().equals("SaveNewExercise")){
			try{
				ArrayList<Exercise> ex=getExercisedetailPanel().getCreatedExercises();
				this.getService().updateExercises(ex);
				updateOverview();
			}
			catch (Exception ex){
				JOptionPane.showMessageDialog(super.getView(),ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
				ex.printStackTrace();
			}
		}
		else{
			updateOverview();
			
		}

	}

	private void updateOverview(){
		try{
		this.getExerOverviewAction().actionPerformed(null);	
		this.getExerciseOverviewPanel().update();
		}
		catch(Exception ex){
			JOptionPane.showMessageDialog(super.getView(),"Reloading exercises failed!","Error",JOptionPane.ERROR_MESSAGE);
			ex.printStackTrace();
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


	public ExerciseOverviewAction getExerOverviewAction() {
		return exerOverviewAction;
	}


	public void setExerOverviewAction(ExerciseOverviewAction exerOverviewAction) {
		this.exerOverviewAction = exerOverviewAction;
	}
	
	
	

}
