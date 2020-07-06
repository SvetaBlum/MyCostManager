package main.homeCost;

import main.homeCost.IModel;
import main.homeCost.MVVMDemoException;
import main.homeCost.Model;
import main.Models.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import javax.swing.table.DefaultTableModel;

import org.apache.log4j.Logger;

public class Model implements IModel {
	private static final Logger log = Logger.getLogger(Model.class);

	DateFormat fmt = DateFormat.getDateInstance(DateFormat.MEDIUM,Locale.GERMANY);
	public static DateFormat fmt2 = new SimpleDateFormat("dd.MM.yyyy");
	public static String[] comboType = { "currency", "account", "category" };

	public static ArrayList<String> combo = new ArrayList<>();
	public static ArrayList<String> comboAcc = new ArrayList<>();
	public static ArrayList<String> comboCat = new ArrayList<>();
	public static ArrayList<String> comboCatM = new ArrayList<>();

	Connection conn = null;

	Statement st = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	public static String username = "cost";
	public static String password = "1234567";
	//public static String driver = "org.apache.derby.jdbc.ClientDriver";
	public static String driver = "org.apache.derby.jdbc.EmbeddedDriver";
	//public static String protocol = "jdbc:derby://localhost:1527/costHome;create=true";
	public static String protokol = "jdbc:derby:costHome;create=true";

	/**
	 *  CREATE TABLE
	 */
	public static String qCurrency = " CREATE TABLE Currency (Id INT NOT NULL GENERATED ALWAYS AS IDENTITY,name VARCHAR(255),PRIMARY KEY (Id))";// currency

	public static String qAccount = " CREATE TABLE Account (Id INT NOT NULL GENERATED ALWAYS AS IDENTITY,name VARCHAR(255),currency_id INT,PRIMARY KEY (Id))"; // account
	public static String qAccountFK = " ALTER TABLE Account ADD CONSTRAINT FK_AcountCurrency FOREIGN KEY (currency_id) REFERENCES Currency(ID)";

	public static String qCategory = " CREATE TABLE Category (Id INT NOT NULL GENERATED ALWAYS AS IDENTITY,name VARCHAR(255),main_category_id INT,PRIMARY KEY (Id))"; // category
	public static String qCategoryFK = " ALTER TABLE Category ADD CONSTRAINT FK_CategoryCategoryMain FOREIGN KEY (main_category_id) REFERENCES Category(ID)";

	public static String qOperation = " CREATE TABLE Operation (Id INT NOT NULL GENERATED ALWAYS AS IDENTITY,amount_in DOUBLE,amount_out DOUBLE,date_oper DATE,comment VARCHAR(255),category_id INT,account_id INT,PRIMARY KEY (Id))";
	public static String qOperationFK = " ALTER TABLE Operation ADD CONSTRAINT FK_OperationAcount FOREIGN KEY (account_id) REFERENCES Account(ID)";
	public static String qOperationFK2 = " ALTER TABLE Operation ADD CONSTRAINT FK_OperationCategory FOREIGN KEY (category_id) REFERENCES Category(ID)";

	/**
	 *  SELECTS FROM TABLE and COLUMNS_NAME
	 */

