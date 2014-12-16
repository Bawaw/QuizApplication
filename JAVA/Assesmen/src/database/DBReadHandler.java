package database;


public interface DBReadHandler {
	void read(String path, DBDataHandler dataHandler) throws DBException;
}
