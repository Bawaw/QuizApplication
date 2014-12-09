package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.panels.ExerciseDetailPanel;
import view.panels.SettingsOverviewPanel;

public class SettingsSelectionListener implements ActionListener {
	private SettingsOverviewPanel settingsOverviewPanel;
	
	public SettingsSelectionListener() {

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(getSettingsOverviewPanel().getSelectedSelectionAlgo()!= null && getSettingsOverviewPanel().getSelectedSelectionAlgo().equals("QuestionsByType")){
			getSettingsOverviewPanel().showQuestionType();
		}
		else{
			getSettingsOverviewPanel().hideQuestionType();
		}
	
	}

	public SettingsOverviewPanel getSettingsOverviewPanel() {
		return settingsOverviewPanel;
	}

	public void setSettingsOverviewPanel(SettingsOverviewPanel settingsOverviewPanel) {
		this.settingsOverviewPanel = settingsOverviewPanel;
	}
	
	
}