package controller;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import view.panels.ExerciseDetailPanel;
import domain.Answer;
import domain.FacadeActionManager;
import domain.enums.QuestionType;

public class ExerciseNewAction extends AbstractTestAction {
	private ExerciseDetailPanel exerciseDetailPanel;
	
	
	public ExerciseNewAction(FacadeActionManager service) {
		super(service);
		// TODO Auto-generated constructor stub
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("open new window");
		//START WITH UNLOCKING GUI AND CLEARING ALL FIELDS THAT NEED TO BE CLEARED!
		
		String[] qt=this.getService().getAllQuestionTypes();
		
		getExerciseDetailPanel().setNew();
		
		getExerciseDetailPanel().setTypeList(this.getService().getAllQuestionTypes(), qt[0]);
		getExerciseDetailPanel().setCategorieList(this.getService().getCategoryList());
		getExerciseDetailPanel().setOptionSelector(new ArrayList<Answer>(getService().getAnswerPool().getAnswers()));
		

		getExerciseDetailPanel().update();
		setPanelAsContentForView(getExerciseDetailPanel());

		
	}


	public ExerciseDetailPanel getExerciseDetailPanel() {
		return exerciseDetailPanel;
	}


	public void setExerciseDetailPanel(ExerciseDetailPanel exerciseDetailPanel) {
		this.exerciseDetailPanel = exerciseDetailPanel;
	}

	
}
