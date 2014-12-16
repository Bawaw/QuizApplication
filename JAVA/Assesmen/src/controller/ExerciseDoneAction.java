package controller;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import view.panels.ExerciseDetailPanel;
import view.panels.ExerciseOverviewPanel;
import domain.DomainException;
import domain.Exercise;
import domain.FacadeActionManager;

public class ExerciseDoneAction extends AbstractTestAction {
	private static final long serialVersionUID = 1L;
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
				if(this.getExercisedetailPanel().getNumberOfExercises()<1){
					throw new DomainException("Select at least one category!");
				}
				
				ArrayList<Exercise> ex=getExercisedetailPanel().getCreatedExercises();
				this.getService().updateExercises(ex);
				updateOverview();
			}
			catch (Exception ex){
				JOptionPane.showMessageDialog(super.getView(),getRootCause(ex).getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
				ex.printStackTrace();
			}
		}
		else{
			updateOverview();
			
		}

	}

	private void updateOverview(){
		try{
		//this.getExerOverviewAction().setExercisesBasedOnSelector();
		//this.getExerOverviewAction().update();
		//this.setPanelAsContentForView(this.getExerciseOverviewPanel());
			this.getExerOverviewAction().actionPerformed(null);
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
	
	
	//http://stackoverflow.com/questions/9017820/exception-getmessage-output-with-class-name
	public static Throwable getRootCause(Throwable throwable) {
	    if (throwable.getCause() != null)
	        return getRootCause(throwable.getCause());

	    return throwable;
	}
	

}
