package main.Models;
/**
 * Currency class is the outer mantle of CurrencySql class.
 * Including id and name.
 * @author User
 *
 */

public class Currency {

	private int id;
	private String name;

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
	 */

	public Currency(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	
}
