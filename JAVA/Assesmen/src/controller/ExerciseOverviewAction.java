package controller;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import view.ViewException;
import view.panels.ExerciseOverviewPanel;
import domain.Category;
import domain.DomainException;
import domain.Exercise;
import domain.FacadeActionManager;

public class ExerciseOverviewAction extends AbstractTestAction {
	private static final long serialVersionUID = 1L;
	private ExerciseOverviewPanel exerciseOverviewPanel;
	private int index;
	

	public ExerciseOverviewAction(FacadeActionManager service) {
		super(service, "Exercises");
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// Listener to get all categories from service...
		
		initialiseCategories();
		setExercisesBasedOnSelector();
		update();
		setPanelAsContentForView(getExerciseOverviewPanel());

	}

	public void update(){
		try {
			getExerciseOverviewPanel().update();
		} catch (ViewException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	
	public void setExercisesBasedOnSelector(){
		List<Exercise> exercises =null;
		if(getExerciseOverviewPanel().getSelectedCategoryIndex()==0){
			exercises = new ArrayList<Exercise>(getService().getUniqueExercises());
		}
		else{
			String catName=this.getExerciseOverviewPanel().getSelectedCategory();
			try {
				exercises = new ArrayList<Exercise>(getService().getUniqueExercisesByCategory(catName));
			} catch (DomainException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		getExerciseOverviewPanel().setExercises(exercises);
	}
	
	private void initialiseCategories(){
		getExerciseOverviewPanel().setCategoriesOptions(this.getService().getCategoryList(), getIndex());
	}
	
	public ExerciseOverviewPanel getExerciseOverviewPanel() {
		return exerciseOverviewPanel;
	}

	public void setExerciseOverviewPanel(
			ExerciseOverviewPanel exererciseOverviewPanel) {
		this.exerciseOverviewPanel = exererciseOverviewPanel;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex() {
		this.index=getExerciseOverviewPanel().getSelectedCategoryIndex();
	}
	
	

}
