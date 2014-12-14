package controller;

import java.awt.event.ActionEvent;

import view.panels.ExcelPanel;
import domain.FacadeActionManager;

public class ExcelOverviewController extends AbstractTestAction {
	private static final long serialVersionUID = 1L;
	private ExcelPanel excelPanel;
	
	public ExcelOverviewController(FacadeActionManager service) {
		super(service,"Excel");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		setPanelAsContentForView(getExcelPanel());
	}

	public ExcelPanel getExcelPanel() {
		return excelPanel;
	}

	public void setExcelPanel(ExcelPanel excelPanel) {
		this.excelPanel = excelPanel;
	}

	
	
}
