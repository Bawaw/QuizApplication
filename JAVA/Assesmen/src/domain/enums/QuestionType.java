package domain.enums;


public enum QuestionType {
	YesNoQuestions("domain.YesNoQuestion"),
	MultipleChoiceQuestions("domain.MultipleChoiceQuestion");
	
	private String FQDN;
	
	private QuestionType(String FQDN){
		this.setFQDN(FQDN);
	}

	public String getFQDN() {
		return FQDN;
	}

	private void setFQDN(String fQDN) {
		FQDN = fQDN;
	}
	
	public static String[] toStringArray(){
		QuestionType[] val= values();
		String[] output=new String[val.length];
		for(int i=0;i<val.length;i++){
			output[i]=val[i].name();
		}
		return output;
	}
	
}

