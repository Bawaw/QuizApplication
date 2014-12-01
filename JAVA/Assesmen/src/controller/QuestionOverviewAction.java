package controller;

import java.awt.event.ActionEvent;

import view.panels.CategoryOverviewPanel;
import domain.FacadeActionManager;

public class QuestionOverviewAction extends AbstractTestAction {
	//Hier komt het paneel dat ingeladen moet worden wanneer men op dit menuItem klikt!!!
		private CategoryOverviewPanel overviewPanel;
		
		
		public QuestionOverviewAction(FacadeActionManager service) {
			super(service, "Questions");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

		}
		
		
		private CategoryOverviewPanel getOverviewPanel() {
			return overviewPanel;
		}

		public void setOverviewPanel(CategoryOverviewPanel overviewPanel) {
			this.overviewPanel = overviewPanel;
		}

	}
