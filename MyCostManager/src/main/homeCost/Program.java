package main.homeCost;

import main.homeCost.IModel;
import main.homeCost.IView;
import main.homeCost.IViewModel;
import main.homeCost.MVVMDemoException;
import main.homeCost.Model;
import main.homeCost.View;
import main.homeCost.ViewModel;

import javax.swing.SwingUtilities;
/**
 * Create tables in first running
 * @author User
 *
 */
public class Program {
	public static void main(String[] args) throws MVVMDemoException {

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName());
				
				IModel m = new Model();
				try {
					m.connection();
			//		m.createTableDB();  //  run once - create table in DB 
					m.getModelCombo(1);
					m.getModelCombo(2);
					m.getModelCombo(3);
					m.getModelCombo(4);
					
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				IView v = new View();
				v.start();
				
				IViewModel vm = new ViewModel();
				v.setViewModel(vm);
				vm.setModel(m);
				vm.setView(v);
			}
		});

	}

}
