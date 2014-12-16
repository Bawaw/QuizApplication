package domain.enums;

public enum ScoreBehaviourType {
	MaxScoreCalculator("domain.strategy.score.MaxScoreCalculator"),
	ErrorCorrectionCalculator("domain.strategy.score.ErrorCorrectionCalculator"),
	TimeWeightedScoreCalculator("domain.strategy.score.TimeWeightedScoreCalculator");
	
private String FQDN;
	
	private ScoreBehaviourType(String FQDN){
		this.setFQDN(FQDN);
	}

	public String getFQDN() {
		return FQDN;
	}

	private void setFQDN(String fQDN) {
		FQDN = fQDN;
	}
	
	public static String[] toStringArray(){
		ScoreBehaviourType[] val= values();
		String[] output=new String[val.length];
		for(int i=0;i<val.length;i++){
			output[i]=val[i].name();
		}
		return output;
	}
	
}
