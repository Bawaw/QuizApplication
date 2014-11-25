package domain;

public class Feedback {
	private String text;
	
	public Feedback(String text)throws DomainException{
		this.setText(text);
	}

	public String getText() {
		return text;
	}

	private void setText(String text) throws DomainException{
		if(text==null || text.equals("")){
			throw new DomainException("Invalid feedbacktext");
		}
		this.text = text;
	}
	
	
}
