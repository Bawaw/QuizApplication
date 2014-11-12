package domain;

public class Available implements ProductState {

	@Override
	public void borowItem(Product p) throws IllegalStateException {
		p.setProductState(new Borowed());
	}

	@Override
	public void returnItem(Product p) throws IllegalStateException {
		throw new IllegalStateException("Product " + p.getId() +  " is registered as available");
	}

	@Override
	public double removeItem(Product p) throws IllegalStateException {
		p.setProductState(new Removed());
		return 0;
	}

}
