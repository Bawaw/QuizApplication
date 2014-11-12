package domain;

public class ProductFactory {
	private static ProductFactory instance;
	
	private ProductFactory() {
		instance = this;
	}
	
	public static synchronized ProductFactory getInstance(){
		if(instance == null)
			new ProductFactory();
		return instance;
	}
	
	public Product createProduct(){
		return null;
	}
}
