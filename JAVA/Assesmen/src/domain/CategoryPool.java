package domain;

import java.util.ArrayList;
import java.util.HashSet;

public class CategoryPool {
	HashSet<Category> categoryPool;

	public CategoryPool(HashSet<Category> categoryPool) {
		setCategoryPool(categoryPool);
	}
	

	public HashSet<Category> getCategoryPool() {
		return categoryPool;
	}
	
	public void editCategory(Category oldCategory,String name, String description) throws DomainException{
		oldCategory.setDescription(description);
		oldCategory.setName(name);
	}
	
	public Category getCategory(String naam){
		for(Category c : getCategoryPool()){
			if(c.getName().equals(naam))
				return c;
		}
		return null;
	}

	private void setCategoryPool(HashSet<Category> categoryPool) {
		this.categoryPool = categoryPool;
	}

	public void AddCategory(Category category){
		if (!categoryPool.contains(category))
			this.categoryPool.add(category);
	}

	public void removeCategory(Category category){
		if (categoryPool.contains(category))
			categoryPool.remove(category);
	}

}
