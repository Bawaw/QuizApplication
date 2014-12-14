package domain;

import java.util.HashSet;

public abstract class Question {
	private String question;
	private HashSet<Answer> options;
	private Answer rightAnswer;
	private int time;

	public Question(String question, Answer rightAnswer,
			HashSet<Answer> option, int time) throws DomainException {
		this.setQuestion(question);
		this.setOptions(option);
		this.setRightAnswer(rightAnswer);
		this.setTime(time);
	}

	// TODO antwoordopties mogen niet dubbel voorkomen, dus mss voor een set
	// opteren? een set laat ook geen null toe (DONE)

	public String getQuestion() {
		return question;
	}

	private void setQuestion(String question) throws DomainException {
		if (question == null || question.equals("")) {
			throw new DomainException("Invalid question!");
		}
		this.question = question;
	}

	public HashSet<Answer> getOptions() {
		return options;
	}

	private void setOptions(HashSet<Answer> options) throws DomainException {
		if (options == null) {
			throw new DomainException("Options are null!");
		}
		if (options.size() > 5 || options.size() < 2) {
			throw new DomainException("Options have to be between 2-5");
		}
		this.options = options;
	}

	public abstract String getType();
	
	public Answer getRightAnswer() {
		return rightAnswer;
	}

	private void setRightAnswer(Answer rightAnswer) throws DomainException {
		if (rightAnswer == null) {
			throw new DomainException("Answer is null...");
		}
		if (!getOptions().contains(rightAnswer)) {
			throw new DomainException(
					"Include the right answer in the options!");
		}

		this.rightAnswer = rightAnswer;
	}

	public int getTime() {
		return time;
	}

	private void setTime(int time) throws DomainException {
		if (time <= 0) {
			throw new DomainException("time has to be positive!");
		}
		this.time = time;
	}

	// 2 vragen zijn gelijk als de vraagstelling gelijk is
	@Override
	public boolean equals(Object o) {
		boolean equals = false;
		if (o instanceof Question) {
			Question q = (Question) o;
			equals = q.getQuestion().equals(this.getQuestion());
		}
		return equals;
	}

	@Override
	public int hashCode() {
		return this.getQuestion().hashCode();
	}

}
