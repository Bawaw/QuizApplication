package database;

import java.io.File;

public interface DBWriteHandler {
	void write(String path, DBDataHandler dataHandler);
}
