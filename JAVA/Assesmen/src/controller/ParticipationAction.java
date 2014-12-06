package controller;

import java.awt.event.ActionEvent;
import java.util.List;

import view.ViewException;
import view.panels.ParticipationPanel;
import domain.Category;
import domain.FacadeActionManager;
import domain.Participation;

public class ParticipationAction extends AbstractTestAction {
	private static final long serialVersionUID = 1L;
	private ParticipationPanel EvaluationPanel;

	public ParticipationAction(FacadeActionManager service) {
		super(service,"voorlopigHier");
		// TODO Auto-generated constructor stub
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		List<Participation> participations = getService().getParticipationList();
		getEvaluationPanel().setParticipations(participations);
		try {
			getEvaluationPanel().update();
		} catch (ViewException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		setPanelAsContentForView(getEvaluationPanel());

	}


	public ParticipationPanel getEvaluationPanel() {
		return EvaluationPanel;
	}


	public void setEvaluationPanel(ParticipationPanel evaluationPanel) {
		EvaluationPanel = evaluationPanel;
	}
	
	

}
