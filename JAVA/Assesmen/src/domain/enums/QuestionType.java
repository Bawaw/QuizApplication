package domain.enums;

import domain.Question;

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
	
	
	
}
