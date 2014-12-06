package domain.factory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Map.Entry;

import config.ConfigException;
import config.InitConfigHandler;
import domain.Answer;
import domain.DomainException;
import domain.Evaluation;
import domain.Exercise;
import domain.enums.EvaluationType;
import domain.enums.ScoreBehaviourType;

public class EvaluationFactory {
	
	public static Evaluation create(ArrayList<Entry<Exercise, Answer>> exercises,ScoreBehaviourType scoreBehaviourType) throws DomainException {
		InitConfigHandler it=InitConfigHandler.getInstance();
		EvaluationType evaluationType=null;
		try{
			evaluationType=EvaluationType.valueOf(it.getEvaluationType());
		}
		catch(ConfigException e){
			throw new DomainException(e);
		}
		return create(evaluationType,exercises,scoreBehaviourType);
	}
	
	public static Evaluation create(EvaluationType evaluationType,ArrayList<Entry<Exercise, Answer>> exercises,ScoreBehaviourType scoreBehaviourType) throws DomainException {
		Evaluation evaluation=null;
		
			Class<?> c;
			try {
				c = Class.forName(evaluationType.getFQDN());
				Constructor<?> constructor = c.getConstructor(exercises.getClass(), scoreBehaviourType.getClass());
				evaluation = (Evaluation) constructor.newInstance(exercises,scoreBehaviourType);
			} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				throw new DomainException(e);
			}
			
		return evaluation;		
	}

}
