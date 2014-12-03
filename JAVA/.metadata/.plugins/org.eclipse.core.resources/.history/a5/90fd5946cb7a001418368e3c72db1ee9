package controller;

import java.awt.event.ActionEvent;
import java.util.Arrays;

import view.panels.SettingsOverviewPanel;
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
		System.out.println(this.getDetailPanel().getSelectBeh().getSelectedItem());
		
	}

	
	private SettingsOverviewPanel getDetailPanel() {
		return detailPanel;
	}

	public void setDetailPanel(SettingsOverviewPanel detailPanel) {
		this.detailPanel = detailPanel;
	}
}
