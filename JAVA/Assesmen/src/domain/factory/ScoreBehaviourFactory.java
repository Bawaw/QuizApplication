package domain.factory;

import java.lang.reflect.Constructor;

import config.ConfigException;
import config.InitConfigHandler;
import domain.DomainException;
import domain.Evaluation;
import domain.enums.ScoreBehaviourType;
import domain.strategy.score.ScoreBehaviour;

public class ScoreBehaviourFactory {

	public static ScoreBehaviour createStandard(Evaluation evaluation) throws DomainException{
		InitConfigHandler it=InitConfigHandler.getInstance();
		ScoreBehaviourType scorebehaviourtype=null;
		try {
			scorebehaviourtype = ScoreBehaviourType.valueOf(it.getScoreBehaviour());
		} catch (ConfigException e) {
			throw new DomainException(e);
		}
		return ScoreBehaviourFactory.create(evaluation, scorebehaviourtype);
	}
	
	
	
	public static ScoreBehaviour create(Evaluation evaluation,
			ScoreBehaviourType scorebehaviourtype) throws DomainException {
		ScoreBehaviour scoreBehaviour = null;

		try {
			Class<?> c = Class.forName(scorebehaviourtype.getFQDN());
			Constructor<?> constructor = c.getConstructor(evaluation.getClass());
			scoreBehaviour = (ScoreBehaviour) constructor
					.newInstance(evaluation);
		} catch (Exception e) {
			throw new DomainException("fACTORY ERROR",e);
		}
		return scoreBehaviour;
	}
}
