package controller;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JOptionPane;

import view.panels.EvaluationPanel;
import view.panels.ParticipationPanel;
import domain.Answer;
import domain.Evaluation;
import domain.FacadeActionManager;

public class EvaluationController extends AbstractTestAction {
	private EvaluationPanel evaluationPanel;
	private ParticipationAction participationAction;
	
	public EvaluationController(FacadeActionManager service) {
		super(service,"Evaluation");
	}

	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("new")){
			try{
			this.getService().createEvaluation();	
			update();
			setPanelAsContentForView(getEvaluationPanel());
			}
			catch(Exception ex){
				JOptionPane.showMessageDialog(super.getView(),ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
				ex.printStackTrace();
			}
		}
		else if(e.getActionCommand().equals("stop")){
			goBackToOverview();
		}
		else if(e.getActionCommand().equals("next")){
			setAnswerForCurrentQuestion();
			this.getService().getActiveEvaluation().increaseIndex();
			update();
		}
		else if(e.getActionCommand().equals("prev")){
			setAnswerForCurrentQuestion();
			this.getService().getActiveEvaluation().decreaseIndex();
			update();
		}
		else if(e.getActionCommand().equals("finish")){
			setAnswerForCurrentQuestion();
			Evaluation activeEvaluation=this.getService().getActiveEvaluation();
			activeEvaluation.finish();
			
			//System.out.println(activeEvaluation);
			
			JOptionPane.showMessageDialog(this.getView(),activeEvaluation.getSpecificFeedback());
			
			
			
			
			try{
				this.getService().addParticipation(activeEvaluation.getEvaluationReport());	
			}
			catch(Exception ex){
				JOptionPane.showMessageDialog(super.getView(),ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
				ex.printStackTrace();
			}

			goBackToOverview();
			
		}

	}

	private void goBackToOverview(){
		this.getParticipationAction().actionPerformed(null);
	}
	
	private void update(){
		Evaluation e=this.getService().getActiveEvaluation();
		List<Answer> answers = e.getAnswers();
		String category=e.getCategoryCurrentExercise();
		int points=e.getPointsCurrentExercise();
		Answer currentAnswer=e.getCurrentSelectedAnswer();
		String question=e.getCurrentQuestion();
		boolean hasNext=e.hasNextQuestion();
		boolean hasPrev=e.hasPreviousQuestion();
		int currentIndex=e.questionNumber();
		int totalQ=e.getNumberOfQuestions();
		
		this.getEvaluationPanel().update(question,answers,currentAnswer,hasNext,hasPrev,currentIndex,totalQ,category,points);
	}
	
	private void setAnswerForCurrentQuestion(){
		try{
			this.getService().getActiveEvaluation().answerCurrentQuestion(getEvaluationPanel().getSelectedAnswer());
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}

	
	







	public ParticipationAction getParticipationAction() {
		return participationAction;
	}



	public void setParticipationAction(ParticipationAction participationAction) {
		this.participationAction = participationAction;
	}



	public EvaluationPanel getEvaluationPanel() {
		return evaluationPanel;
	}



	public void setEvaluationPanel(EvaluationPanel evaluationPanel) {
		this.evaluationPanel = evaluationPanel;
	}
	
	

}
