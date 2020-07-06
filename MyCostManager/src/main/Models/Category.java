package main.Models;
/**
 * Category class is the outer mantle of CategorySql class.
 * Including id, name and self parameter of Category class.
 * @author User
 *
 */
public class Category {
	private int id;
	private String name;
	private Category mainCategory;

	public Category getMainCategory() {
		return mainCategory;
	}

	public int getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}
    /**
     * Constructor
     * @param id
     * @param name
     * @param mainCategory
     */
	public Category(int id, String name, Category mainCategory) {
		this.id = id;
		this.name = name;
		this.mainCategory = mainCategory;
	}
}
