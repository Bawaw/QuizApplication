package controller;

import java.awt.event.MouseAdapter;

import javax.swing.JFrame;
import javax.swing.JPanel;

import domain.FacadeActionManager;

public abstract class AbstractTestMouseAdapter extends MouseAdapter {
	private FacadeActionManager service;
	private JFrame view;
	
	public AbstractTestMouseAdapter(FacadeActionManager service){
		super();
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
