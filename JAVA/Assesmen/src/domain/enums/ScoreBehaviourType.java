package domain.enums;

public enum ScoreBehaviourType {
	MaxScoreCalculator("domain.MaxScoreCalculator"),
	ErrorCorrectionCalculator("domain.ErrorCorrectionCalculator"),
	TimeWeightedScoreCalculator("FullQualifiedDomainName");
	
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
