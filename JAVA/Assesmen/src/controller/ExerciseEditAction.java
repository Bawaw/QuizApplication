package controller;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JTable;

import view.MainViewAdmin;
import view.panels.ExerciseDetailPanel;
import view.panels.ExerciseTableModel;
import domain.Answer;
import domain.Exercise;
import domain.FacadeActionManager;

public class ExerciseEditAction extends AbstractTestMouseAdapter {
	private ExerciseDetailPanel exerciseDetailPanel;
	
	public ExerciseEditAction(FacadeActionManager service) {
		super(service);
		// TODO Auto-generated constructor stub
	}
	
	public void mouseClicked(MouseEvent evt) {
		if (evt.getClickCount() == 2 && !evt.isConsumed()) {
			try{
			JTable table = (JTable) (evt.getSource());
			ExerciseTableModel tablem = (ExerciseTableModel) (table.getModel());
			
			Exercise clickedExercise = (Exercise) tablem.getExerciseAt(table.getSelectedRow());

			ArrayList<Exercise> similiarExercises = this.getService().getExercisesByQuestion(clickedExercise);
			getExerciseDetailPanel().setTypeList(this.getService().getAllQuestionTypes(), clickedExercise.getQuestion().getType());
			
			getExerciseDetailPanel().setCategorieList(this.getService().getCategoryList());
			getExerciseDetailPanel().setCommonExercises(similiarExercises);
			getExerciseDetailPanel().setDefaultCategory();
			getExerciseDetailPanel().setOptionSelector(new ArrayList<Answer>(getService().getAnswerPool().getAnswers()));

			
			
			getExerciseDetailPanel().setEdit();
			
			setPanelAsContentForView(getExerciseDetailPanel());
			this.getView().pack();
			((MainViewAdmin)this.getView()).center();
			evt.consume();
			}
			catch(Exception ex){
				ex.printStackTrace();
			}
		}
	}
	public ExerciseDetailPanel getExerciseDetailPanel() {
		return exerciseDetailPanel;
	}
	public void setExerciseDetailPanel(ExerciseDetailPanel exerciseDetailPanel) {
		this.exerciseDetailPanel = exerciseDetailPanel;
	}
	
	
}
