package domain;

import java.util.Date;

public abstract class Product {

	private String id;
	private String name;
	private ProductState state;
	private Date borowDate;
	private int price = 25;
	private Klant subject;
	
	public Product(String id, String name)
		throws DomainException
	{
		setId(id);
		setName(name);

	}
	
	

	public Klant getSubject() {
		return subject;
	}



	public void setSubject(Klant subject) {
		this.subject = subject;
	}



	public String getId() {
		return id;
	}

	public ProductState getState() {
		return state;
	}

	public void setState(ProductState state) {
		this.state = state;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	private void setId(String id) 
		throws DomainException
	{
		if(id == null)
			throw new DomainException("provide valid id");
		this.id = id;
	}
	
	public Date getBorowDate() {
		return borowDate;
	}
	
	public void setBorowDate(Date borowDate) {
		this.borowDate = borowDate;
	}

	public String getName() {
		return name;
	}

	private void setName(String name) 
		throws DomainException
	{
		if(name == null)
			throw new DomainException("provide valid name");
		this.name = name;
	}
	
	public void setProductState(ProductState productState){
		this.state = productState;
	}
	
	public ProductState getProductState() {
		return state;
	}


	public abstract String getType();
	public abstract double berekenHuurprijs(int aantalDagen);
	

}
