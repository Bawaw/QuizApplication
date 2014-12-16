package database;


public interface DBWriteHandler {
	void write(String path, DBDataHandler dataHandler) throws DBException;
}
