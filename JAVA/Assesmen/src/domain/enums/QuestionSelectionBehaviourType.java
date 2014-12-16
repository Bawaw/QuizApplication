package domain.enums;

public enum QuestionSelectionBehaviourType {
	LeastAskedQuestions("domain.strategy.questionSelection.LeastAskedQuestions"),
	RandomQuestions("domain.strategy.questionSelection.RandomQuestions"),
	QuestionsByType("domain.strategy.questionSelection.QuestionsByType");
	
	private String FQDN;
	
	private QuestionSelectionBehaviourType(String FQDN){
		this.setFQDN(FQDN);
	}

	public String getFQDN() {
		return FQDN;
	}

	private void setFQDN(String fQDN) {
		FQDN = fQDN;
	}
	
	public static String[] toStringArray(){
		QuestionSelectionBehaviourType[] val= values();
		String[] output=new String[val.length];
		for(int i=0;i<val.length;i++){
			output[i]=val[i].name();
		}
		return output;
	}
}
