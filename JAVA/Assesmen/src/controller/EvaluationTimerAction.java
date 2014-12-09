package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.panels.EvaluationPanel;

public class EvaluationTimerAction implements ActionListener {
	private EvaluationPanel evaluationPanel;
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		getEvaluationPanel().setTime(getEvaluationPanel().getTime()+1);
	}
	
	public EvaluationPanel getEvaluationPanel() {
		return evaluationPanel;
	}
	
	public void setEvaluationPanel(EvaluationPanel evaluationPanel) {
		this.evaluationPanel = evaluationPanel;
	}
}
