package domain.factory;

import java.lang.reflect.Constructor;

import domain.DomainException;
import domain.Evaluation;
import domain.enums.ScoreBehaviourType;
import domain.strategy.score.ScoreBehaviour;

public class ScoreBehaviourFactory {

	public static ScoreBehaviour createStandard(Evaluation evaluation) throws DomainException{
		ScoreBehaviourType scorebehaviourtype=null;
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