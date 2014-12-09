package domain.enums;

import domain.MultipleChoiceQuestion;
import domain.YesNoQuestion;


public enum QuestionType {
	YesNoQuestions("domain.YesNoQuestion","Yes Or No Question"),
	MultipleChoiceQuestions("domain.MultipleChoiceQuestion","Multiple Choice Question");
	
	private String FQDN;
	private String uiString;

	
	private QuestionType(String FQDN,String uiString){
		this.setFQDN(FQDN);
		this.setUiString(uiString);
	}

	public String getUiString() {
		return uiString;
	}


	public void setUiString(String uiString) {
		this.uiString = uiString;
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
	
	public static String[] toStringArrayUI(){
		QuestionType[] val= values();
		String[] output=new String[val.length];
		for(int i=0;i<val.length;i++){
			output[i]=val[i].getUiString();
		}
		return output;
	}
	
	
	
}

