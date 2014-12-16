package database;

import java.util.ArrayList;

public interface DBDataHandler {	
	String[] getDataTypes();
	void handleData(ArrayList<Object> dataLists) throws DBException;
	ArrayList<Object> getData(String data);
}