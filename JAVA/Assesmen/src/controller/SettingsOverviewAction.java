package controller;

import java.awt.event.ActionEvent;

import view.panels.SettingsOverviewPanel;
import domain.FacadeActionManager;

public class SettingsOverviewAction extends AbstractTestAction {
	private static final long serialVersionUID = 1L;
	private SettingsOverviewPanel overviewPanel;

	public SettingsOverviewAction(FacadeActionManager service) {
		super(service,"Settings");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent arg0){
		try{
		String[] selectList= this.getService().getAllQuestionSelectionBehaviours();
		String[] scoreList=this.getService().getAllScoreBehaviours();
		String[] evalList=this.getService().getAllEvaluationTypes();
		String[] questionTypes=this.getService().getAllQuestionTypesAdmin();
		
		String currentScoreBehaviour=this.getService().currentScoreBehaviourName();
		String currentSelectionBehaviour=this.getService().currentQuestionSelectionBehaviour();
		String currentEvalType=this.getService().currentEvaluationType();
		String currentQuestionType=this.getService().currentQuestionType();
		int numberOfQuestions=this.getService().getNumberofQuestionForEvaluation();
		
		getOverviewPanel().setSelectList(selectList,currentSelectionBehaviour);
		getOverviewPanel().setQuestionType(questionTypes, currentQuestionType);
		getOverviewPanel().setScoreList(scoreList,currentScoreBehaviour);
		getOverviewPanel().setEvalList(evalList, currentEvalType);
		getOverviewPanel().setAmount(numberOfQuestions);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		setPanelAsContentForView(getOverviewPanel());

	}

	
	private SettingsOverviewPanel getOverviewPanel() {
		return overviewPanel;
	}

	public void setOverviewPanel(SettingsOverviewPanel overviewPanel) {
		this.overviewPanel = overviewPanel;
	}
	

}
