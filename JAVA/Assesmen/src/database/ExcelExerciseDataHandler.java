package database;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import domain.Answer;
import domain.Category;
import domain.DomainException;
import domain.Exercise;
import domain.FacadeActionManager;
import domain.Feedback;
import domain.Question;
import domain.enums.QuestionType;

public class ExcelExerciseDataHandler implements DBDataHandler {
	FacadeActionManager facadeActionManager;

	public ExcelExerciseDataHandler(FacadeActionManager facadeActionManager) {
		setFacadeActionManager(facadeActionManager);
	}

	@Override
	public void handleData(ArrayList<Object> dataLists) throws DBException {
		ArrayList<String> dataStringList = new ArrayList<String>();
		for (Object object : dataLists) {
			dataStringList.add((String)object);
		}
		ArrayList<Integer> questionStarts = getNextQuestionStarts(dataStringList);
		for (int i = 0; i < questionStarts.size(); i++) {
			int l = (i + 1 < questionStarts.size()) ? questionStarts.get(i + 1)
					: dataStringList.size();
			handleSingleExercise(dataStringList.subList(questionStarts.get(i), l));
		}
	}

	public void handleSingleExercise(List<String> list) throws DBException {
		// excel and own implementation inconsistency
		String t = (list.get(0).equals("MeerkeuzeVraag") ? "Multiple Choice Question"
				: "Yes Or No Question");
		QuestionType qt = QuestionType.getQuestionTypeByDescription(t);
		String questionString = list.get(1);
		try {
			Answer correctAnswer = new Answer(list.get(2));
			Question question;

			if (qt == QuestionType.MultipleChoiceQuestions) {
				HashSet<Answer> answers = new HashSet<Answer>();
				for (int i = 4; i < 7; i++) {
					if (!list.get(i).equals("")) {
						Answer a = new Answer(list.get(i));
						answers.add(a);
						getFacadeActionManager().addAnswer(a);
					}
				}
				answers.add(correctAnswer);
				question = getFacadeActionManager().createQuestion(qt,
						questionString, correctAnswer, answers, 30);
			} else {
				// excel inconsistency
				if (correctAnswer.equals("Neen"))
					correctAnswer = new Answer("No");
				else
					correctAnswer = new Answer("Yes");
				question = getFacadeActionManager().createQuestion(qt,
						questionString, correctAnswer, 30);
			}
			// addQuestion
			for (int i = 8; i < list.size(); i += 7) {
				Category category = new Category(list.get(i));

				String feedS = list.get(i + 2);
				Feedback categoryFeedback = new Feedback(feedS);
				getFacadeActionManager().addFeedback(feedS);

				category.addFeedback(categoryFeedback);
				getFacadeActionManager().addCategory(category);

				int punten = Integer.parseInt(list.get(i + 1));
				Exercise e = new Exercise(question, category, categoryFeedback,
						punten);
				getFacadeActionManager().addExercise(e);
			}
		} catch (DomainException e) {
			throw new DBException();
		}
	}

	public ArrayList<Integer> getNextQuestionStarts(ArrayList<String> dataLists) {
		ArrayList<Integer> is = new ArrayList<Integer>();
		for (int i = 0; i < dataLists.size(); i += 7)
			if (!dataLists.get(i).equals(""))
				is.add(i);
		return is;
	}

	public FacadeActionManager getFacadeActionManager() {
		return facadeActionManager;
	}

	public void setFacadeActionManager(FacadeActionManager facadeActionManager) {
		this.facadeActionManager = facadeActionManager;
	}

	
	//for write Implementation
	@Override
	public ArrayList<Object> getData(String data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getDataTypes() {
		// TODO Auto-generated method stub
		return null;
	}
}
