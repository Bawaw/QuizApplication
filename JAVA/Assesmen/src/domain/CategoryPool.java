package domain;

import java.util.HashSet;
import java.util.LinkedHashSet;

public class CategoryPool {
	HashSet<Category> categoryPool;

	public CategoryPool() {
		categoryPool = new LinkedHashSet<Category>();
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
		if (categoryPool.contains(category)){
			throw new DomainException("Category already exists! Choose another Title!");
		}
			
			this.categoryPool.add(category);
	}

	public void removeCategory(Category category) {
		if (categoryPool.contains(category))
			categoryPool.remove(category);
	}

}
