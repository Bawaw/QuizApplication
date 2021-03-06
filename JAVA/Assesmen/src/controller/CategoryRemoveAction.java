package controller;

import java.awt.event.ActionEvent;

import view.ViewException;
import view.panels.CategoryOverviewPanel;
import view.panels.CategoryTableModel;
import domain.Category;
import domain.DomainException;
import domain.FacadeActionManager;

public class CategoryRemoveAction extends AbstractTestAction {
	private static final long serialVersionUID = 1L;
	private CategoryOverviewPanel overviewPanel;

	public CategoryRemoveAction(FacadeActionManager service) {
		super(service, "New");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		CategoryTableModel tablem = getOverviewPanel().getModel();
		Category clickedCategory = (Category) tablem.getCategoryAt(getOverviewPanel().getSelectedRow());
		try {
			getService().removeCategory(clickedCategory);
			getOverviewPanel().setCategories(getService().getCategoryList());
			getOverviewPanel().update();
		} catch (DomainException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ViewException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	private CategoryOverviewPanel getOverviewPanel() {
		return overviewPanel;
	}

	public void setOverviewPanel(CategoryOverviewPanel overviewPanel) {
		this.overviewPanel = overviewPanel;
	}
}