	String columnCurrency[] = { "id", "name" };
	String columnAccount[] = { "id", "name", "currency", "cur_id" };
	String columnCategory[] = { "id", "category", "subcategory", "id_main_cat" };
	String columnOperation[] = { "id", "data", "incoming", "consumption", "account","category", "comment", "aId", "cId" };
	String columnVivOperation[] = { "reg", "category", "account", "currency","incoming", "consumption", "balance" };
	
//	Class columnCurrencyType[] = {Integer.class, String.class };
//	Class columnAccountType[] = {Integer.class, String.class, String.class,Integer.class};
//	Class columnCategoryType[] = {Integer.class, String.class, String.class,Integer.class};
//  Class columnOperationType[] = {Integer.class, String.class,Double.class,Double.class,String.class,String.class,String.class,Integer.class,Integer.class};
//  Class columnVivOperationType[] = {Integer.class, String.class, String.class, String.class,Double.class,Double.class,Double.class};
	@Override
	public void connection() throws ClassNotFoundException {

		try {
			conn = null;
			Class.forName(driver);
			conn = DriverManager.getConnection(protokol, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void createTableDB() throws ClassNotFoundException {
		try {
			st = conn.createStatement();

			st.execute(qCurrency);
			st.execute(qAccount);
			st.execute(qAccountFK);
			st.execute(qCategory);
			st.execute(qCategoryFK);
			st.execute(qOperation);
			st.execute(qOperationFK);
			st.execute(qOperationFK2);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (st != null)
				try {
					st.close();
				} catch (Exception e) {
				}
		}
	}

	@Override
	public boolean addItem(int reg, ArrayList<String> paramSel)	throws MVVMDemoException, SQLException {
		try {
			int id = Integer.valueOf(paramSel.get(0)).intValue();
			switch (reg) {
			case 1:
				Currency cur = new Currency(id, paramSel.get(1));
				(new CurrencySql()).insertToTable(cur, conn);
				break;
			case 2:
				Currency c = (new CurrencySql()).getCurrency(paramSel.get(2).length() > 0 ? paramSel.get(2).substring(0, 5).trim(): "0", conn);
				Account acc = new Account(id, paramSel.get(1), c);
				(new AccountSql()).insertToTable(acc, conn);
				break;
			case 3:
				Category ctm = null;
				if (paramSel.get(2).length() > 0) {
			    	ctm = (new CategorySql()).getCategory(paramSel.get(2).length() > 0 ? paramSel.get(2).substring(0, 5).trim() : "0", conn);
				}
				Category cat = new Category(id, paramSel.get(1), ctm);
				(new CategorySql()).insertToTable(cat, conn);
				break;
			default:
				Account a = (new AccountSql()).getAccount(paramSel.get(5).length() > 0 ? paramSel.get(5).substring(0, 5).trim()	: "0", conn);
				Category ct = (new CategorySql()).getCategory(paramSel.get(4).length() > 0 ? paramSel.get(4).substring(0, 5).trim()	: "0", conn);

				java.util.Date d1 = null;
				try {
					d1 = DateFormat.getDateInstance(DateFormat.MEDIUM,Locale.GERMANY).parse(paramSel.get(1));
				} catch (ParseException ex) {}
				java.sql.Date dt1 = new java.sql.Date(d1.getTime());

				Operation oper = new Operation(id, dt1,
						paramSel.get(2).length() > 0 ? Double.valueOf(paramSel.get(2)).doubleValue() : 0,
						paramSel.get(3).length() > 0 ? Double.valueOf(paramSel.get(3)).doubleValue() : 0,
						a, ct,paramSel.get(6));

				(new OperationSql()).insertToTable(oper, conn);
			}

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Insert error" + e.toString());
		} finally {
			if (st != null)
				try {
					st.close();
				} catch (Exception e) {
				}
		}
		log.info("Insert error");
		return false;
	}

	@Override
	public boolean editItem(int reg, ArrayList<String> paramSel)
			throws MVVMDemoException, SQLException {
		try {
			int id = Integer.valueOf(paramSel.get(0)).intValue();
			switch (reg) {
			case 1:
				(new CurrencySql()).editTable(new Currency(id, paramSel.get(1)), conn);
				break;
			case 2:
				Currency c = (new CurrencySql()).getCurrency(paramSel.get(2).length() > 0 ? paramSel.get(2).substring(0, 5).trim() : "0", conn);
				(new AccountSql()).editTable(new Account(id, paramSel.get(1), c), conn);
				break;
			case 3:
				Category ctm = null;
				if (paramSel.get(2).length() > 0) {
					ctm = (new CategorySql()).getCategory(paramSel.get(2).substring(0, 5).trim(), conn);
				}
				(new CategorySql()).editTable(new Category(id, paramSel.get(1),ctm), conn);
				break;
			default:
				Account a = (new AccountSql()).getAccount(paramSel.get(5).length() > 0 ? paramSel.get(5).substring(0, 5).trim()	: "0", conn);
				Category ct = (new CategorySql()).getCategory(paramSel.get(4).length() > 0 ? paramSel.get(4).substring(0, 5).trim()	: "0", conn);

				java.util.Date d1 = null;
				try {
					d1 = DateFormat.getDateInstance(DateFormat.MEDIUM,Locale.GERMANY).parse(paramSel.get(1));
				} catch (ParseException ex) {}
				java.sql.Date dt1 = new java.sql.Date(d1.getTime());

				Operation oper = new Operation(id, dt1, 
						paramSel.get(2).length() > 0 ? Double.valueOf(paramSel.get(2)).doubleValue() : 0,
						paramSel.get(3).length() > 0 ? Double.valueOf(paramSel.get(3)).doubleValue() : 0,
						a, ct,paramSel.get(6));

				(new OperationSql()).editTable(oper, conn);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteItem(int reg, String id) throws MVVMDemoException,
			SQLException {
		try {
			switch (reg) {
			case 1:
				(new CurrencySql()).deleteItem(id, conn);
				break;
			case 2:
				(new AccountSql()).deleteItem(id, conn);
				break;
			case 3:
				(new CategorySql()).deleteItem(id, conn);
				break;
			default:
				(new OperationSql()).deleteItem(id, conn);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public DefaultTableModel getModelTable(int reg,ArrayList<String> paramSel) throws ClassNotFoundException {
		try {
			DefaultTableModel model = new DefaultTableModel();
			model.setColumnIdentifiers(getColumns(reg));
			switch (reg) {
			case 1:
				// "id", "name"
				ArrayList<Currency> listC = (new CurrencySql()).getCurrencys(conn);
				for (Currency item : listC) {
					ArrayList<Object> v = new ArrayList<Object>();
					v.add(item.getId());
					v.add(item.getName());
					model.addRow(v.toArray());
				}
				return model;
			case 2:
				// "id", "name", "currency", "cur_id"
				ArrayList<Account> listA = (new AccountSql()).getAccounts(conn);
				for (Account item : listA) {
					ArrayList<Object> v = new ArrayList<Object>();
					v.add(item.getId());
					v.add(item.getName());
					v.add(item.getCurrency().getName());
					v.add(item.getCurrency().getId());
					model.addRow(v.toArray());
				}
				return model;
			case 3:
				// "id", "category", "podcategory","id_main_cat" 
				ArrayList<Category> listCt = (new CategorySql()).getCategorys(conn, "");
				for (Category item : listCt) {
					ArrayList<Object> v = new ArrayList<Object>();
					v.add(item.getId());
					if (item.getMainCategory().getId() != item.getId()) {
						v.add(item.getMainCategory().getName());
						v.add(item.getName());
					} else{
					  v.add(item.getName());
				   	  v.add("");
					}
					v.add(item.getMainCategory().getId());
					model.addRow(v.toArray());
				}
				return model;
			case 7:
				ArrayList<ArrayList<Object>> dataq =  (new OperationSql()).getViv(reg,paramSel, conn);
				model.setColumnIdentifiers(getColumns(reg));
				for (ArrayList<Object> list : dataq) {
					model.addRow(list.toArray());
				}
				return model;
			default: //5
				//  "id", "data", "in","out", "account", "category", "comment", "aId", "cId" 
				ArrayList<Operation> listO = (new OperationSql()).getOperations(paramSel, conn);
				for (Operation item : listO) {
					ArrayList<Object> v = new ArrayList<Object>();
					v.add(item.getId());
					v.add(fmt.format(item.getDate().getTime()));
					v.add(item.getAmount_in());
					v.add(item.getAmount_out());
					v.add(item.getAccount().getName() + " / "+ item.getAccount().getCurrency().getName());
					if ((item.getCategory().getMainCategory()!=null) && (item.getCategory().getMainCategory().getId() != item.getCategory().getId()))
						v.add(item.getCategory().getMainCategory().getName()+ " / " + item.getCategory().getName());
					else
						v.add(item.getCategory().getName());

					v.add(item.getComment());
					v.add(item.getAccount().getId());
					v.add(item.getCategory().getId());

					model.addRow(v.toArray());
				}
				return model;
			}

		} catch (Exception e) {	e.printStackTrace();}
		return null;
	}
	@Override
	public ArrayList<ArrayList<Object>> getModelTableV(int reg,ArrayList<String> paramSel) throws ClassNotFoundException {
		try {
			ArrayList<ArrayList<Object>> dataq = (new OperationSql()).getViv(reg,paramSel, conn);
			// log.info("getModelTableV 2");
			return dataq;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public String getText(int reg, ArrayList<String> paramSel)	throws ClassNotFoundException {
		try{
		ArrayList<ArrayList<Object>> datas =(new OperationSql()).getViv(reg,paramSel,conn);
		 log.info("getText datas="+datas.size());
		String stroka = "";
		if (reg == 6) {
			stroka = "Balance";
			String r = "";
			for (ArrayList<Object> list : datas) {
				if (!r.equals(list.get(2).toString())) {
					stroka += "\n\n" + list.get(2).toString()+ "\n___________\n";
					r = list.get(2).toString();
				}
				stroka += list.get(0).toString() + "  "	+ list.get(1).toString() + " " + list.get(3).toString()	+ "\n";
			}
		}
		if (reg == 9) {
			double ost = 0;
			stroka = "Balance begin ";
			String strokaP = "\n Incoming \n";
			String strokaR = "\n Consumption \n";
			for (ArrayList<Object> list : datas) {
				if ((Double.parseDouble(list.get(1).toString()) == 0)
						&& (Double.parseDouble(list.get(2).toString()) == 0)) {
					stroka += list.get(3).toString() + "  "	+ list.get(4).toString() + "\n";
					ost += Double.parseDouble(list.get(3).toString());
				} else if (Double.parseDouble(list.get(2).toString()) > 0) {
					strokaR += list.get(0).toString() + "  "+ list.get(2).toString() + "  "	+ list.get(4).toString() + "\n";
					ost -= Double.parseDouble(list.get(2).toString());
				} else {
					strokaP += list.get(0).toString() + "  "+ list.get(1).toString() + "  "	+ list.get(4).toString() + "\n";
					ost += Double.parseDouble(list.get(1).toString());
				}
			}
			stroka = stroka + "___________" + strokaP + "___________" + strokaR	+ "___________\n Balance end " + ost;
		}
		if (reg == 10) {
			double ost = 0;
			stroka = "Balance begin \n";
			String strokaP = "\nIncoming \n";
			String strokaR = "\n Consumption \n";
			for (ArrayList<Object> list : datas) {
				if ((Double.parseDouble(list.get(1).toString()) == 0) && (Double.parseDouble(list.get(2).toString()) == 0)) {
					stroka += "\n" + list.get(5).toString() + "  "	+ list.get(3).toString() + "  "	+ list.get(4).toString();
					ost += Double.parseDouble(list.get(3).toString());
				} else if (Double.parseDouble(list.get(2).toString()) > 0) {
					strokaR += "\n" + list.get(5).toString() + "  "	+ list.get(2).toString() + "  "
							+ list.get(4).toString() + "  "	+ list.get(0).toString();
					ost -= Double.parseDouble(list.get(2).toString());
				} else {
					strokaP += "\n" + list.get(5).toString() + "  "	+ list.get(1).toString() + "  "
							+ list.get(4).toString() + "  "	+ list.get(0).toString();
					ost += Double.parseDouble(list.get(1).toString());
				}
			}
			stroka = stroka + "\n___________" + strokaP + "\n___________"+ strokaR + "\n___________\n Balance end " + ost;
		}
		
		 log.info("getText stroka="+stroka);
		return stroka;
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	@Override
	public ArrayList<String> getModelCombo(int reg)
			throws ClassNotFoundException {
		try {
			ArrayList<String> comb = new ArrayList<>();
			switch (reg) {
			case 1:
				ArrayList<Currency> listC = (new CurrencySql()).getCurrencys(conn);
				for (Currency item : listC) {
					String str = String.valueOf(item.getId());
					for (int i = str.length(); i < 5; i++) {str += " ";}
					str = str + "- " + item.getName();
					comb.add(str);
				}
				break;
			case 2:
				ArrayList<Account> listA = (new AccountSql()).getAccounts(conn);
				for (Account item : listA) {
					String str = String.valueOf(item.getId());
					for (int i = str.length(); i < 5; i++) {str += " ";}
					str = str + "- " + item.getName() + " / "
							+ item.getCurrency().getName();
					comb.add(str);
				}
				break;
			case 3:
				ArrayList<Category> listCt = (new CategorySql()).getCategorys(conn, "");
				for (Category item : listCt) {
					String str = String.valueOf(item.getId());
					for (int i = str.length(); i < 5; i++) {str += " ";	}
					if (item.getId() != item.getMainCategory().getId())
						str += "- " + item.getMainCategory().getName() + " / "
								+ item.getName();
					else
						str = str + "- " + item.getName();
					comb.add(str);
				}
				break;
			default://4
				comb.add("");
				ArrayList<Category> listCtm = (new CategorySql()).getCategorys(conn," where main_category_id is null or main_category_id=id ");
				for (Category item : listCtm) {
					String str = String.valueOf(item.getId());
					for (int i = str.length(); i < 5; i++) {str += " ";	}
					str = str + "- " + item.getName();
					comb.add(str);
				}
				break;
			}
			switch (reg) {
			case 1:
				combo = comb;
				break;
			case 2:
				comboAcc = comb;
				break;
			case 3:
				comboCat = comb;
				break;
			default:
				comboCatM = comb;
				break;
			}
			return comb;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String[] getColumns(int reg) {

		switch (reg) {
		case 1:
			return columnCurrency;
		case 2:
			return columnAccount;
		case 3:
			return columnCategory;
		case 5:
			return columnOperation;
		default:
			return columnVivOperation;
    	}
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public Statement getSt() {
		return st;
	}

	public void setSt(Statement st) {
		this.st = st;
	}

	public ResultSet getRs() {
		return rs;
	}

	public void setRs(ResultSet rs) {
		this.rs = rs;
	}

	public static String[] getComboType() {
		return comboType;
	}

	public static void setComboType(String[] comboType) {
		Model.comboType = comboType;
	}

}
