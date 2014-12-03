package controller;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JOptionPane;

import view.ViewException;
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
		//probleem: Wanneer we category editten -> geeft hij terug dat die category al bestaat met die naam. (logisch)
		//probleem2: Wanneer we een categroy die al bestaat de naam aanpassen, komt deze 2 maal in lijst te staan (no idea)
		
		
		if(e.getActionCommand().equals("Save")){
			try{
				getService().addCategory(getDetailPanel().getCreatedCategory());
				List<Category> categories = getService().getCategoryList();
				getOverviewPanel().setCategories(categories);
				getOverviewPanel().update();
				setPanelAsContentForView(getOverviewPanel());	
				System.out.println(this.getService().getCategoryPool().getCategoryPool().size());
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
