package database;

import java.util.ArrayList;

import domain.Answer;
import domain.Category;
import domain.DomainException;
import domain.Exercise;
import domain.FacadeActionManager;
import domain.Feedback;
import domain.Participation;

public class SerializableExerciseDataHandler implements DBDataHandler {
	public FacadeActionManager facadeActionManager;

	public SerializableExerciseDataHandler(
			FacadeActionManager facadeActionManager) {
		setFacadeActionManager(facadeActionManager);
	}

	private enum DataTypes {
		FEEDBACKS, PARTICIPATIONS, ANSWERS, CATEGORIES, EXERCISES;

		public static String[] toStringArray() {
			DataTypes[] val = values();
			String[] output = new String[val.length];
			for (int i = 0; i < val.length; i++) {
				output[i] = val[i].name();
			}
			return output;
		}
	}

	public String[] getDataTypes() {
		return DataTypes.toStringArray();
	}

	@Override
	public void handleData(ArrayList<Object> dataLists) throws DBException {
		if (dataLists != null && dataLists.size() > 0) {
			if (dataLists.get(0) instanceof Feedback) {
				for (Object object : dataLists)
					getFacadeActionManager().getFeedbackPool().addFeedback(
							(Feedback) object);
			}

			else if (dataLists.get(0) instanceof Participation) {
				for (Object object : dataLists)
					getFacadeActionManager().getParticipations()
							.addParticipation((Participation) object);
			}

			else if (dataLists.get(0) instanceof Answer) {
				for (Object object : dataLists)
					getFacadeActionManager().getAnswerPool().AddAnswer(
							(Answer) object);
			}

			else if (dataLists.get(0) instanceof Category) {
				for (Object object : dataLists)
					try {
						getFacadeActionManager().getCategoryPool().AddCategory(
								(Category) object);
					} catch (DomainException e) {
						throw new DBException(e);
					}
			} else if (dataLists.get(0) instanceof Exercise) {
				for (Object object : dataLists)
					try {
						getFacadeActionManager().getExercisePool().addExercise(
								(Exercise) object);
					} catch (DomainException e) {
						throw new DBException(e);
					}
			}
		}

	}

	@Override
	public ArrayList<Object> getData(String data) {
		ArrayList<Object> retList = null;
		switch (DataTypes.valueOf(data)) {
		case FEEDBACKS:
			retList = new ArrayList<Object>(getFacadeActionManager().getFeedbackPool().getAllStandardFeedbacks());
			break;
		case PARTICIPATIONS:
			retList = new ArrayList<Object>(getFacadeActionManager().getParticipations().getParticipationPool());
			break;
		case CATEGORIES:
			retList = new ArrayList<Object>(getFacadeActionManager().getCategoryPool().getCategoryPool());
			break;
		case EXERCISES:
			retList = new ArrayList<Object>(getFacadeActionManager().getExercisePool().getExercisePool());
			break;
		case ANSWERS:
			retList = new ArrayList<Object>(getFacadeActionManager().getAnswerPool().getAnswers());
			break;
		}
		return retList;
	}

	public FacadeActionManager getFacadeActionManager() {
		return facadeActionManager;
	}

	public void setFacadeActionManager(FacadeActionManager facadeActionManager) {
		this.facadeActionManager = facadeActionManager;
	}

}
