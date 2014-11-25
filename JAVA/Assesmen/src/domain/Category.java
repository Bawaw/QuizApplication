package domain;
import java.util.ArrayList;

public class Category {
	private String name;
	private String description;
	private ArrayList<Feedback> feedbacks;
	
	
	
	public Category(String name,String description,ArrayList<Feedback> feedbacks) throws DomainException{
		this.setFeedbacks(feedbacks);
		this.setDescription(description);
		this.setName(name);
	}

	
	

	public String getName() {
		return name;
	}



	private void setName(String name) throws DomainException {
		if(name==null || name.equals("")){
			throw new DomainException("Invalid name");
		}
		this.name = name;
	}

	


	public String getDescription() {
		return description;
	}




	public void setDescription(String description) throws DomainException {
		if(description==null || description.equals("")){
			throw new DomainException("Invalid description");
		}
		this.description = description;
	}




	public ArrayList<Feedback> getFeedbacks() {
		return feedbacks;
	}



	private void setFeedbacks(ArrayList<Feedback> feedbacks) {
		this.feedbacks = feedbacks;
	}
	
	
	@Override
	public boolean equals(Object o){
		boolean output=false;
		if(o instanceof Category){
			Category category=(Category)o;
			output=this.getName().equals(category.getName());
		}
		return output;
	}
	
	
	public void addFeedback(Feedback feedback){
		if(!this.getFeedbacks().contains(feedback)){
			this.getFeedbacks().add(feedback);
		}
	}

	
}
