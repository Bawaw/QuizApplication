package domain;

public class Borowed implements ProductState {

	@Override
	public void borowItem(Product p) throws IllegalStateException {
		throw new IllegalStateException("The item "+ p.getId() + " is already borowed by ");
	}

	@Override
	public void returnItem(Product p) throws IllegalStateException {
		p.setProductState(new Available());
	}

	@Override
	public double removeItem(Product p) throws IllegalStateException {
		p.setProductState(new Removed());
		return p.getPrice()/3;
	}

}
