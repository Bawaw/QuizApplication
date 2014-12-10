package domain;

public class Exercise implements Comparable<Exercise> {
	private int score;
	private Question question;
	private Category category;
	private Feedback feedback;
	private int timesPicked;

	public Exercise(Question question, Category category, Feedback feedback,
			int score) throws DomainException {
		this.setQuestion(question);
		this.setScore(score);
		this.setCategory(category);
		this.setFeedback(feedback);
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) throws DomainException {
		if (score <= 0) {
			throw new DomainException("Score cannot be negative");
		}
		this.score = score;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) throws DomainException {
		if (question == null) {
			throw new DomainException("question cannot be null");
		}
		this.question = question;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) throws DomainException {
		if (category == null) {
			throw new DomainException("category cannot be null");
		}
		this.category = category;
	}

	public Feedback getFeedback() {
		return feedback;
	}

	public void setFeedback(Feedback feedback) throws DomainException {
		if (feedback == null) {
			throw new DomainException("feedback cannot be null");
		}
		this.feedback = feedback;
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

	public void increaseTimesPicked() {
		timesPicked++;
	}

	// 2 Exercises zijn gelijk als hun vragen gelijk zijn
	@Override
	public boolean equals(Object o) {
		boolean output = false;
		if (o instanceof Exercise) {
			Exercise ex = (Exercise) o;
			output = this.getQuestion().equals(ex.getQuestion());
		}
		return output;
	}

	@Override
	public int compareTo(Exercise o) {
		return o.getTimesPicked() - this.getTimesPicked();
	}

	
	public boolean uniqueEquals(Object o){
		boolean output = false;
		if (o instanceof Exercise) {
			Exercise ex = (Exercise) o;
			output = this.getQuestion().equals(ex.getQuestion()) && this.getCategory().equals(ex.getCategory());
		}
		return output;
	}
	
	@Override
	public int hashCode(){
		return score;
	}
}
