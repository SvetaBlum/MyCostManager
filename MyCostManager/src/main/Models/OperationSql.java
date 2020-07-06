package main.Models;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Locale;

import org.apache.log4j.Logger;

import main.Models.Operation;
/**
 * Class OperationSql including all internal operations on tables.
 * @author User
 *
 */
public class OperationSql {
	Statement st;
	ResultSet rs;
	PreparedStatement pstmt;
	private static final Logger log = Logger.getLogger(OperationSql.class);
	
	/**
	 *  Insert table and return id
	 * @param paramSel - parameter of operation
	 * @param connectoin
	 * @throws ClassNotFoundException
	 */
	public void insertToTable(Operation paramSel,Connection conn) throws ClassNotFoundException {
		try {
			String query = "insert into Operation (amount_in,amount_out,date_oper,category_id,account_id,comment)"
					      +" values (?,?,?,?,?,?)";
			pstmt=conn.prepareStatement(query);	
      	    pstmt.setDouble(1,paramSel.getAmount_in());
            pstmt.setDouble(2,paramSel.getAmount_out());
      	    pstmt.setDate(3,paramSel.getDate());
      	    pstmt.setInt(4,paramSel.getCategory().getId());
      	    pstmt.setInt(5,paramSel.getAccount().getId());
      	    pstmt.setString(6,paramSel.getComment());
      	    pstmt.execute();
      	    log.info(" insertToTable Account");
		 } catch (SQLException e) {
			 log.info("err insertToTable Account");
			 e.printStackTrace();
		 } finally {
				if (pstmt != null)
					try {pstmt.close();	} catch (Exception e) {}
			}
	 }

