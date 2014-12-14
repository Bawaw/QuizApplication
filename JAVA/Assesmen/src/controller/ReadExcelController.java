package controller;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JOptionPane;

import view.panels.ExcelPanel;
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
		try{
		File fileToRead=getExcelPanel().getSelectedFile();
		getService().readFromExcel(fileToRead.getAbsoluteFile());
		JOptionPane.showMessageDialog(super.getView(),"Reading done.");
		}
		catch(Exception ex){
			ex.printStackTrace();
			JOptionPane.showMessageDialog(super.getView(),"Select a valid file!","Error",JOptionPane.ERROR_MESSAGE);
		}
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
