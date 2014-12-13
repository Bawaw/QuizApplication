package controller;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import view.panels.ExerciseDetailPanel;
import domain.Answer;
import domain.DomainException;
import domain.FacadeActionManager;

public class AnswerActionManager extends AbstractTestAction{
	private ExerciseDetailPanel exerciseDetailPanel;
	
	
	public AnswerActionManager(FacadeActionManager service) {
		super(service);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getActionCommand().equals("AddOption")){
			try{
			String s=this.getExerciseDetailPanel().getNewOption();
			this.getService().addAnswer(s);
			this.getExerciseDetailPanel().addOptionLocally(s);
			//this.getExerciseDetailPanel().setOptions(new ArrayList<Answer> (this.getService().getAnswerPool().getAnswers()));
			this.getExerciseDetailPanel().update();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		else if(arg0.getActionCommand().equals("RemoveOption")){
			
			try {
				String s=this.getExerciseDetailPanel().getSelectedValueOption();
				this.getService().removeAnswer(s);
				this.getExerciseDetailPanel().removeOptionlocally(s);
				this.getExerciseDetailPanel().update();
			} catch (DomainException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else if(arg0.getActionCommand().equals("SelectOption")){
			String s = this.getExerciseDetailPanel().getSelectedValueOption();
			this.getExerciseDetailPanel().setAnswerText(s);
		}
		
	}

	public ExerciseDetailPanel getExerciseDetailPanel() {
		return exerciseDetailPanel;
	}

	public void setExerciseDetailPanel(ExerciseDetailPanel exerciseDetailPanel) {
		this.exerciseDetailPanel = exerciseDetailPanel;
	}

	
}
