package application;

import java.util.ArrayList;
import java.util.List;

import view.MainView;
import view.ViewException;
import view.panels.CategoryDetailPanel;
import view.panels.CategoryOverviewPanel;
import view.panels.ParticipationPanel;
import view.panels.SettingsOverviewPanel;
import controller.AbstractTestAction;
import controller.CategoryDoneAction;
import controller.CategoryEditAction;
import controller.CategoryNewAction;
import controller.CategoryOverviewAction;
import controller.CategoryRemoveAction;
import controller.CheckCategoryNameAction;
import controller.ParticipationAction;
import controller.FeedBackActionManager;
import controller.JsliderAmountAction;
import controller.SettingsOverviewAction;
import controller.SettingsSaveAction;
import domain.FacadeActionManager;

public class CompetentieTesterApp {
	public static void main(String[] args) throws ViewException{
		FacadeActionManager service = FacadeActionManager.getInstance();
		
		
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
		ParticipationAction evaluationAction=new ParticipationAction(service);

		CategoryOverviewPanel categoryOverviewPanel = new CategoryOverviewPanel(categoryEditAction, categoryNewAction,categoryRemoveAction);
		CategoryDetailPanel categoryDetailPanel = new CategoryDetailPanel(categoryDoneAction,feedbackActionManager,checkCategoryNameAction);
		SettingsOverviewPanel settingsOverviewPanel = new SettingsOverviewPanel(settingsSaveAction,jsliderAmountAction);
		ParticipationPanel evaluationPanel=new ParticipationPanel();
		
		evaluationAction.setEvaluationPanel(evaluationPanel);
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
		actions.add(evaluationAction);

		MainView mainView = new MainView(actions);
	
		evaluationAction.setView(mainView);
		
		categoryOverviewAction.setView(mainView);
		categoryEditAction.setView(mainView);
		categoryNewAction.setView(mainView);
		categoryDoneAction.setView(mainView);
		categoryDoneAction.setView(mainView);
		settingsOverviewAction.setView(mainView);
		settingsSaveAction.setView(mainView);
		feedbackActionManager.setView(mainView);
		categoryRemoveAction.setView(mainView);
		mainView.setVisible(true);
		
	}
}
