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
				String newFeedback = getCategoryDetailPanel().getNewFeedbackFieldText();
				getService().addFeedback(newFeedback);
				getCategoryDetailPanel().clearfeedbackFieldText();
				updateFeedbackList();
			} catch (DomainException e1) {
				e1.printStackTrace();
			}
		}
		else{
			try {
				
					String oldFeedback = getCategoryDetailPanel().getSelectedValue();
					
					int confirm = JOptionPane.showConfirmDialog (super.getView(), "This will unlink/remove feeback from ALL categories and exercises!","Warning",JOptionPane.YES_NO_OPTION);
					if(confirm == JOptionPane.YES_OPTION)
						{
					getService().removeFeedback(oldFeedback);
					updateFeedbackList();
						}
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(super.getView(),"Select a feedback to remove!","Error",JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private void updateFeedbackList(){
		getCategoryDetailPanel().setFeedbacks(new ArrayList(getService().getFeedbackPool().getAllStandardFeedbacks()));
	}
	
	public CategoryDetailPanel getCategoryDetailPanel() {
		return categoryDetailPanel;
	}

	public void setCategoryDetailPanel(CategoryDetailPanel categoryFeedbackPanel) {
		this.categoryDetailPanel = categoryFeedbackPanel;
	}

}
