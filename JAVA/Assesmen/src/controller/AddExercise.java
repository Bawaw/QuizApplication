package controller;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import view.panels.ExerciseDetailPanel;
import domain.Answer;
import domain.Category;
import domain.Exercise;
import domain.FacadeActionManager;
import domain.Feedback;
import domain.Question;
import domain.enums.QuestionType;

public class AddExercise extends AbstractTestAction {
	private Question safeDummy;
	private ExerciseDetailPanel exerciseDetailPanel;
	
	public AddExercise(FacadeActionManager service) {
		super(service);
		try{
			this.setSafeDummy(this.getService().createQuestion(QuestionType.YesNoQuestions, "dummy", new Answer("Yes"), 30));
			}
			catch(Exception ex){
				
			}
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		Category c=this.getExerciseDetailPanel().getSelectedCategory();
		Feedback f=this.getExerciseDetailPanel().getSelectedFeedback();
		int score=this.getExerciseDetailPanel().getScoreValue();
		try{
		Exercise ex=new Exercise(this.getSafeDummy(), c, f, score);
		this.getExerciseDetailPanel().addExerciseLocally(ex);
		this.getExerciseDetailPanel().update();
		}
		catch(Exception ex){
			JOptionPane.showMessageDialog(super.getView(),ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			ex.printStackTrace();
		}
	}


	public Question getSafeDummy() {
		return safeDummy;
	}


	public void setSafeDummy(Question safeDummy) {
		this.safeDummy = safeDummy;
	}


	public ExerciseDetailPanel getExerciseDetailPanel() {
		return exerciseDetailPanel;
	}


	public void setExerciseDetailPanel(ExerciseDetailPanel exerciseDetailPanel) {
		this.exerciseDetailPanel = exerciseDetailPanel;
	}

	

}
