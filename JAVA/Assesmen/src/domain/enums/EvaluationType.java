package domain.enums;

public enum EvaluationType {
	FeedbackEvaluation("domain.FeedbackEvaluation"),
	ScoreEvaluation("domain.ScoreEvaluation");
	
	
	private String FQDN;
	
	private EvaluationType(String FQDN){
		this.setFQDN(FQDN);
	}

	public String getFQDN() {
		return FQDN;
	}

	private void setFQDN(String fQDN) {
		FQDN = fQDN;
	}
	
	public static String[] toStringArray(){
		EvaluationType[] val= values();
		String[] output=new String[val.length];
		for(int i=0;i<val.length;i++){
			output[i]=val[i].name();
		}
		return output;
	}
}
