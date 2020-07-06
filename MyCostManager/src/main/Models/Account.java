package main.Models;
/**
 * Account class is the outer mantle of AccountSql class.
 * Including id,name and currency parameter
 * @author User
 *
 */
public class Account {
	private int id;
	private String name;
	private Currency currency;

	public Currency getCurrency() {
		return currency;
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
     * @param currency - parameter from Currency class
     */
	public Account(int id, String name, Currency currency) {
		this.id = id;
		this.name = name;
		this.currency = currency;
	}

}