	/**
	 *  Update table
	 * @param paramSel - parameter of operation
	 * @param Connection
	 * @throws ClassNotFoundException
	 */
	public void editTable(Operation paramSel,Connection conn) throws ClassNotFoundException {
		try {
			String query ="update operation set amount_in=?,amount_out=?,date_oper=?,"
		                 + "category_id=?,account_id=?,comment=?  where id=? ";
			pstmt=conn.prepareStatement(query);	
  		    pstmt.setDouble(1,paramSel.getAmount_in());
  		    pstmt.setDouble(2,paramSel.getAmount_out());
  		    pstmt.setDate(3,paramSel.getDate());
  		    pstmt.setInt(4,paramSel.getCategory().getId());
  		    pstmt.setInt(5,paramSel.getAccount().getId());
  		    pstmt.setString(6,paramSel.getComment());
  		    pstmt.setInt(7,paramSel.getId());
  		  	pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (Exception e) {
				}
		}
	}
	/**
	 *  Delete item from table
	 * @param id
	 * @param connection
	 * @throws ClassNotFoundException
	 */
		public boolean deleteItem(String id,Connection conn) throws ClassNotFoundException {
			try {
				String query = "delete from operation where id=" + id;
				st = conn.createStatement();
				st.executeUpdate(query);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			} finally {
				if (st != null)		try {st.close();} catch (Exception e) {	}
			}
		}
	/**
	 *  Get max Id from table
	 * @param connection
	 * @return id
	 * @throws SQLException
	 */
		public int getMaxId(Connection conn) throws SQLException {
			st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			int id=0;
			ResultSet rs = st.executeQuery("Select max(id) from operation");
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
		 *  Select operation by id
		 * @param id
		 * @param connection
		 * @throws SQLException
		 */
		public Operation getOperation(String id, Connection conn) throws SQLException {
			PreparedStatement pstmt = conn.prepareStatement("Select * from Operation where id=" + id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Account a = (new AccountSql()).getAccount(rs.getString("account_id"),conn);
				Category ct=(new CategorySql()).getCategory(rs.getString("category_id"),conn);
				
				Operation op = new Operation(rs.getInt("id"),rs.getDate("date_oper"),rs.getDouble("amount_in"),
						rs.getDouble("amount_out"),a,ct,rs.getString("comment"));
				return op;
			}
			rs.close();
			pstmt.close();
			return null;
		}
	/**
	 * Select Operations 	
	 * @param paramSel - array list
	 * @param connection
	 * @return list of operation
	 * @throws SQLException
	 */
		public ArrayList<Operation> getOperations(ArrayList<String> paramSel,Connection conn) throws SQLException {
			ArrayList<Operation> list = new ArrayList<>();
			java.util.Date d1 = null;
			java.util.Date d2 = null;
			try {
				d1 = DateFormat.getDateInstance(DateFormat.MEDIUM,Locale.GERMANY).parse(paramSel.get(0));
				d2 = DateFormat.getDateInstance(DateFormat.MEDIUM,Locale.GERMANY).parse(paramSel.get(1));
			} catch (ParseException ex) {}
			java.sql.Date dt1 = new java.sql.Date(d1.getTime());
			java.sql.Date dt2 = new java.sql.Date(d2.getTime());
			
			PreparedStatement pstmt=conn.prepareStatement("Select * from operation o where o.date_oper >=?"
			+" and o.date_oper <=?");
			pstmt.setDate(1,dt1);
			pstmt.setDate(2,dt2);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Account a = (new AccountSql()).getAccount(rs.getString("account_id"),conn);
				Category ct=(new CategorySql()).getCategory(rs.getString("category_id"),conn);
				
				Operation o = new Operation(rs.getInt("id"),rs.getDate("date_oper"),rs.getDouble("amount_in"),
						rs.getDouble("amount_out"),a,ct,rs.getString("comment"));
				list.add(o);
			}

			rs.close();
			pstmt.close();
			
			return list;
		}
		/**
		 *  Selects from Operation 
		 * @param reg - number of operation
		 * @param paramSel - array list
		 * @param connection
		 * @throws SQLException
		 */
	public ArrayList<ArrayList<Object>> getViv(int reg,ArrayList<String> paramSel,Connection conn) throws SQLException {
	 int i=1;
	 ArrayList<ArrayList<Object>> dataq = new ArrayList<ArrayList<Object>>();
	 String query ="";
	 java.util.Date d1=null;
	 java.util.Date d2=null;
	 java.sql.Date dt1=null;
	 java.sql.Date dt2=null;
	 if (reg!=6){  
		 try {
			d1 = DateFormat.getDateInstance(DateFormat.MEDIUM,Locale.GERMANY).parse(paramSel.get(0));
			d2 = DateFormat.getDateInstance(DateFormat.MEDIUM,Locale.GERMANY).parse(paramSel.get(1));
		 } catch (ParseException ex) {}
		 dt1 = new java.sql.Date(d1.getTime());
		 dt2 = new java.sql.Date(d2.getTime());
	}
	/**
	 *   Variables For sub categories
	 */
	String qsVivOperation = " SELECT 1 reg,ctm.name ||' / '||ct.name,a.name,c.name,sum(o.amount_in),sum(o.amount_out),sum(o.amount_in-o.amount_out) from Operation o,Account a, Currency c ,Category ct,Category ctm "
        	+ " where o.account_id=a.id and o.category_id=ct.id and a.currency_id=c.id and ct.main_category_id=ctm.id and ctm.id<>ct.id  "; 
	/**
	 *  Variables For main_category
	 */
	String qsVivOperation1 = " union SELECT 1 reg,ct.name,a.name,c.name,sum(o.amount_in),sum(o.amount_out),sum(o.amount_in-o.amount_out) from Operation o,Account a, Currency c ,Category ct "
			+ " where o.account_id=a.id and o.category_id=ct.id and a.currency_id=c.id and ct.main_category_id=ct.id  "; 
	/**
	 *  Variables For total by currency
	 */
	String qsVivOperation2 = " union SELECT 2 reg,'','',c.name,sum(o.amount_in),sum(o.amount_out),sum(o.amount_in-o.amount_out) from Operation o,Account a, Currency c ,Category ct "
			+ " where o.account_id=a.id and o.category_id=ct.id and a.currency_id=c.id "; 
			        
	String qsCAnOperation = "SELECT ct.name,sum(o.amount_in),sum(o.amount_out),sum(o.amount_in-o.amount_out),c.name  from Operation o,Account a, Currency c ,Category ct  "
			+ " where o.account_id=a.id and o.category_id=ct.id and a.currency_id=c.id  "; 
	String qsCAnOperation1 = " union SELECT '',0,0,sum(o.amount_in-o.amount_out),c.name  from Operation o,Account a, Currency c ,Category ct "
  			+ " where o.account_id=a.id and o.category_id=ct.id and a.currency_id=c.id "; 

	String qsCaAnOperation = "SELECT ct.name,sum(o.amount_in),sum(o.amount_out),sum(o.amount_in-o.amount_out),c.name,a.name  from Operation o,Account a, Currency c ,Category ct  "
			+ " where o.account_id=a.id and o.category_id=ct.id and a.currency_id=c.id  "; 
	String qsCaAnOperation1 = " union SELECT '',0,0,sum(o.amount_in-o.amount_out),c.name ,a.name from Operation o,Account a, Currency c ,Category ct  "
			+ " where o.account_id=a.id and o.category_id=ct.id and a.currency_id=c.id ";

	switch (reg) {
	 case 6:
		/**
		 *  Balance, Input panel
		 */
	   	query = "SELECT a.name,sum(o.amount_in-o.amount_out) ,'account',c.name from Operation o,Account a, Currency c "
	    + " where o.account_id=a.id  and a.currency_id=c.id group by a.name ,c.name"
	    + " union "
	    + " SELECT c.name,sum(o.amount_in-o.amount_out) ,'currency','' from Operation o,Account a, Currency c  "
	    + " where o.account_id=a.id  and a.currency_id=c.id group by c.name order by 3 ";
	   	pstmt=conn.prepareStatement(query);
	    break;
	 case 7:
	  	/**
	  	 *  Table in output panel
	  	 */
	    query = qsVivOperation +" and o.date_oper >=? and o.date_oper <=?";
	    if (paramSel.get(2).length() > 0) query += " and o.account_id =?";
	    if (paramSel.get(3).length() > 0) query += " and (ct.id=? or ct.main_category_id=?)";
     	if (paramSel.get(4).length() > 0) query += " and a.currency_id=?";
		query += " group by a.name,c.name,ct.name ,ctm.name  "  ; 

		query += qsVivOperation1+" and o.date_oper >=? and o.date_oper <=?";
		if (paramSel.get(2).length() > 0) query += " and o.account_id =?";
		if (paramSel.get(3).length() > 0) query += " and (ct.id=? or ct.main_category_id=?)";
     	if (paramSel.get(4).length() > 0) query += " and a.currency_id=?";
     	query += " group by a.name,c.name,ct.name  " ;
     	         
		query += qsVivOperation2+ " and o.date_oper >=? and o.date_oper <=?";
		if (paramSel.get(2).length() > 0) query += " and o.account_id =?";
		if (paramSel.get(3).length() > 0) query += " and (ct.id=? or ct.main_category_id=?)";
     	if (paramSel.get(4).length() > 0) query += " and a.currency_id=?";
		query += " group by c.name order by 1,2,3 ";
					
		pstmt=conn.prepareStatement(query);  
		pstmt.setDate(i++,dt1);
		pstmt.setDate(i++,dt2);
		if (paramSel.get(2).length() > 0) 
		 pstmt.setInt(i++,Integer.valueOf(paramSel.get(2).substring(0, 5).trim()).intValue());
		if (paramSel.get(3).length() > 0){
		 pstmt.setInt(i++,Integer.valueOf(paramSel.get(3).substring(0, 5).trim()).intValue());
		 pstmt.setInt(i++,Integer.valueOf(paramSel.get(3).substring(0, 5).trim()).intValue());
		}
		if (paramSel.get(4).length() > 0)
		 pstmt.setInt(i++,Integer.valueOf(paramSel.get(4).substring(0, 5).trim()).intValue());
					 
		pstmt.setDate(i++,dt1);
		pstmt.setDate(i++,dt2);
		if (paramSel.get(2).length() > 0)
		 pstmt.setInt(i++,Integer.valueOf(paramSel.get(2).substring(0, 5).trim()).intValue());
		if (paramSel.get(3).length() > 0){
		 pstmt.setInt(i++,Integer.valueOf(paramSel.get(3).substring(0, 5).trim()).intValue());
		 pstmt.setInt(i++,Integer.valueOf(paramSel.get(3).substring(0, 5).trim()).intValue());
		}
		if (paramSel.get(4).length() > 0)
		 pstmt.setInt(i++,Integer.valueOf(paramSel.get(4).substring(0, 5).trim()).intValue());
					 
		pstmt.setDate(i++,dt1);
		pstmt.setDate(i++,dt2);
		if (paramSel.get(2).length() > 0)
		 pstmt.setInt(i++,Integer.valueOf(paramSel.get(2).substring(0, 5).trim()).intValue());
		if (paramSel.get(3).length() > 0){
		 pstmt.setInt(i++,Integer.valueOf(paramSel.get(3).substring(0, 5).trim()).intValue());
		 pstmt.setInt(i++,Integer.valueOf(paramSel.get(3).substring(0, 5).trim()).intValue());
		}
		if (paramSel.get(4).length() > 0)
		 pstmt.setInt(i++,Integer.valueOf(paramSel.get(4).substring(0, 5).trim()).intValue());
		break;
	 case 8: // chart 1  in analysis panel
		query = qsCAnOperation+" and o.date_oper >=? and o.date_oper <=? ";
		if (paramSel.get(2).length() > 0) query += " and c.id =? ";
		query += " and o.amount_out>0 group by ct.name ,c.name order by 1  ";
		pstmt=conn.prepareStatement(query);  
		pstmt.setDate(i++,dt1);
		pstmt.setDate(i++,dt2);
		if (paramSel.get(2).length() > 0)
		 pstmt.setInt(i++,Integer.valueOf(paramSel.get(2).substring(0, 5).trim()).intValue());
		break;
	 case 9:// textpanel1 in analysis panel
		query = qsCAnOperation+" and o.date_oper >=? and o.date_oper <=? and c.id =? group by ct.name ,c.name  ";
		query += qsCAnOperation1 + " and o.date_oper <? and c.id =? group by c.name order by 1 ";
		pstmt=conn.prepareStatement(query);  
		pstmt.setDate(i++,dt1);
		pstmt.setDate(i++,dt2);
		pstmt.setInt(i++,Integer.valueOf(paramSel.get(2).substring(0, 5).trim()).intValue());
		pstmt.setDate(i++,dt1);
		pstmt.setInt(i++,Integer.valueOf(paramSel.get(2).substring(0, 5).trim()).intValue());
		break;
     case 10:// textpanel2 in analysis panel
		query = qsCaAnOperation+" and o.date_oper >=? and o.date_oper <=? and c.id =? group by ct.name ,c.name ,a.name ";
		query += qsCaAnOperation1 + " and o.date_oper <? and c.id =? group by c.name,a.name order by 6,1 ";
		pstmt=conn.prepareStatement(query);  
		pstmt.setDate(i++,dt1);
		pstmt.setDate(i++,dt2);
		pstmt.setInt(i++,Integer.valueOf(paramSel.get(2).substring(0, 5).trim()).intValue());
		pstmt.setDate(i++,dt1);
		pstmt.setInt(i++,Integer.valueOf(paramSel.get(2).substring(0, 5).trim()).intValue());
		break;
	 default:
	   // 11 chart 2  in analysis panel
		query = qsCaAnOperation+" and o.date_oper >=? and o.date_oper <=? and c.id =? ";
		query += " and o.amount_out>0 group by ct.name ,c.name,a.name order by 1  ";
		pstmt=conn.prepareStatement(query);  
		pstmt.setDate(i++,dt1);
		pstmt.setDate(i++,dt2);
		pstmt.setInt(i++,Integer.valueOf(paramSel.get(2).substring(0, 5).trim()).intValue());
		break;
	}				
	ResultSet rs = pstmt.executeQuery();
	int nColumns = rs.getMetaData().getColumnCount();
	while (rs.next()) {
		ArrayList<Object> v = new ArrayList<Object>();
		for (int j = 0; j < nColumns; j++) {
			v.add(rs.getObject(j + 1));
		}
		dataq.add(v);
	}
	rs.close();
	pstmt.close();
		
	return dataq;
  }
}
