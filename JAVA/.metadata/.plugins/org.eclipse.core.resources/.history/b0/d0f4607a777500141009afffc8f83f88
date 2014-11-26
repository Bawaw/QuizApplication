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

	private void setCategoryPool(HashSet<Category> categoryPool) {
		this.categoryPool = categoryPool;
	}

	public void AddAnswer(Category category){
		if (!categoryPool.contains(category))
			this.categoryPool.add(category);
	}

	public void removeAnswer(Category category){
		if (categoryPool.contains(category))
			categoryPool.remove(category);
	}

}
