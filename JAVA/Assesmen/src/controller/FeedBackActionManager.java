package controller;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import view.panels.CategoryDetailPanel;
import domain.DomainException;
import domain.FacadeActionManager;

public class FeedBackActionManager extends AbstractTestAction {

	private CategoryDetailPanel categoryDetailPanel;

	public FeedBackActionManager(FacadeActionManager service) {
		super(service);
		// TODO Auto-generated constructor stub
	}

	public FeedBackActionManager(FacadeActionManager service, String caption) {
		super(service, caption);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("AddFeedback")) {
			try {
				String newFeedbak = getCategoryDetailPanel()
						.getNewFeedbackFieldText();
				getService().addFeedback(newFeedbak);
				getCategoryDetailPanel().setFeedbacks(
						new ArrayList(getService().getFeedbackPool()
								.getAllStandardFeedbacks()));
				getCategoryDetailPanel().clearfeedbackFieldText();
			} catch (DomainException e1) {
				e1.printStackTrace();
			}
		}
		else{
			try {
				String oldFeedbak = getCategoryDetailPanel()
						.getSelectedValue();
				getService().removeFeedback(oldFeedbak);
				getCategoryDetailPanel().setFeedbacks(
						new ArrayList(getService().getFeedbackPool()
								.getAllStandardFeedbacks()));
				
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(super.getView(),"Select feedback to remove!","Error",JOptionPane.ERROR_MESSAGE);
				//e1.printStackTrace();
			}
		}
	}

	public CategoryDetailPanel getCategoryDetailPanel() {
		return categoryDetailPanel;
	}

	public void setCategoryDetailPanel(CategoryDetailPanel categoryFeedbackPanel) {
		this.categoryDetailPanel = categoryFeedbackPanel;
	}

}
