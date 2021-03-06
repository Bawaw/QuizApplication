package controller;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JOptionPane;

import view.panels.CategoryDetailPanel;
import view.panels.CategoryOverviewPanel;
import domain.Category;
import domain.FacadeActionManager;

public class CategoryDoneAction extends AbstractTestAction {
	private static final long serialVersionUID = 1L;
	private CategoryDetailPanel detailPanel;
	private CategoryOverviewPanel overviewPanel;

	public CategoryDoneAction(FacadeActionManager service){
		super(service);
	}

	public CategoryDoneAction(FacadeActionManager service, String caption){
		super(service, caption);
	}

	@Override
	public void actionPerformed(ActionEvent e){
		
		if(e.getActionCommand().equals("Save")){
			try{
				Category c=getDetailPanel().getCreatedCategory();
				if(!getDetailPanel().isEditScreen()){
					getService().addCategory(c);
				}
				
				List<Category> categories = getService().getCategoryList();
				getOverviewPanel().setCategories(categories);
				getOverviewPanel().update();
				setPanelAsContentForView(getOverviewPanel());	
				
			}
			catch (Exception ex){
				JOptionPane.showMessageDialog(super.getView(),ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			}
		}
		else{
			setPanelAsContentForView(getOverviewPanel());		
		}
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
