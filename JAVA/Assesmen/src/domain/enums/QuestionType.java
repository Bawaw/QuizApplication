package domain.enums;

public enum QuestionType {
	YesNoQuestions("FullQualifiedDomainName"),
	MultipleChoiceQuestions("FullQualifiedDomainName");
	
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

