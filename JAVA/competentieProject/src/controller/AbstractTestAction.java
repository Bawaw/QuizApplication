package controller;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JPanel;

import domain.FacadeManager;

public abstract class AbstractTestAction extends AbstractAction {
	private static final long serialVersionUID = 1L;
	private FacadeManager service;
	private JFrame view;
	
	public AbstractTestAction(FacadeManager service){
		setService(service);
	}
	
	public AbstractTestAction(FacadeManager service, String caption){
		super(caption);
		setService(service);
	}

	protected FacadeManager getService() {
		return service;
	}

	private void setService(FacadeManager service) {
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
