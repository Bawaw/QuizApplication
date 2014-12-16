package domain;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class EvaluationReport {
	private int score;
	private Map<String,PointCouple> catScore;
	private int scoreOn;

	public EvaluationReport() {
		this.setCatScore(new HashMap<String,PointCouple>());
	}

	public Map<String, PointCouple> getCatScore() {
		return catScore;
	}

	public void setCatScore(Map<String, PointCouple> catScore) {
		this.catScore = catScore;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	public void addExercise(Exercise ex,boolean answeredCorrect){
		String catName=ex.getCategory().getName();
		PointCouple p=null;
		
		if(!this.getCatScore().containsKey(catName)){
			p=new PointCouple();
			getCatScore().put(catName, p);
		}
		else{
			p=this.getCatScore().get(catName);
		}
		
		
		if(answeredCorrect){
			p.correctAnswer();
		}
		else{
			p.wrongAnswer();
		}
	}
	
	
	
	
	
	public int getScoreOn() {
		return scoreOn;
	}

	public void setScoreOn(int scoreOn) {
		this.scoreOn = scoreOn;
	}

	@Override
	public String toString(){
		String out= "you scored " + this.getScore()+"/"+this.getScoreOn()+"\n";
		Iterator<Entry<String, PointCouple>> it=this.getCatScore().entrySet().iterator();
		while(it.hasNext()){
			Map.Entry<String, PointCouple> res=it.next();
			out+=res.getKey() + " : " + res.getValue() +"\n";
		}
		return out;
	}
	

}
