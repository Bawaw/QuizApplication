package controller;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import view.panels.CategoryDetailPanel;
import domain.Category;
import domain.FacadeActionManager;
import domain.Feedback;

public class CategoryNewAction extends AbstractTestAction {
	private static final long serialVersionUID = 1L;
	private CategoryDetailPanel detailPanel;

	public CategoryNewAction(FacadeActionManager service){
		super(service, "New");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		getDetailPanel().setCategory(new Category());
		
		List<Category> categories = getService().getCategoryList();
		getDetailPanel().setCategories(categories);
		getDetailPanel().setFeedbacks(new ArrayList<Feedback>(getService().getFeedbackPool().getAllStandardFeedbacks()));
		getDetailPanel().setNewScreen();
		setPanelAsContentForView(getDetailPanel());		
	}

	private CategoryDetailPanel getDetailPanel() {
		return detailPanel;
	}

	public void setDetailPanel(CategoryDetailPanel detailPanel) {
		this.detailPanel = detailPanel;
	}
}
