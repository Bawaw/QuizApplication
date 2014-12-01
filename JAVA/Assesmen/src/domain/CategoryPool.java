package domain;

import java.util.ArrayList;
import java.util.HashSet;

public class CategoryPool {
	HashSet<Category> categoryPool;

	public CategoryPool() {
		categoryPool = new HashSet<Category>();

		// temp
		try {
			Category temp = new Category();
			temp.setName("test");
			temp.setDescription("description test");
			AddCategory(temp);
		} catch (DomainException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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

	public void AddCategory(Category category) {
		if (!categoryPool.contains(category))
			this.categoryPool.add(category);
	}

	public void removeCategory(Category category) {
		if (categoryPool.contains(category))
			categoryPool.remove(category);
	}

}
