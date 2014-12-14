package controller;

import java.awt.event.MouseAdapter;

import javax.swing.JFrame;
import javax.swing.JPanel;

import domain.FacadeManager;

public abstract class AbstractTestMouseAdapter extends MouseAdapter {
	private FacadeManager service;
	private JFrame view;
	
	public AbstractTestMouseAdapter(FacadeManager service){
		super();
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
