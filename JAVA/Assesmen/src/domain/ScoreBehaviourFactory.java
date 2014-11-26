package domain;

import java.lang.reflect.Constructor;

import domain.enums.ScoreBehaviourType;

public class ScoreBehaviourFactory {

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