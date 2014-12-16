package domain.factory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import domain.DomainException;
import domain.Question;
import domain.enums.QuestionType;

public class QuestionFactory {

	public QuestionFactory() {
		// TODO Auto-generated constructor stub
	}

	// Variabel aantal params, nodig omdat YesNo niet zelfde constructor heeft
	// als MultipleChoice.
	// Gebruik altijd non-primitive types in QuestionConstructor!!!
	public static Question create(QuestionType type, Object... args)
			throws DomainException {
		Question q = null;
		try {
			Class<?>[] arg = new Class[args.length];
			for (int i = 0; i < args.length; i++) {
				arg[i] = args[i].getClass();
			}

			Class<?> c = Class.forName(type.getFQDN());
			Constructor<?> constr = c.getConstructor(arg);
			q = (Question) constr.newInstance(args);

		} catch (InstantiationException e) {
			e.printStackTrace();
			throw new DomainException(e);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			throw new DomainException(e);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new DomainException(e);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			throw new DomainException(e);
		} catch (SecurityException e) {
			e.printStackTrace();
			throw new DomainException(e);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			throw new DomainException(e);
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			throw new DomainException(e);
		}
		return q;
	}

}
