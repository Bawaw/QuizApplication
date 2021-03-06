package controller;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;

import view.panels.CategoryDetailPanel;
import view.panels.CategoryTableModel;
import domain.Category;
import domain.FacadeActionManager;
import domain.Feedback;

public class CategoryEditAction extends AbstractTestMouseAdapter {
	private CategoryDetailPanel detailPanel;
	
	public CategoryEditAction(FacadeActionManager service){
		super(service);
	}
	
	@Override
	public void mouseClicked(MouseEvent evt) {
		if (evt.getClickCount() == 2 && !evt.isConsumed()) {
			JTable table = (JTable) (evt.getSource());
			CategoryTableModel tablem = (CategoryTableModel) (table.getModel());
			Category clickedCategory = (Category) tablem.getCategoryAt(table
					.getSelectedRow());
			getDetailPanel().setCategory(clickedCategory);

			List<Category> categories = getService().getCategoryList();
			getDetailPanel().setCategories(categories);
			getDetailPanel().setFeedbacks(
					new ArrayList<Feedback>(getService().getFeedbackPool()
							.getAllStandardFeedbacks()));
			getDetailPanel().setEdit();
			setPanelAsContentForView(getDetailPanel());
			evt.consume();
		}
	}

	private CategoryDetailPanel getDetailPanel() {
		return detailPanel;
	}

	public void setDetailPanel(CategoryDetailPanel detailPanel) {
		this.detailPanel = detailPanel;
	}

}