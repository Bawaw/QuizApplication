package controller;

import java.awt.event.ActionEvent;
import java.util.List;

import view.panels.EvaluationPanel;
import domain.Answer;
import domain.Evaluation;
import domain.FacadeActionManager;

public class EvaluationController extends AbstractTestAction {
	private EvaluationPanel evaluationPanel;
	
	public EvaluationController(FacadeActionManager service) {
		super(service,"Evaluation");
	}

	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("new")){
			System.out.println("start new evaluation");
			try{
			this.getService().createEvaluation();
			System.out.println(this.getService().getActiveEvaluation().getNumberOfQuestions());
			update();
			setPanelAsContentForView(getEvaluationPanel());
			}
			catch(Exception ex){
				ex.printStackTrace();
				System.out.println(ex.getMessage());
			}
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
			System.out.println(this.getService().getActiveEvaluation());
			System.out.println("Your Score "+ this.getService().getActiveEvaluation().calculateScore());
		}

	}

	private void update(){
		Evaluation e=this.getService().getActiveEvaluation();
		List<Answer> answers = e.getAnswers();
		Answer currentAnswer=e.getCurrentSelectedAnswer();
		String question=e.getCurrentQuestion();
		boolean hasNext=e.hasNextQuestion();
		boolean hasPrev=e.hasPreviousQuestion();
		int currentIndex=e.questionNumber();
		int totalQ=e.getNumberOfQuestions();
		
		this.getEvaluationPanel().update(question,answers,currentAnswer,hasNext,hasPrev,currentIndex,totalQ);
	}
	
	private void setAnswerForCurrentQuestion(){
		try{
			this.getService().getActiveEvaluation().answerCurrentQuestion(getEvaluationPanel().getSelectedAnswer());
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}

	
	public EvaluationPanel getEvaluationPanel() {
		return evaluationPanel;
	}



	public void setEvaluationPanel(EvaluationPanel evaluationPanel) {
		this.evaluationPanel = evaluationPanel;
	}
	
	

}
