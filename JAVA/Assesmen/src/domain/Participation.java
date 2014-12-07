package domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class Participation {
	private Date date;
	private int score;
	private Map<String, PointCouple> errors;

	public Participation(int score, Map<String, PointCouple> map)
			throws DomainException {
		// sets date to current date
		this.setDate(new Date());
		this.setScore(score);
		this.setErrors(map);
	}

	public Date getDate() {
		return date;
	}

	private void setDate(Date date) {
		this.date = date;
	}

	public int getScore() {
		return score;
	}

	private void setScore(int score) throws DomainException {
		if (score < 0) {
			throw new DomainException("Score can not be negative");
		}
		this.score = score;
	}

	public Map<String, PointCouple> getErrors() {
		return errors;
	}
	
	public String getErrorsFormatted(){
		String output="<html>";
		Iterator<Entry<String, PointCouple>> it=this.getErrors().entrySet().iterator();
		while(it.hasNext()){
			Entry<String, PointCouple> entry=it.next();
			output+=entry.getKey() +" : "+entry.getValue().toString()+"<br>";
		}
		output+="</html>";
		return output;
	}

	private void setErrors(Map<String, PointCouple> errors) {
		this.errors = errors;
	}
	
	@Override
	public boolean equals(Object o){
		boolean output=false;
		if(o instanceof Participation){
			Participation p=(Participation)o;
			output=this.getDate().equals(p.getDate()) && this.getScore()==p.getScore() && this.getErrors().equals(p.getErrors());
		}
		return output;
	}

}
