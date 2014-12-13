package database;

import java.util.ArrayList;

import domain.DomainException;

public interface DBDataHandler {	
	void handleData(ArrayList<String> dataLists) throws DBException;
}