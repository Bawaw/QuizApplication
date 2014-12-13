package domain;

import java.io.File;
import java.util.ArrayList;

public interface DBHandler {
	void read(File path, DBDataHandler dataHandler);
}
