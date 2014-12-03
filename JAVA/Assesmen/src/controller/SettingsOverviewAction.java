package controller;

import java.awt.event.ActionEvent;

import javax.swing.JPanel;

import view.panels.CategoryOverviewPanel;
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
		
		String currentScoreBehaviour=this.getService().currentScoreBehaviourName();
		String currentSelectionBehaviour=this.getService().currentQuestionSelectionBehaviour();
		
		int numberOfQuestions=this.getService().getNumberofQuestionForEvaluation();
		
		getOverviewPanel().setSelectList(selectList,currentSelectionBehaviour);
		getOverviewPanel().setScoreList(scoreList,currentScoreBehaviour);
		getOverviewPanel().setAmount(numberOfQuestions);
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
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
