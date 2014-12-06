package controller;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import view.panels.SettingsOverviewPanel;
import config.ConfigException;
import domain.FacadeActionManager;

public class SettingsSaveAction  extends AbstractTestAction{
	private static final long serialVersionUID = 1L;
	private SettingsOverviewPanel detailPanel;
	
	
	public SettingsSaveAction(FacadeActionManager service) {
		super(service, "Save");
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		//Info uit panel lezen en opslaan in model!!!
		String selectionBehaviour = this.getDetailPanel().getSelectionBehaviour();
		String scoreBehaviour =this.getDetailPanel().getScoreBehaviour();
		String evalType=this.getDetailPanel().getEvalType();
		int number = this.getDetailPanel().getNumberOfQuestions();
		try {
			super.getService().saveSelectionBehaviour(selectionBehaviour);
			super.getService().saveScoreBehaviour(scoreBehaviour);
			super.getService().saveNumberofQuestions(number);
			super.getService().saveEvaluationType(evalType);
			JOptionPane.showMessageDialog(super.getView(),"Settings saved successfully!");
		} catch (ConfigException e) {
			JOptionPane.showMessageDialog(super.getView(),"Couldn't save setings!","Error",JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		
	}

	
	private SettingsOverviewPanel getDetailPanel() {
		return detailPanel;
	}

	public void setOverviewPanel(SettingsOverviewPanel detailPanel) {
		this.detailPanel = detailPanel;
	}
}
