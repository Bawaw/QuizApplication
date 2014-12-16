package database;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class SerializableReader implements DBReadHandler {

	public SerializableReader() {

	}

	@Override
	public void read(String path, DBDataHandler dataHandler) {
		try {
			for (String string : dataHandler.getDataTypes()) {
				String newPath = newFilePath(path, string);
				FileInputStream fileIn = new FileInputStream(newPath);
				ObjectInputStream in = new ObjectInputStream(fileIn);
				System.out.println(string + " read from " + path);
				ArrayList<Object> list = new ArrayList<Object>();
				Object obj;
				try {
					while ((obj = in.readObject()) != null) {
						list.add(obj);
					}
				} catch (Exception e) {
				}
				dataHandler.handleData(list);
				in.close();
				fileIn.close();
			}

		} catch (IOException i) {
			i.printStackTrace();
		} catch (DBException e) {
			e.printStackTrace();
		}
	}

	public String newFilePath(String path, String string) {
		int pathIndex = (path.lastIndexOf("\\") != -1) ? path.lastIndexOf("\\")
				: 0;
		String s = path.substring(0, pathIndex) + string
				+ path.substring(path.lastIndexOf("."), path.length());
		return s;
	}
}
