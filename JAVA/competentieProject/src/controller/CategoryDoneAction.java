package controller;

import java.awt.event.ActionEvent;
import java.util.List;

import view.panels.CategoryDetailPanel;
import view.panels.CategoryOverviewPanel;
import domain.Category;
import domain.FacadeManager;

public class CategoryDoneAction extends AbstractTestAction {
	private static final long serialVersionUID = 1L;
	private CategoryDetailPanel detailPanel;
	private CategoryOverviewPanel overviewPanel;

	public CategoryDoneAction(FacadeManager service){
		super(service);
	}

	public CategoryDoneAction(FacadeManager service, String caption){
		super(service, caption);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Save")){
			getService().addCategory(getDetailPanel().getCreatedCategory());
		}
		
		List<Category> categories = getService().getAllCategories();
		getOverviewPanel().setCategories(categories);
		setPanelAsContentForView(getOverviewPanel());		
	}

	private CategoryOverviewPanel getOverviewPanel() {
		return overviewPanel;
	}

	public void setOverviewPanel(CategoryOverviewPanel overviewPanel) {
		this.overviewPanel = overviewPanel;
	}

	private CategoryDetailPanel getDetailPanel() {
		return detailPanel;
	}

	public void setDetailPanel(CategoryDetailPanel detailPanel) {
		this.detailPanel = detailPanel;
	}
}
