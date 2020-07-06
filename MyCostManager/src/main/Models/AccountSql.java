package main.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import main.Models.Currency;
/**
 * Class AccountSql including all internal operations on tables.
 * @author User
 *
 */
public class AccountSql {
	Statement st;
	PreparedStatement pstmt;
	private static final Logger log = Logger.getLogger(AccountSql.class);

	/**
	 *  Insert item to table and return id
	 * @param paramSel - parameter of Account
	 * @param connection - parameter from Connection class
	 * @throws ClassNotFoundException
	 */
	public void insertToTable(Account paramSel, Connection conn)	throws ClassNotFoundException {
		try {
			if (paramSel.getCurrency()!=null)
			pstmt=conn.prepareStatement("insert into account (name,currency_id)  values (?,?)");
			else
			pstmt=conn.prepareStatement("insert into account (name)  values (?)");	
      	    pstmt.setString(1,paramSel.getName());
      	    if (paramSel.getCurrency()!=null)
            pstmt.setInt(2,paramSel.getCurrency().getId());
			pstmt.execute();
			log.info("!!!! insertToTable Account " );
		} catch (SQLException e) {
			e.printStackTrace();
			log.info("err insertToTable Account " );
		} finally {
				if (pstmt != null)	try {pstmt.close();	} catch (Exception e) {	}
		}
	}

	/**
	 *  Update item table
	 * @param paramSel - parameter of Account
	 * @param connection - parameter from Connection class
	 * @throws ClassNotFoundException
	 */
	public void editTable(Account paramSel, Connection conn)	throws ClassNotFoundException {
		try {
			pstmt=conn.prepareStatement("update account set name=?,currency_id=? where id=? ");	
      	    pstmt.setString(1,paramSel.getName());
            pstmt.setInt(2,paramSel.getCurrency().getId());
            pstmt.setInt(3,paramSel.getId());
			pstmt.execute();
			log.info("!!!! update Table Account id="+paramSel.getId() );
		} catch (SQLException e) {
			e.printStackTrace();
			log.info("err update Table Account " );
		} finally {
				if (pstmt != null)	try {pstmt.close();	} catch (Exception e) {	}
		}
	}
			
	/**
	 *  Delete item from table
	 * @param id
	 * @param connection - parameter from Connection class
	 * @return true or false
	 * @throws ClassNotFoundException
	 */
	public boolean deleteItem(String id, Connection conn)
			throws ClassNotFoundException {
		try {
			String query = "delete from account where id=" + id;
			st = conn.createStatement();
			st.executeUpdate(query);
			log.info("delete from Table Account id= "+id );
			return true;
		} catch (Exception e) {
			log.info("err delete from Table Account id= "+id +" forenkey");
			e.printStackTrace();
			return false;
		} finally {
			if (st != null)	try {st.close();} catch (Exception e) {	}
		}
	}
	/**
	 * Get max Id from table
	 * @param connection - parameter from Connection class
	 * @return id
	 * @throws SQLException
	 */
	public int getMaxId(Connection conn) throws SQLException {
			st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			int id=0;
			ResultSet rs = st.executeQuery("Select max(id) from account");
			if (rs != null) {
				while (rs.next()) {
					id= rs.getInt(1);
				}
			}
			rs.close();
			st.close();
			return id;
	}
	/**
	 * Select items by id
	 * @param id
	 * @param connection - parameter from Connection class
	 * @return new account
	 * @throws SQLException
	 */
	public Account getAccount(String id, Connection conn) throws SQLException {
		PreparedStatement pstmt = conn.prepareStatement("Select * from account where id=" + id);
		ResultSet rs = pstmt.executeQuery();
		Account a = null;
		while (rs.next()) {
		    Currency c =null;
			if (rs.getString("currency_id")!=null)
			c= (new CurrencySql()).getCurrency(rs.getString("currency_id"), conn);
			a = new Account(rs.getInt("id"), rs.getString("name"), c);
		}
		rs.close();
		pstmt.close();
		return a;
	}
    /**
     * Select Accounts
     * @param connection - parameter from Connection class
     * @return new array list of account
     * @throws SQLException
     */
	public ArrayList<Account> getAccounts(Connection conn) throws SQLException {
		ArrayList<Account> acc = new ArrayList<>();
		PreparedStatement pstmt = conn.prepareStatement("Select * from account");
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			CurrencySql curSqll = new CurrencySql();
			Currency c =null;
			if (rs.getString("currency_id")!=null)
			c = curSqll.getCurrency(rs.getString("currency_id"), conn);
			Account a = new Account(rs.getInt("id"), rs.getString("name"), c);
			acc.add(a);
		}
		rs.close();
		pstmt.close();
		return acc;
	}
}
