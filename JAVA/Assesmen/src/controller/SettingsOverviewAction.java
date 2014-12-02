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
	public void actionPerformed(ActionEvent arg0) {
		String[] selectList= {"1","2"};
		String[] scoreList={"3","4"};
		
		getOverviewPanel().setSelectList(selectList);
		getOverviewPanel().setScoreList(scoreList);
		getOverviewPanel().setAmount(2);

		setPanelAsContentForView(getOverviewPanel());

	}

	
	private SettingsOverviewPanel getOverviewPanel() {
		return overviewPanel;
	}

	public void setOverviewPanel(SettingsOverviewPanel overviewPanel) {
		this.overviewPanel = overviewPanel;
	}
}
