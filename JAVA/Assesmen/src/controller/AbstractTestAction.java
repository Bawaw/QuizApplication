package controller;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JPanel;

import domain.FacadeActionManager;

public abstract class AbstractTestAction extends AbstractAction {
	private static final long serialVersionUID = 1L;
	private FacadeActionManager service;
	private JFrame view;
	
	public AbstractTestAction(FacadeActionManager service){
		setService(service);
	}
	
	public AbstractTestAction(FacadeActionManager service, String caption){
		super(caption);
		setService(service);
	}

	protected FacadeActionManager getService() {
		return service;
	}

	private void setService(FacadeActionManager service) {
		this.service = service;
	}

	public JFrame getView() {
		return view;
	}

	public void setView(JFrame view) {
		this.view = view;
	}
	
	public void setPanelAsContentForView(JPanel panel){
		getView().setContentPane(panel);
		getView().getContentPane().revalidate();
		getView().getContentPane().repaint();
		getView().pack();
	}
}
