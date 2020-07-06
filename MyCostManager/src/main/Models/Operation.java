package main.Models;


import java.sql.Date;
/**
 * Operation class is the outer mantle of OperationSql class.
 * Including id, name, income, cost, date, comment, parameter of Category class and parameter of Account class.
 * @author User
 *
 */
public class Operation {
	private int id;
	private double amount_in;
	private double amount_out;
	private Date date;
	private String comment;
	private Category category;
	private Account account;

	public double getAmount_in() {
		return amount_in;
	}

	public double getAmount_out() {
		return amount_out;
	}

	public Account getAccount() {
		return account;
	}

	public int getId() {
		return id;
	}

	public Date getDate() {
		return date;
	}

	public String getComment() {
		return comment;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
    /**
     * Constructor 
     * @param id
     * @param date
     * @param amount_in
     * @param amount_out
     * @param account - parameter of Account class
     * @param category - parameter of Category class
     * @param comment
     */
	public Operation(int id, Date date, double amount_in, double amount_out,
			Account account, Category category, String comment) {
		this.id = id;
		this.comment = comment;
		this.category = category;
		this.account = account;
		this.amount_in = amount_in;
		this.amount_out = amount_out;
		this.date = date;
	}
}
