package application;

import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import view.MainViewAdmin;
import view.MainViewUser;
import view.ViewException;
import view.panels.CategoryDetailPanel;
import view.panels.CategoryOverviewPanel;
import view.panels.EvaluationPanel;
import view.panels.ExcelPanel;
import view.panels.ExerciseDetailPanel;
import view.panels.ExerciseOverviewPanel;
import view.panels.ParticipationPanel;
import view.panels.SettingsOverviewPanel;
import controller.AbstractTestAction;
import controller.AddExercise;
import controller.AnswerActionManager;
import controller.CategoryDoneAction;
import controller.CategoryEditAction;
import controller.CategoryNewAction;
import controller.CategoryOverviewAction;
import controller.CategoryRemoveAction;
import controller.CategorySelectionListener;
import controller.CheckCategoryNameAction;
import controller.EvaluationController;
import controller.EvaluationTimerAction;
import controller.ExcelOverviewController;
import controller.ExerciseCategoryRemoveAction;
import controller.ExerciseDoneAction;
import controller.ExerciseEditAction;
import controller.ExerciseNewAction;
import controller.ExerciseOverviewAction;
import controller.ExerciseRemoveAction;
import controller.ExerciseTypeListener;
import controller.FeedBackActionManager;
import controller.JsliderAmountAction;
import controller.MainWindowCloseListener;
import controller.ParticipationAction;
import controller.ReadExcelController;
import controller.SettingsOverviewAction;
import controller.SettingsSaveAction;
import controller.SettingsSelectionListener;
import database.ExcelReader;
import domain.FacadeActionManager;

public class CompetentieTesterApp {
	public static void main(String[] args) throws ViewException{
		FacadeActionManager service = new FacadeActionManager();
		
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
		ExerciseOverviewAction exerciseOverviewAction=new ExerciseOverviewAction(service);
		ExerciseEditAction exerciseEditAction=new ExerciseEditAction(service);
		ExerciseTypeListener exerciseTypeListener=new ExerciseTypeListener();
		SettingsSelectionListener settingsSelectionListener=new SettingsSelectionListener();
		ExerciseRemoveAction exerciseRemoveAction=new ExerciseRemoveAction(service);
		AnswerActionManager answerActionManager=new AnswerActionManager(service);
		CategorySelectionListener categorySelectionListener = new CategorySelectionListener(service);
		ExerciseCategoryRemoveAction exerciseCategoryRemoveAction = new ExerciseCategoryRemoveAction(service);
		AddExercise addExercise=new AddExercise(service);
		ExerciseDoneAction exerciseDoneAction=new ExerciseDoneAction(service);
		ExerciseNewAction exerciseNewAction=new ExerciseNewAction(service);
		ExcelOverviewController excelController=new ExcelOverviewController(service);
		ReadExcelController readExcelController=new ReadExcelController(service);
		MainWindowCloseListener mainWindowCloseListener = new MainWindowCloseListener(service);
		
		
		CategoryOverviewPanel categoryOverviewPanel = new CategoryOverviewPanel(categoryEditAction, categoryNewAction,categoryRemoveAction);
		CategoryDetailPanel categoryDetailPanel = new CategoryDetailPanel(categoryDoneAction,feedbackActionManager,checkCategoryNameAction);
		ExerciseDetailPanel exerciseDetailPanel=new ExerciseDetailPanel(exerciseCategoryRemoveAction,categorySelectionListener,addExercise,exerciseDoneAction,exerciseTypeListener,answerActionManager);
		SettingsOverviewPanel settingsOverviewPanel = new SettingsOverviewPanel(settingsSaveAction,jsliderAmountAction,settingsSelectionListener);
		ExerciseOverviewPanel exererciseOverviewPanel=new ExerciseOverviewPanel(exerciseEditAction,exerciseNewAction,exerciseRemoveAction);
		ExcelPanel excelPanel=new ExcelPanel(readExcelController);
		
		
		categorySelectionListener.setExerciseDetailPanel(exerciseDetailPanel);
		answerActionManager.setExerciseDetailPanel(exerciseDetailPanel);
		settingsSelectionListener.setSettingsOverviewPanel(settingsOverviewPanel);
		exerciseTypeListener.setExerciseDetailpanel(exerciseDetailPanel);
		exerciseEditAction.setExerciseDetailPanel(exerciseDetailPanel);
		exerciseOverviewAction.setExerciseOverviewPanel(exererciseOverviewPanel);
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
		exerciseRemoveAction.setExerciseOverviewPanel(exererciseOverviewPanel);
		exerciseCategoryRemoveAction.setExerciseDetailPanel(exerciseDetailPanel);
		addExercise.setExerciseDetailPanel(exerciseDetailPanel);
		exerciseDoneAction.setExercisedetailPanel(exerciseDetailPanel);
		exerciseDoneAction.setExerciseOverviewPanel(exererciseOverviewPanel);
		exerciseDoneAction.setExerOverviewAction(exerciseOverviewAction);
		exerciseNewAction.setExerciseDetailPanel(exerciseDetailPanel);
		settingsOverviewAction.setOverviewPanel(settingsOverviewPanel);
		excelController.setExcelPanel(excelPanel);
		readExcelController.setExcelPanel(excelPanel);
		

		List<AbstractTestAction> actions = new ArrayList<AbstractTestAction>();
		actions.add(categoryOverviewAction);
		actions.add(exerciseOverviewAction);
		actions.add(excelController);
		actions.add(settingsOverviewAction);

		MainViewAdmin admin = new MainViewAdmin(actions,mainWindowCloseListener);
		MainViewUser user = new MainViewUser(mainWindowCloseListener);
	
		readExcelController.setView(admin);
		exerciseNewAction.setView(admin);
		exerciseDoneAction.setView(admin);
		addExercise.setView(admin);
		exerciseTypeListener.setView(admin);
		exerciseOverviewAction.setView(admin);
		exerciseEditAction.setView(admin);
		excelController.setView(admin);
		categoryOverviewAction.setView(admin);
		categoryEditAction.setView(admin);
		categoryNewAction.setView(admin);
		categoryDoneAction.setView(admin);
		categoryDoneAction.setView(admin);
		settingsOverviewAction.setView(admin);
		settingsSaveAction.setView(admin);
		feedbackActionManager.setView(admin);
		categoryRemoveAction.setView(admin);
		exerciseRemoveAction.setView(admin);
		
		admin.setVisible(false);
		//################################################################################################
		

		//#####################################USERSIDE#####################################################
		ParticipationAction participationAction=new ParticipationAction(service);
		EvaluationTimerAction evaluationTimerAction = new EvaluationTimerAction(service);
		EvaluationController evaluationController=new EvaluationController(service);
		
		
		
		ParticipationPanel participationPanel=new ParticipationPanel(evaluationController);
		EvaluationPanel evaluationPanel=new EvaluationPanel(evaluationController,evaluationTimerAction);
		
		
		
		evaluationController.setParticipationAction(participationAction);
		evaluationTimerAction.setEvaluationPanel(evaluationPanel);
		evaluationTimerAction.setEvaluationController(evaluationController);
		
		evaluationController.setEvaluationPanel(evaluationPanel);
		participationAction.setEvaluationPanel(participationPanel);
		evaluationTimerAction.setEvaluationPanel(evaluationPanel);
		
		evaluationTimerAction.setView(user);
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
