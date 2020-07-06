package main.homeCost;

import java.sql.SQLException;
import java.util.ArrayList;

import main.homeCost.MVVMDemoException;

import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import org.apache.log4j.Logger;
/**
 * IViewModel class is outer mantle of View class.
 * @author User
 *
 */
public class ViewModel implements IViewModel {
	private static final Logger log = Logger.getLogger(ViewModel.class);
	private IModel model;
	private IView view;

	@Override
	public boolean deleteItem(int reg, String text) throws MVVMDemoException,
			SQLException {
		return model.deleteItem(reg, text);
	}

	@Override
	public void addItem(int reg, ArrayList<String> paramSel)
			throws MVVMDemoException, SQLException {

		model.addItem(reg, paramSel);

	}

	@Override
	public void editItem(int reg, ArrayList<String> paramSel)
			throws MVVMDemoException, SQLException {

		model.editItem(reg, paramSel);

	}

	@Override
	public String[] getColumns(int reg) {
		String[] columns = model.getColumns(reg);
		return columns;

	}

	@Override
	public ArrayList<String> getlCombo(int reg) {
		try {
			ArrayList<String> combs = model.getModelCombo(reg);
			return combs;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void getlComboAll(int reg) throws ClassNotFoundException {
		new Thread(new Runnable() {
			ArrayList<String> cmb = null;

			@Override
			public void run() {
				Thread mainThread = Thread.currentThread();
				System.out.println("Текущий поток: " + mainThread.getName());
				System.out.println("model.getItems");
				try {
					System.out.println("model.getModelTable2(reg)");
					cmb = model.getModelCombo(reg);
				} catch (Exception e) {
					e.printStackTrace();
				}
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						try {
							view.showCombo(cmb, reg);
						} catch (MVVMDemoException e) {
							e.printStackTrace();
						}

					}

				});
			}

		}).start();

	}

	@Override
	public void getTablesV(int reg, ArrayList<String> paramSel)
			throws ClassNotFoundException {
		new Thread(new Runnable() {
			ArrayList<ArrayList<Object>> data = new ArrayList<ArrayList<Object>>();
			DefaultTableModel tModel=new DefaultTableModel();
			String text = "";

			@Override
			public void run() {
				try {
					if ((reg == 6) || (reg == 9) || (reg == 10))  // text panels
						text = model.getText(reg, paramSel);
					else if ((reg == 1) || (reg == 2) || (reg == 3)	|| (reg == 5)|| (reg == 7)) // tables 
						tModel= model.getModelTable(reg, paramSel);
					else
						data = model.getModelTableV(reg, paramSel); // charts
				} catch (Exception e) {
					e.printStackTrace();
				}
				log.info("getTablesV from model! reg="+reg);
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						try {
							if ((reg == 6) || (reg == 9) || (reg == 10))
								view.showItemsText(text, reg);
							else if ((reg == 8) || (reg == 11))
								view.showChart(data, reg);
							else
				     			view.showTables(tModel, reg);
							 log.info("getTablesV to view! reg="+reg);
						} catch (MVVMDemoException e) {
							e.printStackTrace();
						}
					}
				});
			}
		}).start();
	}

	public IModel getModel() {
		return model;
	}

	public void setModel(IModel model) {
		this.model = model;
	}

	public IView getView() {
		return view;
	}

	public void setView(IView view) {
		this.view = view;
	}

}
