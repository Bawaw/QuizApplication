package controller;

import java.awt.event.ActionEvent;
import java.util.List;

import view.panels.CategoryDetailPanel;
import domain.Category;
import domain.FacadeManager;

public class CategoryNewAction extends AbstractTestAction {
	private static final long serialVersionUID = 1L;
	private CategoryDetailPanel detailPanel;

	public CategoryNewAction(FacadeManager service){
		super(service, "New");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		getDetailPanel().setCategory(new Category());
		
		List<Category> categories = getService().getAllCategories();
		getDetailPanel().setCategories(categories);
		setPanelAsContentForView(getDetailPanel());		
	}

	private CategoryDetailPanel getDetailPanel() {
		return detailPanel;
	}

	public void setDetailPanel(CategoryDetailPanel detailPanel) {
		this.detailPanel = detailPanel;
	}
}
