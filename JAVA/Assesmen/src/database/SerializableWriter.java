package database;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SerializableWriter implements DBWriteHandler {
	public SerializableWriter() {

	}

	@Override
	public void write(String path, DBDataHandler dataHandler) {
		try {
			for (String string : dataHandler.getDataTypes()) {
				String newPath = newFilePath(path, string);
				FileOutputStream fileOut = new FileOutputStream(newPath);
				ObjectOutputStream out = new ObjectOutputStream(fileOut);
				System.out.println(string + " written to " + path);
				for (Object o : dataHandler.getData(string)) {
					out.writeObject(o);
				}
				out.close();
				fileOut.close();
			}
		} catch (IOException i) {
			i.printStackTrace();
		}

	}

	public String newFilePath(String path, String string) {
		int pathIndex = (path.lastIndexOf("\\") != -1 )? path.lastIndexOf("\\"):0;
		String s = path.substring(0,pathIndex) + string + path.substring(path.lastIndexOf("."),path.length());
		return s;
	}
}
