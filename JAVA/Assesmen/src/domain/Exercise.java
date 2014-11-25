package domain;

public class Exercise {
	private int score;
	private Question question;
	private Category category;
	private Feedback feedback;
	
	public Exercise(Question question,Category category,Feedback feedback,int score) throws DomainException {
		this.setQuestion(question);
		this.setScore(score);
		this.setCategory(category);
		this.setFeedback(feedback);
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) throws DomainException {
		if(score<0){
			throw new DomainException("Score cannot be negative");
		}
		this.score = score;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) throws DomainException {
		if(question==null){
			throw new DomainException("question cannot be null");
		}
		this.question = question;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) throws DomainException {
		if(category==null){
			throw new DomainException("category cannot be null");
		}
		this.category = category;
	}

	public Feedback getFeedback() {
		return feedback;
	}

	public void setFeedback(Feedback feedback) throws DomainException {
		if(feedback==null){
			throw new DomainException("feedback cannot be null");
		}
		// TODO Moet dit wel? Wat als we de feedback in category verwijderen. Dan kan die category toch nog vragen hebben met die feedback!
		if(!this.getCategory().hasFeedback(feedback)){
			throw new DomainException("Category does not have that feedback");
		}
		this.feedback = feedback;
	}
	

	//2 Exercises zijn gelijk als hun vragen gelijk zijn
	@Override
	public boolean equals(Object o){
		boolean output=false;
		if(o instanceof Exercise){
			Exercise ex=(Exercise)o;
			output=this.getQuestion().equals(ex.getQuestion());
		}
		return output;
	}

}
