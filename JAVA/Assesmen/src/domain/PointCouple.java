package domain;

import java.io.Serializable;

public class PointCouple implements Serializable {
	private static final long serialVersionUID = 1L;
	private int totalQ;
	private int correctQ;
	
	public PointCouple() {
		
	}
	
	public void correctAnswer(){
		this.setCorrectQ(this.getCorrectQ()+1);
		this.setTotalQ(this.getTotalQ()+1);
	}
	
	public void wrongAnswer(){
		this.setTotalQ(this.getTotalQ()+1);
	}

	public int getTotalQ() {
		return totalQ;
	}

	public void setTotalQ(int totalQ) {
		this.totalQ = totalQ;
	}

	public int getCorrectQ() {
		return correctQ;
	}

	public void setCorrectQ(int correctQ) {
		this.correctQ = correctQ;
	}
	
	
	@Override
	public String toString(){
		return this.getCorrectQ() + " out of " +this.getTotalQ() +" correct";
	}
	

}
