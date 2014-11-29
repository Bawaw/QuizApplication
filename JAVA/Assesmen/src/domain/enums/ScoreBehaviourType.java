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
	
}
