package domain.factory;

import java.lang.reflect.Constructor;

import config.ConfigException;
import config.InitConfigHandler;
import domain.DomainException;
import domain.ExercisePool;
import domain.enums.QuestionSelectionBehaviourType;
import domain.enums.QuestionType;
import domain.strategy.questionSelection.QuestionSelectionBehaviour;

public class QuestionSelectAlgFactory {

	public static QuestionSelectionBehaviour createStandard(ExercisePool exercisePool) throws DomainException{
		InitConfigHandler it=InitConfigHandler.getInstance();
		QuestionSelectionBehaviourType questionSelectionBehaviourType=null;
		QuestionType qt=null;
		try {
			questionSelectionBehaviourType = QuestionSelectionBehaviourType.valueOf(it.getQuestionSelectionBehaviour());
			qt=QuestionType.valueOf(it.getQuestionType());
		} catch (ConfigException e) {
			throw new DomainException(e);
		}
		
		if(questionSelectionBehaviourType==QuestionSelectionBehaviourType.QuestionsByType){
			return QuestionSelectAlgFactory.create(questionSelectionBehaviourType,exercisePool,qt);	
		}
		else{
		return QuestionSelectAlgFactory.create(questionSelectionBehaviourType,exercisePool);
		}
	}
	
	
	
	public static QuestionSelectionBehaviour create(QuestionSelectionBehaviourType questionSelectionBehaviourType,Object...args) throws DomainException {
		QuestionSelectionBehaviour questionSelectionBehaviour = null;

		try {
			
			Class<?>[] arg = new Class[args.length];
			for (int i = 0; i < args.length; i++) {
				arg[i] = args[i].getClass();
			}
			
			Class<?> c = Class.forName(questionSelectionBehaviourType.getFQDN());
			Constructor<?> constructor = c.getConstructor(arg);
			questionSelectionBehaviour = (QuestionSelectionBehaviour) constructor.newInstance(args);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DomainException(e);
		}
		return questionSelectionBehaviour;
	}

}
