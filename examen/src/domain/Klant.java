package domain;

import java.util.List;

public class Klant implements Observer {
	private static int idMax; 
	private String naam;
	private int id; 
	private String eMail;
	private String[] berichten;
	private List<Product> borowedItems;
	
	public Klant(String naam, String eMail) {
		idMax++;
		id = idMax;
		berichten = new String[3];
	}

	public static int getIdMax() {
		return idMax;
	}

	public static void setIdMax(int idMax) {
		Klant.idMax = idMax;
	}

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
	
	public void addBericht(String bericht){
		if(berichten[0] != null){
			String temp = berichten[1];
			berichten[1] = berichten[0];
			berichten[2] = temp;
		}
		berichten[0] = bericht;
	}
	
	public void borowItem(Product p)throws Exception{
		if(borowedItems.size() > 4)
			throw new Exception("You can not hire more than 5 items at the same time");
		borowedItems.add(p);
	}

	public void update(Object o) {
		if(o instanceof String)
			addBericht((String)o);
	}
	
	
}
