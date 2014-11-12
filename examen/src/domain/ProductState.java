package domain;

public interface ProductState {
	void borowItem(Product p) throws IllegalStateException;
	void returnItem(Product p) throws IllegalStateException;
	double removeItem(Product p) throws IllegalStateException;
}
