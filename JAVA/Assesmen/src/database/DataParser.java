package database;

import domain.FacadeActionManager;

public abstract class DataParser {
	private FacadeActionManager service;
	
	public DataParser(FacadeActionManager service) {
		this.setService(service);
	}

	public FacadeActionManager getService() {
		return service;
	}

	public void setService(FacadeActionManager service) {
		this.service = service;
	}

	public abstract void parseData(Object dataToParse);
	
	

}
