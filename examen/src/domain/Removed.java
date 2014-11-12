package domain;

public class Removed implements ProductState {

	@Override
	public void borowItem(Product p) throws IllegalStateException {
		throw new IllegalStateException("This item is unavailable");
	}

	@Override
	public void returnItem(Product p) throws IllegalStateException {
		throw new IllegalStateException("This item is unavailable");
	}

	@Override
	public double removeItem(Product p) throws IllegalStateException {
		throw new IllegalStateException("The item " + p.getId() + " is already in the unavailable category");
	}

}
