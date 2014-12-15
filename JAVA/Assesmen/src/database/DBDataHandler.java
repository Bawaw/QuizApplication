package database;

import java.util.ArrayList;

import domain.DomainException;

public interface DBDataHandler {	
	String[] getDataTypes();
	void handleData(ArrayList<Object> dataLists) throws DBException;
	ArrayList<Object> getData(String data);
}