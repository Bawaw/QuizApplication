package domain;

public class Answer {
	private String answer;
	private int timesPicked;

	public Answer(String answer) throws DomainException {
		this.setAnswer(answer);
		this.setTimesPicked(0);
	}

	public String getAnswer() {
		return answer;
	}

	private void setAnswer(String answer) throws DomainException {
		if (answer == null || answer.equals("")) {
			throw new DomainException("Invalid Answer");
		}
		this.answer = answer;
	}

	public int getTimesPicked() {
		return timesPicked;
	}

	private void setTimesPicked(int timesPicked) throws DomainException {
		if (timesPicked < 0) {
			throw new DomainException("timesPicked has to be positive");
		}
		this.timesPicked = timesPicked;
	}

	public void increaseTimesPicked() throws DomainException {
		this.setTimesPicked(this.getTimesPicked() + 1);
	}

	@Override
	public boolean equals(Object o) {
		boolean output = false;
		if (o instanceof Answer) {
			Answer a = (Answer) o;
			output = this.getAnswer().equals(a.getAnswer());
		}
		return output;
	}

	@Override
	public int hashCode() {
		return this.getAnswer().hashCode();
	}

}
