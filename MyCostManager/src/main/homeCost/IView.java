package main.homeCost;


import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import main.homeCost.IViewModel;
import main.homeCost.MVVMDemoException;


public interface IView {
	public void setMessage(String  text) throws MVVMDemoException;
	
    public void showCombo(ArrayList<String> items, int reg) throws MVVMDemoException ;
    public void showChart(ArrayList<ArrayList<Object>> datas, int reg) throws MVVMDemoException ;		
    public void showItemsText(String text, int reg) throws MVVMDemoException;
	public void showTables(ArrayList<ArrayList<Object>> datas,String columns[],int reg) throws MVVMDemoException;
	public void showTables(DefaultTableModel datas,int reg) throws MVVMDemoException;
	public void setViewModel(IViewModel ob);
	public void start();
	
	
	
}