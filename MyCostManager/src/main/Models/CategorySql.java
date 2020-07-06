package main.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import main.Models.Category;
/**
 * Class CategorySql including all internal operations on tables.
 * @author User
 *
 */
public class CategorySql {
	PreparedStatement pstmt;
	private static final Logger log = Logger.getLogger(CategorySql.class);
	/**
	 *  Insert item to table and return id
	 * @param paramSel
	 * @param conn - parameter from Connection class 
	 * @throws ClassNotFoundException
	 */
	public void insertToTable(Category paramSel, Connection conn)  throws ClassNotFoundException {
		try {
			PreparedStatement pstmt;
			if (paramSel.getMainCategory()!=null)
			 pstmt=conn.prepareStatement("insert into Category (name,main_category_id) values (?,?)");	
			else
			 pstmt=conn.prepareStatement("insert into Category (name) values (?)");	
      	    pstmt.setString(1,paramSel.getName());
      	    if (paramSel.getMainCategory()!=null)
            pstmt.setInt(2,paramSel.getMainCategory().getId() );
			pstmt.execute();
			log.info("!!!! insert To Table  Category " );
			pstmt=conn.prepareStatement("update Category set main_category_id =id where main_category_id  is null");
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			log.info("err insert To Table Category" );
		} finally {
				if (pstmt != null)	try {pstmt.close();	} catch (Exception e) {	}
		}
	}
	
	/**
	 *  Update item table
	 * @param paramSel
	 * @param conn - parameter from Connection class 
	 * @throws ClassNotFoundException
	 */
	public void editTable(Category paramSel, Connection conn)	throws ClassNotFoundException {
		try {
			int i=1;
			if (paramSel.getMainCategory()!=null)
			 pstmt=conn.prepareStatement("update Category  set name=?, main_category_id=? where id=?");	
			else
			 pstmt=conn.prepareStatement("update Category  set name=? where id=?");		
      	    pstmt.setString(i++,paramSel.getName());
      	    if (paramSel.getMainCategory()!=null)
            pstmt.setInt(i++,paramSel.getMainCategory().getId());
            pstmt.setInt(i++,paramSel.getId());
			pstmt.execute();
			log.info("!!!! update Table Category id=" );
		} catch (SQLException e) {
			e.printStackTrace();
			log.info("err update Table Category id= " );
		} finally {
				if (pstmt != null)	try {pstmt.close();	} catch (Exception e) {	}
		}
	}


	/**
	 *  Delete item from table
	 * @param id
	 * @param conn - parameter from Connection class 
	 * @return true or false
	 * @throws ClassNotFoundException
	 */
	public boolean deleteItem(String id, Connection conn)	throws ClassNotFoundException {
		try {
			String query = "delete from Category where id=" + id;
			Statement st = conn.createStatement();
			st.executeUpdate(query);
			st.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	/**
	 *  Get max Id from table
	 * @param conn - parameter from Connection class 
	 * @return id
	 * @throws SQLException
	 */
		public int getMaxId(Connection conn) throws SQLException {
			Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			int id=0;
			ResultSet rs = st.executeQuery("Select max(id) from category");
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
	 * @param conn - parameter from Connection class 
	 * @return new category
	 * @throws SQLException
	 */
	public Category getCategory(String id, Connection conn) throws SQLException {
		PreparedStatement pstmt = conn.prepareStatement("Select * from category where id=" + id);
		ResultSet rs = pstmt.executeQuery();
		Category ct = null;
		while (rs.next()) {
			Category c = null;
			if (!(rs.getString("main_category_id").isEmpty() || (rs.getString("main_category_id").equals(rs.getString("id"))))) {
				c = getCategory(rs.getString("main_category_id"), conn);
			}
			ct = new Category(rs.getInt("id"), rs.getString("name"), c);
		}
		rs.close();
		pstmt.close();
		return ct;
	}

	/**
	 *  Select Category
	 * @param conn - parameter from Connection class 
	 * @param usl
	 * @return category
	 * @throws SQLException
	 */
	public ArrayList<Category> getCategorys(Connection conn,String usl)	throws SQLException {
		
		ArrayList<Category> cat = new ArrayList<>();
		PreparedStatement pstmt = conn.prepareStatement("Select * from category c "+usl+" order by c.main_category_id,c.id ");
		// log.info("getModelTableV query="+query);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			Category c = null;
			if (rs.getString("main_category_id")!=null)
			c = getCategory(rs.getString("main_category_id"), conn);
			Category ct = new Category(rs.getInt("id"), rs.getString("name"), c);
			cat.add(ct);
		}
		rs.close();
		pstmt.close();
		return cat;
	}

}
