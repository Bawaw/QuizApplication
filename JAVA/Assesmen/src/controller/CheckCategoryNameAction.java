package controller;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import view.panels.CategoryDetailPanel;
import domain.FacadeActionManager;

public class CheckCategoryNameAction implements DocumentListener {
	private CategoryDetailPanel categoryDetailPanel;
	private FacadeActionManager facadeActionManager;
	
	public CheckCategoryNameAction(FacadeActionManager service) {
		this.setFacadeActionManager(service);
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		print();

	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		print();

	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		print();

	}

	public CategoryDetailPanel getCategoryDetailPanel() {
		return categoryDetailPanel;
	}

	public void setCategoryDetailPanel(CategoryDetailPanel categoryDetailPanel) {
		this.categoryDetailPanel = categoryDetailPanel;
	}

	public void print(){
		if(getFacadeActionManager().doesCatExist(getCategoryDetailPanel().getCategoryTitle())){
			getCategoryDetailPanel().overrideText();
		}
		else{
			getCategoryDetailPanel().notOverrideText();
		}
	}

	public FacadeActionManager getFacadeActionManager() {
		return facadeActionManager;
	}

	public void setFacadeActionManager(FacadeActionManager facadeActionManager) {
		this.facadeActionManager = facadeActionManager;
	}

	
}
