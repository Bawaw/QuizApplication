package domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Winkel implements Subject {
	public static final String FILMT = "F";
	public static final String MUZIEKT = "M";
	public static final String SPELT = "S";
	public static final String[] TYPES = { FILMT, MUZIEKT, SPELT };

	private HashMap<String, Product> producten;
	private Map<Integer, Klant> klanten;
	private List<Observer> observers;
	private ProductFactory productFactory;

	public Winkel() {
		producten = new HashMap<String, Product>();
		productFactory = ProductFactory.getInstance();
		observers = new ArrayList<Observer>();
		klanten = new HashMap<Integer,Klant>();
	}

	public void voegProductToe(String type, String id, String title)
			throws DomainException {
		Product p = productFactory.createProduct();
		voegProductToe(p);
	}
	
	public void borowProduct(int klantId, String productId)throws Exception{
		Klant k = getKlanten().get(klantId);
		Product p = getProduct(productId);
		p.getProductState().borowItem(p);
		p.setBorowDate(new Date());
		p.setSubject(k);
		k.borowItem(p);
		addObserver(k);
	}
	
	public double returnProduct(String productId,ProductState state){
		double fine = 0;
		Product p = getProduct(productId);
		if(state instanceof Removed){
			fine = p.getProductState().removeItem(p);
			getProducten().remove(productId);
		}
		else
			p.getProductState().returnItem(p);
		
		nodifyObserver(new Date());
		return fine;
	}

	public void voegProductToe(Product product) throws DomainException {
		if (product == null)
			throw new DomainException("product should not be null");
		if (getProducten().containsKey(product.getId()))
			throw new DomainException("product already present in database");
		producten.put(product.getId(), product);
		nodifyObserver(product);
	}

	private Product getProduct(String id) {
		return producten.get(id);
	}

	public String getProductTitle(String id) {
		String title = "";
		Product product = getProduct(id);
		if (product != null)
			title = product.getName();
		else
			title = "no product with this id";
		return title;
	}

	public double getProductRentalPrice(String id, int nrDays) {
		double price = 0;
		Product product = getProduct(id);
		if (product != null) {
			price = product.berekenHuurprijs(nrDays);
		}
		return price;
	}

	public Map<Integer, Klant> getKlanten() {
		return klanten;
	}

	public void addKlant(Klant klant) {
		if (!klanten.containsKey(klant.getId()))
			this.klanten.put(klant.getId(), klant);
	}

	public String toString() {
		String winkelitems = "";
		Collection<Product> productenSet = producten.values();
		for (Product product : productenSet) {
			winkelitems += product + ": " + producten.get(product) + "\n";
		}
		return winkelitems;
	}

	public HashMap<String, Product> getProducten() {
		return producten;
	}

	@Override
	public void addObserver(Observer o) {
		if(!observers.contains(o)){
			observers.add(o);
		}
	}

	@Override
	public void removeObserver(Observer o) {
		if(observers.contains(o))
			observers.remove(o);
	}

	@Override
	public void nodifyObserver(Object args) {
		for (Observer o : observers) {
			o.update(args);
		}
	}

}
