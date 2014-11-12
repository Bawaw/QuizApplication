package db;

import java.io.File;
import java.util.List;

import domain.Winkel;

public abstract class WinkelDatabaseHandler {

	private File productenBestand;
	private List<Winkel> winkels;
	private DBStrategy strategy;

	public WinkelDatabaseHandler(String productenFilename, Winkel winkel, DBStrategy dbStrategy)
			throws DbException {
		setProductenBestand(productenFilename);
		addWinkel(winkel);
		setStrategy(dbStrategy);
	}

	public File getProductFile() {
		return productenBestand;
	}

	private void setProductenBestand(String filename) throws DbException {
		try {
			this.productenBestand = new File(filename);
		} catch (NullPointerException e) {
			throw new DbException("file not valid");
		}
	}

	public List<Winkel> getWinkels() {
		return winkels;
	}

	public void addWinkel(Winkel winkel) {
		if(!winkels.contains(winkel))
			winkels.add(winkel);
	}
	
	public void setStrategy(DBStrategy dbStrategy){
		this.strategy = dbStrategy;
	}
	
	public void readDB(Winkel w) throws DbException{
		strategy.Read(w, getProductFile());
	}
	
	public void writeDB(Winkel w) throws DbException{
		strategy.Write(w, getProductFile());
	}
}
