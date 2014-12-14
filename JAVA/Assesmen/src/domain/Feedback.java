package domain;

import com.sun.xml.internal.bind.v2.runtime.RuntimeUtil.ToStringAdapter;

public class Feedback {
	private String text;
	public static final String STANDARD_FEEDBACK = "No feedback";
	
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
	
	public String toString(){
		return getText();
	}
	
	@Override
	public boolean equals(Object o){
		boolean output=false;
		if(o instanceof Feedback){
			Feedback f = (Feedback) o;
			output=this.getText().equals(f.getText());
		}
		return output;
	}
	
	public int hashCode(){
		return this.getText().hashCode();
	}
	
	
}
