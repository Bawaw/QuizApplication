package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import view.panels.EvaluationPanel;
import domain.FacadeActionManager;

public class EvaluationTimerAction implements ActionListener {
	private EvaluationPanel evaluationPanel;
	private EvaluationController evaluationController;
	private FacadeActionManager service;
	private JFrame view;
	
	public EvaluationTimerAction(FacadeActionManager service) {
		this.setService(service);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		this.getService().decreaseRemainingTime();
		int remTime=this.getService().getRemainingTime();
		getEvaluationPanel().setTime(this.getService().getRemainingTime());
		
		if(remTime==0){
			JOptionPane.showMessageDialog(this.getView(),"Time's up!","Warning!",JOptionPane.WARNING_MESSAGE);
			this.getEvaluationController().evaluationDone();
		}
		
	}
	
	public EvaluationPanel getEvaluationPanel() {
		return evaluationPanel;
	}
	
	public void setEvaluationPanel(EvaluationPanel evaluationPanel) {
		this.evaluationPanel = evaluationPanel;
	}

	public EvaluationController getEvaluationController() {
		return evaluationController;
	}

	public void setEvaluationController(EvaluationController evaluationController) {
		this.evaluationController = evaluationController;
	}

	public FacadeActionManager getService() {
		return service;
	}

	public void setService(FacadeActionManager service) {
		this.service = service;
	}

	public JFrame getView() {
		return view;
	}

	public void setView(JFrame view) {
		this.view = view;
	}
	
	
	
	
	
}
