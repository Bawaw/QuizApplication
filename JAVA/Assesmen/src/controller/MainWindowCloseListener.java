package controller;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import database.DBException;
import domain.FacadeActionManager;

public class MainWindowCloseListener extends WindowAdapter{
	private FacadeActionManager service;
	private JFrame view;
	
	public MainWindowCloseListener(FacadeActionManager service) {
		setService(service);
	}
	
	@Override
	public void windowClosing(WindowEvent e){
		try {
			getService().write();
		} catch (DBException e1) {
			JOptionPane.showMessageDialog(getView(),e1.toString());
		}
	}
	
	public void windowOpened(WindowEvent e){
		try {
			getService().read();
		} catch (DBException e1) {
			JOptionPane.showMessageDialog(getView(),e1.toString());
		}
	}
	
	public JFrame getView() {
		return view;
	}

	public void setView(JFrame view) {
		this.view = view;
	}
	
	public FacadeActionManager getService() {
		return service;
	}
	public void setService(FacadeActionManager service) {
		this.service = service;
	}
	
	

}
