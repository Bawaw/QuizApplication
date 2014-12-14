package domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class FacadeManager {
	private Collection<Category> categories = new HashSet<Category>();
	
	
	public FacadeManager() {
	
		Category designPrinciples = new Category("Design principles", "The SOLID design principles");
		Category designPatterns = new Category("Design patterns", "Design patterns discussed this year");
		Category uml = new Category("UML", "Technique of drawing a class diagram");
		Category java = new Category("Java", "Java extra's");
		addCategory(designPrinciples);
		addCategory(designPatterns);		
		addCategory(uml);
		addCategory(java);
	
		
		
	}

	public void addCategory(Category category){
		categories.add(category);
	}

	public List<Category> getAllCategories() {
		return new ArrayList<Category>(categories);
	}

}
