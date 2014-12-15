package database;

import java.io.File;
import java.util.ArrayList;

public interface DBReadHandler {
	void read(String path, DBDataHandler dataHandler);
}
