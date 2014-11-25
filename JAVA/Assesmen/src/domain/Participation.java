package domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;

public class Participation {
	private Date date;
	private int score;
	private ArrayList<Entry<String, Integer>> errors;

	public Participation(int score, ArrayList<Entry<String, Integer>> errors)
			throws DomainException {
		// sets date to current date
		this.setDate(new Date());
		this.setScore(score);
		this.setErrors(errors);
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

	public ArrayList<Entry<String, Integer>> getErrors() {
		return errors;
	}

	private void setErrors(ArrayList<Entry<String, Integer>> errors) {
		this.errors = errors;
	}

}
