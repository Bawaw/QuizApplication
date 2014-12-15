package controller;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import domain.FacadeActionManager;

public class MainWindowCloseListener extends WindowAdapter{
	FacadeActionManager service;
	public MainWindowCloseListener(FacadeActionManager service) {
		setService(service);
	}
	
	@Override
	public void windowClosing(WindowEvent e){
		getService().write();
	}
	
	public FacadeActionManager getService() {
		return service;
	}
	public void setService(FacadeActionManager service) {
		this.service = service;
	}
	
	

}
