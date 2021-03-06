package domain;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;

public class CategoryPool {
	HashSet<Category> categoryPool;

	public CategoryPool() {
		setCategoryPool(new LinkedHashSet<Category>());
	}

	public HashSet<Category> getCategoryPool() {
		return categoryPool;
	}

	public void editCategory(Category oldCategory, String name,
			String description) throws DomainException {
		oldCategory.setDescription(description);
		oldCategory.setName(name);
	}

	public Category getCategory(String naam) {
		for (Category c : getCategoryPool()) {
			if (c.getName().equals(naam))
				return c;
		}
		return null;
	}

	private void setCategoryPool(HashSet<Category> categoryPool) {
		this.categoryPool = categoryPool;
	}

	public void AddCategory(Category category) throws DomainException {
		if (!categoryPool.contains(category)){
			this.categoryPool.add(category);
		}
		else{
			updateExistingCategory(category);
		}
			
			
	}
	public void removeFeedbackFromAllCat(Feedback f){
		for(Category c : getCategoryPool()){
			c.removeFeedback(f);
		}
			
	}
	
	
	public boolean catNameAlreadyInPool(String name){
		Iterator<Category> it=this.getCategoryPool().iterator();
		while(it.hasNext()){
			Category c=it.next();
			if(c.getName().equals(name)){
				return true;
			}
		}
		return false;
	}
	
	private void updateExistingCategory(Category cat) throws DomainException{
		Iterator<Category> it=this.getCategoryPool().iterator();
		while(it.hasNext()){
			Category c=it.next();
			if(c.equals(cat)){
				c.setDescription(cat.getDescription());
				c.setFeedbacks(cat.getFeedbacks());
			}
		}
	}

	public void removeCategory(Category category) {
		if (categoryPool.contains(category))
			categoryPool.remove(category);
	}

}
