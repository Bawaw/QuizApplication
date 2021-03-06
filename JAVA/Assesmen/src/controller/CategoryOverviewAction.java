package controller;

import java.awt.event.ActionEvent;
import java.util.List;

import view.ViewException;
import view.panels.CategoryOverviewPanel;
import domain.Category;
import domain.FacadeActionManager;

public class CategoryOverviewAction extends AbstractTestAction {
	private static final long serialVersionUID = 1L;
	private CategoryOverviewPanel overviewPanel;

	public CategoryOverviewAction(FacadeActionManager service){
		super(service, "Categories");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Listener to get all categories from service...
		List<Category> categories = getService().getCategoryList();
		getOverviewPanel().setCategories(categories);
		try {
			getOverviewPanel().update();
		} catch (ViewException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		setPanelAsContentForView(getOverviewPanel());
	}

	private CategoryOverviewPanel getOverviewPanel() {
		return overviewPanel;
	}

	public void setOverviewPanel(CategoryOverviewPanel overviewPanel) {
		this.overviewPanel = overviewPanel;
	}
}
