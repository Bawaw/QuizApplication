package controller;

import java.awt.event.ActionEvent;
import java.io.File;

import view.panels.ExcelPanel;
import database.ExcelReader;
import domain.FacadeActionManager;

public class ReadExcelController extends AbstractTestAction {
	private ExcelPanel excelPanel;
	private FacadeActionManager service;
	public ReadExcelController(FacadeActionManager service) {
		super(service);
		setService(service);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		File fileToRead=getExcelPanel().getSelectedFile();
		getService().ExcelReadStrategy(true);
		getService().read(fileToRead.getAbsolutePath());
		getService().ExcelReadStrategy(false);
	}
	
	public FacadeActionManager getService() {
		return service;
	}

	public void setService(FacadeActionManager service) {
		this.service = service;
	}

	public ExcelPanel getExcelPanel() {
		return excelPanel;
	}

	public void setExcelPanel(ExcelPanel excelPanel) {
		this.excelPanel = excelPanel;
	}

	
	
}
