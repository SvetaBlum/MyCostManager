package main.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;




import org.apache.log4j.Logger;
/**
 * Class CurrencySql including all internal operations on tables.
 * @author User
 *
 */
public class CurrencySql {
	Statement st;
	PreparedStatement pstmt;
	private static final Logger log = Logger.getLogger(CurrencySql.class);
	
	/**
	 *  Insert item to table and return id
	 * @param paramSel - parameter currency 
	 * @param connection parameter from Model class
	 * @throws ClassNotFoundException
	 */
	public void insertToTable(Currency paramSel,Connection conn) throws ClassNotFoundException {
	try {
		System.out.println("insertToTable");
		pstmt=conn.prepareStatement("insert into Currency (name)  values (?)");	
		pstmt.setString(1,paramSel.getName());
  	   	pstmt.execute();
		log.info("!!!! insertToTable Currency  " );
	 } catch (SQLException e) {
		e.printStackTrace();
		log.info("err insertToTable Currency  " );
	 } finally {
			if (pstmt != null)
			try {pstmt.close();	} catch (Exception e) {	}
	 }
	}
	
	/**
	 *  Update item table
	 * @param paramSel - parameter currency
	 * @param connection - parameter from Model class
	 * @throws ClassNotFoundException
	 */
	public void editTable(Currency paramSel,Connection conn) throws ClassNotFoundException {
		try {
			pstmt=conn.prepareStatement("update Currency set name=? where id=? ");	
	  	    pstmt.setString(1,paramSel.getName());
	  	    pstmt.setInt(2,paramSel.getId());
	 		pstmt.execute();
			log.info("!!!! update Table Currency " );
		 } catch (SQLException e) {
			log.info("err update Table Currency " );
			e.printStackTrace();
		 } finally {
				if (pstmt != null)	try {pstmt.close();	} catch (Exception e) {	}
		 }
	}
	
			
	/**
	 *  Delete item from table
	 * @param id
	 * @param connection - parameter from Model class
	 * @throws ClassNotFoundException
	 */
	public boolean deleteItem(String id,Connection conn) throws ClassNotFoundException {
		try {
			String query = "delete from Currency where id=" + id;
			st = conn.createStatement();
			st.executeUpdate(query);
			log.info("delete from Table Currency id= "+id );
			return true;
		} catch (Exception e) {
			log.info("err delete from Table Currency id= "+id +" forenkey");
			e.printStackTrace();
			return false;
		} finally {
			if (st != null)	try {st.close();} catch (Exception e) {	}
		}
	}
	/**
	 *  Get max Id from table
	 * @param connection - parameter from Model class
	 * @return id
	 * @throws SQLException
	 */
	public int getMaxId(Connection conn) throws SQLException {
		st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
		int id=0;
		ResultSet rs = st.executeQuery("Select max(id) from currency");
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
	 * 	Adding to array list of currency	
	 * @param connection - parameter from Model class 
	 * @return array list
	 * @throws SQLException
	 */
	public ArrayList<Currency> getCurrencys(Connection conn) throws SQLException {
		ArrayList<Currency> curr = new ArrayList<>();

		st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
		ResultSet rs = st.executeQuery("Select * from currency");
		if (rs != null) {
			while (rs.next()) {
				Currency c = new Currency(rs.getInt("id"), rs.getString("name"));
				curr.add(c);
			}
		}
		rs.close();
		st.close();
		return curr;
	}
	/**
	 * Select items by id
	 * @param id
	 * @param connection - parameter from Model class
	 * @return c - The currency that find by id (id+name)
	 * @throws SQLException
	 */
	public Currency getCurrency(String id,Connection conn) throws SQLException {
		Statement st3= conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
		ResultSet rs3 = st3.executeQuery("Select * from currency where id="+id);
		Currency c=null;
		if (rs3 != null) {
			while (rs3.next()) {
			 c = new Currency(rs3.getInt("id"), rs3.getString("name"));
			}
		}
		rs3.close();
		st3.close();
		return c;
	}
}
