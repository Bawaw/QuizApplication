package domain.factory;

import java.lang.reflect.Constructor;

import domain.DomainException;
import domain.ExercisePool;
import domain.enums.QuestionSelectionBehaviourType;
import domain.strategy.questionSelection.QuestionSelectionBehaviour;

public class QuestionSelectAlgFactory {

	public static QuestionSelectionBehaviour createStandard(ExercisePool exercisePool) throws DomainException{
		QuestionSelectionBehaviourType questionSelectionBehaviourType=null;
		return QuestionSelectAlgFactory.create(exercisePool, questionSelectionBehaviourType);
	}
	
	
	
	public static QuestionSelectionBehaviour create(ExercisePool exercisePool,
			QuestionSelectionBehaviourType questionSelectionBehaviourType) throws DomainException {
		QuestionSelectionBehaviour questionSelectionBehaviour = null;

		try {
			Class<?> c = Class.forName(questionSelectionBehaviourType.getFQDN());
			Constructor<?> constructor = c.getConstructor(exercisePool.getClass());
			questionSelectionBehaviour = (QuestionSelectionBehaviour) constructor.newInstance(exercisePool);
		} catch (Exception e) {
			throw new DomainException("fACTORY ERROR",e);
		}
		return questionSelectionBehaviour;
	}

}