package database;

import java.util.ArrayList;

import domain.FacadeActionManager;

public class QuestionParser extends DataParser {

	
	public QuestionParser(FacadeActionManager service) {
		super(service);
	}

	
	
	@Override
	public void parseData(Object dataToParse) {
		ArrayList<ArrayList<String>> data=(ArrayList<ArrayList<String>>) dataToParse;
		//Velden uitlezen en exercises aanmaken (questions aanmaken via factory)
		//vervolgens exercises toevoegen via facade aan model
		
		super.getService();
	}
	
	

}
