package domain;

import java.util.ArrayList;

public interface DBDataHandler {	
	void handleData(ArrayList<String> dataLists) throws DomainException;
}