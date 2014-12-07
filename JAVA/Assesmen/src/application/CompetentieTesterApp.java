package application;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import view.MainViewAdmin;
import view.MainViewUser;
import view.ViewException;
import view.panels.CategoryDetailPanel;
import view.panels.CategoryOverviewPanel;
import view.panels.EvaluationPanel;
import view.panels.ParticipationPanel;
import view.panels.SettingsOverviewPanel;
import controller.AbstractTestAction;
import controller.CategoryDoneAction;
import controller.CategoryEditAction;
import controller.CategoryNewAction;
import controller.CategoryOverviewAction;
import controller.CategoryRemoveAction;
import controller.CheckCategoryNameAction;
import controller.EvaluationController;
import controller.FeedBackActionManager;
import controller.JsliderAmountAction;
import controller.ParticipationAction;
import controller.SettingsOverviewAction;
import controller.SettingsSaveAction;
import domain.FacadeActionManager;

public class CompetentieTesterApp {
	public static void main(String[] args) throws ViewException{
		FacadeActionManager service = FacadeActionManager.getInstance();
		
		//###########################################ADMINSIDE##############################################
		
		CategoryOverviewAction categoryOverviewAction = new CategoryOverviewAction(service);
		CategoryEditAction categoryEditAction = new CategoryEditAction(service);
		CategoryNewAction categoryNewAction = new CategoryNewAction(service);
		CategoryDoneAction categoryDoneAction = new CategoryDoneAction(service);
		FeedBackActionManager feedbackActionManager = new FeedBackActionManager(service);
		CategoryRemoveAction categoryRemoveAction = new CategoryRemoveAction(service);
		SettingsOverviewAction settingsOverviewAction = new SettingsOverviewAction(service);
		SettingsSaveAction settingsSaveAction = new SettingsSaveAction(service);
		JsliderAmountAction jsliderAmountAction = new JsliderAmountAction();
		CheckCategoryNameAction checkCategoryNameAction = new CheckCategoryNameAction(service);
		
		CategoryOverviewPanel categoryOverviewPanel = new CategoryOverviewPanel(categoryEditAction, categoryNewAction,categoryRemoveAction);
		CategoryDetailPanel categoryDetailPanel = new CategoryDetailPanel(categoryDoneAction,feedbackActionManager,checkCategoryNameAction);
		SettingsOverviewPanel settingsOverviewPanel = new SettingsOverviewPanel(settingsSaveAction,jsliderAmountAction);
		
		
		
		categoryOverviewAction.setOverviewPanel(categoryOverviewPanel);
		categoryEditAction.setDetailPanel(categoryDetailPanel);
		categoryNewAction.setDetailPanel(categoryDetailPanel);
		categoryDoneAction.setDetailPanel(categoryDetailPanel);
		categoryDoneAction.setOverviewPanel(categoryOverviewPanel);
		settingsSaveAction.setOverviewPanel(settingsOverviewPanel);
		jsliderAmountAction.setSettingsOverviewPanel(settingsOverviewPanel);
		feedbackActionManager.setCategoryDetailPanel(categoryDetailPanel);
		checkCategoryNameAction.setCategoryDetailPanel(categoryDetailPanel);
		categoryRemoveAction.setOverviewPanel(categoryOverviewPanel);
		
		settingsOverviewAction.setOverviewPanel(settingsOverviewPanel);
		

		List<AbstractTestAction> actions = new ArrayList<AbstractTestAction>();
		actions.add(categoryOverviewAction);
		actions.add(settingsOverviewAction);

		MainViewAdmin admin = new MainViewAdmin(actions);
		MainViewUser user = new MainViewUser();
	
		
		categoryOverviewAction.setView(admin);
		categoryEditAction.setView(admin);
		categoryNewAction.setView(admin);
		categoryDoneAction.setView(admin);
		categoryDoneAction.setView(admin);
		settingsOverviewAction.setView(admin);
		settingsSaveAction.setView(admin);
		feedbackActionManager.setView(admin);
		categoryRemoveAction.setView(admin);
		admin.setVisible(false);
		//################################################################################################
		

		//#####################################USERSIDE#####################################################
		ParticipationAction participationAction=new ParticipationAction(service);
		EvaluationController evaluationController=new EvaluationController(service);

		
		
		ParticipationPanel participationPanel=new ParticipationPanel(evaluationController);
		EvaluationPanel evaluationPanel=new EvaluationPanel(evaluationController);
		
		
	
		evaluationController.setParticipationAction(participationAction);
		
		
		evaluationController.setEvaluationPanel(evaluationPanel);
		participationAction.setEvaluationPanel(participationPanel);
		
		

		participationAction.setView(user);
		evaluationController.setView(user);
		
		//load participation overview
		participationAction.actionPerformed(null);
		
		user.setVisible(false);
		//################################################################################################
		
		String[] buttons = { "ADMIN", "USER"};    
	
		 int option =JOptionPane.showOptionDialog(null,"Choose!","Who are you?",JOptionPane.DEFAULT_OPTION,
				    JOptionPane.QUESTION_MESSAGE,null,buttons,buttons[0]);
		
		 switch(option){
		 case 0:
			 admin.setVisible(true);
			 break;
		 case 1:
			 user.setVisible(true);
			 break;
		 default:
			System.exit(0);
		 }
	}
}
