package main.homeCost;

import java.sql.SQLException;
import java.util.ArrayList;

import main.homeCost.IModel;
import main.homeCost.IView;
import main.homeCost.MVVMDemoException;

public interface IViewModel {

	public void addItem(int reg, ArrayList<String> paramSel) throws MVVMDemoException, SQLException ;
	public void editItem(int reg, ArrayList<String> paramSel) throws MVVMDemoException, SQLException;
	public boolean deleteItem(int reg,String  text)  throws MVVMDemoException, SQLException ;

	public void setModel(IModel m);
	public void setView(IView v);
	
	public void getTablesV(int reg, ArrayList<String> paramSel) throws ClassNotFoundException;
	public String[] getColumns(int reg) ;

	public ArrayList<String> getlCombo(int reg);
	public void getlComboAll(int reg) throws ClassNotFoundException  ;

}

