package database;

import java.util.ArrayList;

import domain.FacadeActionManager;

public class ExerciseParser extends DataParser {

	
	public ExerciseParser(FacadeActionManager service) {
		super(service);
	}

	
	
	@Override
	public void parseData(Object dataToParse) {
		ArrayList<ArrayList<String>> data=(ArrayList<ArrayList<String>>) dataToParse;
		FacadeActionManager facade=super.getService();
		//Velden uitlezen en exercises aanmaken (questions aanmaken via factory)
		//vervolgens exercises toevoegen via facade aan model
		
		//ook ingelezen category/answers etc toevoegen aan hun pool via de facade
		
		
	}
	
	

}
