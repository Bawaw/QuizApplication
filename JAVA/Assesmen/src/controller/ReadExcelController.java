package controller;

import java.awt.event.ActionEvent;
import java.io.File;

import view.panels.ExcelPanel;
import domain.FacadeActionManager;

public class ReadExcelController extends AbstractTestAction {
	private ExcelPanel excelPanel;
	
	public ReadExcelController(FacadeActionManager service) {
		super(service);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		File fileToRead=getExcelPanel().getSelectedFile();
		System.out.println(fileToRead.getAbsolutePath());

	}

	public ExcelPanel getExcelPanel() {
		return excelPanel;
	}

	public void setExcelPanel(ExcelPanel excelPanel) {
		this.excelPanel = excelPanel;
	}

	
	
}
