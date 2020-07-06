package main.homeCost;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import main.homeCost.MVVMDemoException;

public interface IModel {
	 public void connection() throws ClassNotFoundException ;
	 public void createTableDB() throws ClassNotFoundException ; 
	
	 public boolean addItem(int reg, ArrayList<String> paramSel) throws MVVMDemoException, SQLException;
	 public boolean editItem(int reg, ArrayList<String> paramSel) throws MVVMDemoException, SQLException;
	 public boolean deleteItem(int reg,String  text)  throws MVVMDemoException, SQLException ;
	 
	 public DefaultTableModel getModelTable(int reg, ArrayList<String> paramSel) throws ClassNotFoundException ;
	 
	// public ArrayList<ArrayList<Object>> getModelTableM(int reg, ArrayList<String> paramSel) throws ClassNotFoundException ;
	 public ArrayList<ArrayList<Object>> getModelTableV(int reg, ArrayList<String> paramSel) throws ClassNotFoundException ;
	 public String  getText(int reg, ArrayList<String> paramSel) throws ClassNotFoundException ;
	 public ArrayList<String> getModelCombo(int reg) throws ClassNotFoundException ;
	 public String[] getColumns(int reg) ;
	 
}