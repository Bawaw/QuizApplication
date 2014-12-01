package domain;


public class Category {
	private String title;
	private String description;
	private Category mainCategory;
	
	public Category() {
	}
	
	public Category(String title) {
		setTitle(title);
	}
	
	public Category(String title, String Description) {
		this(title);
		setDescription(Description);
	}

	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Category getMainCategorie() {
		return mainCategory;
	}
	
	public void setMainCategorie(Category mainCategory) {
		this.mainCategory = mainCategory;
	}
	
	public String toString(){
		return getTitle();
	}
}