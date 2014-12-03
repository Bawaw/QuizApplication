package domain;

import java.util.HashSet;

public class FeedbackPool {
private HashSet<Feedback> feedbacks;

	public FeedbackPool() {
		feedbacks = new HashSet<Feedback>();
	}
	
	public HashSet<Feedback> getAllStandardFeedbacks(){
		return feedbacks;
	}
	
	public void addFeedback(Feedback feedback){
		if(!feedbacks.contains(feedback))
			feedbacks.add(feedback);
	}
	
	private void setFeedbacks(HashSet<Feedback> feedbacks){
		this.feedbacks = feedbacks;
	}
	
	public void removeFeedback(Feedback feedback){
		getAllStandardFeedbacks().remove(feedback);
	}
}
