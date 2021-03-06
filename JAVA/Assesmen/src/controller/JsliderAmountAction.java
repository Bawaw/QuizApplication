package controller;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import view.panels.SettingsOverviewPanel;

public class JsliderAmountAction implements ChangeListener {
	private SettingsOverviewPanel settingsOverviewPanel;
	
	public JsliderAmountAction() {

	}

	@Override
	public void stateChanged(ChangeEvent arg0) {
		JSlider j=(JSlider)arg0.getSource();
		this.getSettingsOverviewPanel().setAmountField(j.getValue());
	}

	public SettingsOverviewPanel getSettingsOverviewPanel() {
		return settingsOverviewPanel;
	}

	public void setSettingsOverviewPanel(SettingsOverviewPanel settingsOverviewPanel) {
		this.settingsOverviewPanel = settingsOverviewPanel;
	}

	
}
