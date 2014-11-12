package db;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import domain.DomainException;
import domain.Product;
import domain.Winkel;

public class TXTProductStrategy implements DBStrategy {
	@Override
	public void Read(Winkel w, File file) throws DbException {
		Scanner filescanner = null;
		try {
			filescanner = new Scanner(file);
		} catch (FileNotFoundException e1) {
			throw new DbException("file not available");
		}
		while (filescanner.hasNextLine()) {
			Scanner linescanner = new Scanner(filescanner.nextLine());
			linescanner.useDelimiter("\t");
			String type = linescanner.next();
			String id = linescanner.next();
			String title = linescanner.next();
			try {
				w.voegProductToe(type, id, title);
			} catch (DomainException e) {
				throw new DbException("cannot add product available in file");
			} finally {
				linescanner.close();
			}
		}
		filescanner.close();

	}

	@Override
	public void Write(Winkel w, File file) throws DbException {
		PrintWriter schrijver;
		try {
			schrijver = new PrintWriter(file);
		} catch (FileNotFoundException e) {
			throw new DbException("file not available");
		}
		ArrayList<Product> producten = new ArrayList<Product>(w.getProducten().values());
		for (Product product : producten) {
			schrijver.print(product.getType());
			schrijver.print("\t");
			// id
			schrijver.print(product.getId());
			schrijver.print("\t");
			// title
			schrijver.println(product.getName());
		}
		schrijver.close();
	}
}
