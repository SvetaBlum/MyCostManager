package main.homeCost;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import main.homeCost.Model;
import main.homeCost.IViewModel;
import main.homeCost.MVVMDemoException;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.ListSelectionListener;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
/**
 * View class including all descriptions of tabs and functionality.
 * @author User
 *
 */
public class View implements IView {
	JFrame frame;
	IViewModel viewmodel;
 
	DateFormat fmt = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.GERMANY);
	DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy"); 
	
	javax.swing.JButton jButtonAddSpr;
	javax.swing.JButton jButtonAddVV;
	javax.swing.JButton jButtonDeleteVV;
	javax.swing.JButton jButtonDeleteSpr;
	javax.swing.JButton jButtonEditSpr;
	javax.swing.JButton jButtonEditVV;
	javax.swing.JButton jButtonOkSpr;
	javax.swing.JButton jButtonOkViv;
	javax.swing.JButton jButtonOkVV;
	javax.swing.JButton jButtonUpdVV;
	javax.swing.JButton jButtonUpdSpr;
	javax.swing.JButton jButtonUpdCSpr;
	javax.swing.JButton jButtonOkAn;
	javax.swing.JButton jButtonOkAn2;
	javax.swing.JButton jButtonChartAn1;
	javax.swing.JButton jButtonChartAn2;
	javax.swing.JComboBox<String> jComboBoxTypeSpr;
	javax.swing.JComboBox<String> jComboBoxAccountVV;
	javax.swing.JComboBox<String> jComboBoxAccountViv;
	javax.swing.JComboBox<String> jComboBoxCurrencyAn;
	javax.swing.JComboBox<String> jComboBoxCategoryVV;
	javax.swing.JComboBox<String> jComboBoxCategoryViv;
	javax.swing.JComboBox<String> jComboBoxCategorySpr;
	javax.swing.JComboBox<String> jComboBoxCurrencySpr;
	javax.swing.JComboBox<String> jComboBoxCurrencyViv;
	javax.swing.JLabel jLabel1;
	javax.swing.JLabel jLabel10;
	javax.swing.JLabel jLabel11;
	javax.swing.JLabel jLabel12;
	javax.swing.JLabel jLabel13;
	javax.swing.JLabel jLabel14;
	javax.swing.JLabel jLabel15;
	javax.swing.JLabel jLabel16;
	javax.swing.JLabel jLabel17;
	javax.swing.JLabel jLabel20;
	javax.swing.JLabel jLabel21;
	javax.swing.JLabel jLabel22;
	javax.swing.JLabel jLabel23;
	javax.swing.JLabel jLabel2;
	javax.swing.JLabel jLabel3;
	javax.swing.JLabel jLabel4;
	javax.swing.JLabel jLabel5;
	javax.swing.JLabel jLabel6;
	javax.swing.JLabel jLabel7;
	javax.swing.JLabel jLabel8;
	javax.swing.JLabel jLabel9;
	javax.swing.JPanel jPanel1;
	javax.swing.JPanel jPanel2;
	javax.swing.JPanel jPanel3;
	javax.swing.JPanel jPanel4;
	javax.swing.JPanel jPanel5;
	javax.swing.JPanel jPanel6;
	javax.swing.JPanel jPanel7;
	javax.swing.JPanel jPanel8;
	javax.swing.JPanel jPanelAn1;
	javax.swing.JPanel jPanelAn2;
	javax.swing.JScrollPane jScrollPane1;
	javax.swing.JScrollPane jScrollPane2;
	javax.swing.JScrollPane jScrollPane3;
	javax.swing.JScrollPane jScrollPane4;
	javax.swing.JScrollPane jScrollPane5;
	javax.swing.JScrollPane jScrollPane7;
	javax.swing.JTabbedPane jTabbedPane1;
	javax.swing.JTable jTableSpr;
	javax.swing.JTable jTableVV;
	javax.swing.JTable jTableViv;
	javax.swing.JTextField jTextFieldAmountInVV;
	javax.swing.JTextField jTextFieldAmountOutVV;
	javax.swing.JTextField jTextFieldCommentVV;
	javax.swing.JTextField jTextFieldIdSpr;
	javax.swing.JTextField jTextFieldIdVV;
	javax.swing.JTextField jTextFieldNameSpr;
	javax.swing.JTextPane jTextPaneBalVV;
	javax.swing.JTextPane jTextPane1An;
	javax.swing.JTextPane jTextPane2An;
	ListSelectionModel selectVV;
	ListSelectionModel selectViv;
	ListSelectionModel selectSpr;
	/**
	 * Variables declaration
	 */
	UtilDateModel modelVV;
	JDatePanelImpl datePanelVV;
	JDatePickerImpl datePickerVV;
	
	UtilDateModel modelToVV;
	JDatePanelImpl datePanelToVV;
	JDatePickerImpl datePickerToVV;
	
	UtilDateModel modelFromVV;
	JDatePanelImpl datePanelFromVV;
	JDatePickerImpl datePickerFromVV;
	
	UtilDateModel modelToViv;
	JDatePanelImpl datePanelToViv;
	JDatePickerImpl datePickerToViv;
	
	UtilDateModel modelFromViv;
	JDatePanelImpl datePanelFromViv;
	JDatePickerImpl datePickerFromViv;

	UtilDateModel modelToAn;
	JDatePanelImpl datePanelToAn;
	JDatePickerImpl datePickerToAn;
	
	UtilDateModel modelFromAn;
	JDatePanelImpl datePanelFromAn;
	JDatePickerImpl datePickerFromAn;
	
	JFreeChart chart ;
	
	// End of variables declaration

	public IViewModel getViewmodel() {
		return viewmodel;
	}

	public void setViewmodel(IViewModel viewmodel) {
		this.viewmodel = viewmodel;
	}

	public View() {
		frame = new JFrame("Home");
		
		Date datU = new Date();
	    java.sql.Date date = new java.sql.Date(datU.getTime());

		jTableVV = new javax.swing.JTable();
		jTableVV.setCellSelectionEnabled(true);
		selectVV = jTableVV.getSelectionModel();
		selectVV.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		jTableViv = new javax.swing.JTable();
		
		jTableSpr = new javax.swing.JTable();
		jTableSpr.setCellSelectionEnabled(true);
		selectSpr = jTableSpr.getSelectionModel();
		selectSpr.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
	/**
	 *  Dates begin declaration
	 */
		modelVV = new UtilDateModel();
		modelToVV = new UtilDateModel();
		modelFromVV = new UtilDateModel();
		modelToViv = new UtilDateModel();
		modelFromViv = new UtilDateModel();
		modelToAn = new UtilDateModel();
		modelFromAn = new UtilDateModel();
		if (date != null) {
		     modelVV.setDate(Integer.parseInt((new SimpleDateFormat("yyyy")).format(date)),
		               Integer.parseInt((new SimpleDateFormat("MM")).format(date))-1,
		               Integer.parseInt((new SimpleDateFormat("dd")).format(date)));
		     modelFromVV.setDate(Integer.parseInt((new SimpleDateFormat("yyyy")).format(date)),
	                   Integer.parseInt((new SimpleDateFormat("MM")).format(date))-1,
	                   Integer.parseInt((new SimpleDateFormat("dd")).format(date)));
		     modelToVV.setDate(Integer.parseInt((new SimpleDateFormat("yyyy")).format(date)),
	                   Integer.parseInt((new SimpleDateFormat("MM")).format(date))-1,
	                   Integer.parseInt((new SimpleDateFormat("dd")).format(date)));
		     modelFromViv.setDate(Integer.parseInt((new SimpleDateFormat("yyyy")).format(date)),
	                   Integer.parseInt((new SimpleDateFormat("MM")).format(date))-1,
	                   Integer.parseInt((new SimpleDateFormat("dd")).format(date)));
		     modelToViv.setDate(Integer.parseInt((new SimpleDateFormat("yyyy")).format(date)),
	                   Integer.parseInt((new SimpleDateFormat("MM")).format(date))-1,
	                   Integer.parseInt((new SimpleDateFormat("dd")).format(date)));
		     modelFromAn.setDate(Integer.parseInt((new SimpleDateFormat("yyyy")).format(date)),
	                   Integer.parseInt((new SimpleDateFormat("MM")).format(date))-1,
	                   Integer.parseInt((new SimpleDateFormat("dd")).format(date)));
		     modelToAn.setDate(Integer.parseInt((new SimpleDateFormat("yyyy")).format(date)),
	                   Integer.parseInt((new SimpleDateFormat("MM")).format(date))-1,
	                   Integer.parseInt((new SimpleDateFormat("dd")).format(date)));
   	    }
		modelVV.setSelected(true);
		datePanelVV = new JDatePanelImpl(modelVV);
		datePickerVV = new JDatePickerImpl(datePanelVV);
		
		modelFromVV.setSelected(true);
		datePanelFromVV = new JDatePanelImpl(modelFromVV);
		datePickerFromVV = new JDatePickerImpl(datePanelFromVV);
		
		modelToVV.setSelected(true);
		datePanelToVV = new JDatePanelImpl(modelToVV);
		datePickerToVV = new JDatePickerImpl(datePanelToVV);
		
		modelFromViv.setSelected(true);
		datePanelFromViv = new JDatePanelImpl(modelFromViv);
		datePickerFromViv = new JDatePickerImpl(datePanelFromViv);
		
		modelToViv.setSelected(true);
		datePanelToViv = new JDatePanelImpl(modelToViv);
		datePickerToViv = new JDatePickerImpl(datePanelToViv);

		modelFromAn.setSelected(true);
		datePanelFromAn = new JDatePanelImpl(modelFromAn);
		datePickerFromAn = new JDatePickerImpl(datePanelFromAn);
		
		modelToAn.setSelected(true);
		datePanelToAn = new JDatePanelImpl(modelToAn);
		datePickerToAn = new JDatePickerImpl(datePanelToAn);
		
	// Dates end declaration
		
		jTabbedPane1 = new javax.swing.JTabbedPane();
		jPanel1 = new javax.swing.JPanel();
		jPanel5 = new javax.swing.JPanel();
		jScrollPane1 = new javax.swing.JScrollPane();

		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		jLabel4 = new javax.swing.JLabel();
		jLabel4.setText("Consumption");
		jLabel5 = new javax.swing.JLabel();
		jLabel6 = new javax.swing.JLabel();
		jTextFieldIdVV = new javax.swing.JTextField();
		jTextFieldIdVV.setEnabled(false);
		jTextFieldAmountInVV = new javax.swing.JTextField();
		jTextFieldAmountOutVV = new javax.swing.JTextField();
		jLabel7 = new javax.swing.JLabel();
		jComboBoxCategoryVV = new javax.swing.JComboBox<>();
		jComboBoxAccountVV = new javax.swing.JComboBox<>();
		jButtonAddVV = new javax.swing.JButton();
		jButtonEditVV = new javax.swing.JButton();
		jButtonDeleteVV = new javax.swing.JButton();
		jButtonUpdVV = new javax.swing.JButton();
		jTextFieldCommentVV = new javax.swing.JTextField();
		jScrollPane2 = new javax.swing.JScrollPane();
		jScrollPane5 = new javax.swing.JScrollPane();
		jScrollPane7 = new javax.swing.JScrollPane();
	    jTextPaneBalVV = new javax.swing.JTextPane();
	    jTextPaneBalVV.setFont(new java.awt.Font("Times New Roman", 3, 14));
		jLabel20 = new javax.swing.JLabel();
		jLabel21 = new javax.swing.JLabel();
		jButtonOkVV = new javax.swing.JButton();
		jPanel2 = new javax.swing.JPanel();
		jPanel6 = new javax.swing.JPanel();
		jLabel8 = new javax.swing.JLabel();
		jLabel9 = new javax.swing.JLabel();
		jLabel10 = new javax.swing.JLabel();
		jLabel11 = new javax.swing.JLabel();
		jComboBoxCurrencyAn = new javax.swing.JComboBox<>();
		jComboBoxAccountViv = new javax.swing.JComboBox<>();
		jComboBoxCurrencyViv = new javax.swing.JComboBox<>();
		jComboBoxCategoryViv = new javax.swing.JComboBox<>();
		jLabel12 = new javax.swing.JLabel();
		jButtonOkViv = new javax.swing.JButton();
		jPanel7 = new javax.swing.JPanel();
		jScrollPane3 = new javax.swing.JScrollPane();
		jPanel3 = new javax.swing.JPanel();
		jPanel3.setSize( 400 , 400 );
		jPanelAn1 = new javax.swing.JPanel();
		jPanelAn2 = new javax.swing.JPanel();
		jTextPane1An = new javax.swing.JTextPane();
		jTextPane1An.setFont(new java.awt.Font("Times New Roman", 3, 14));
		jTextPane2An = new javax.swing.JTextPane();
		jTextPane2An.setFont(new java.awt.Font("Times New Roman", 3, 14));
		jButtonOkAn = new javax.swing.JButton();
		jButtonOkAn2 = new javax.swing.JButton();
		jButtonChartAn1= new javax.swing.JButton();
		jButtonChartAn2= new javax.swing.JButton();
		jLabel22 = new javax.swing.JLabel();
		jLabel23 = new javax.swing.JLabel();
		jPanel4 = new javax.swing.JPanel();
		jPanel8 = new javax.swing.JPanel();
		jScrollPane4 = new javax.swing.JScrollPane();

		jLabel13 = new javax.swing.JLabel();
		jComboBoxTypeSpr = new javax.swing.JComboBox<>();
		jButtonOkSpr = new javax.swing.JButton();
		jLabel14 = new javax.swing.JLabel();
		jLabel15 = new javax.swing.JLabel();
		jLabel16 = new javax.swing.JLabel();
		jTextFieldIdSpr = new javax.swing.JTextField();
		jTextFieldIdSpr.setEditable(false);
		
		jTextFieldNameSpr = new javax.swing.JTextField();
		jTextFieldNameSpr.setEditable(false);
		jLabel17 = new javax.swing.JLabel();
		jComboBoxCurrencySpr = new javax.swing.JComboBox<>();
		jComboBoxCategorySpr = new javax.swing.JComboBox<>();
		jButtonAddSpr = new javax.swing.JButton();
		jButtonEditSpr = new javax.swing.JButton();
		jButtonDeleteSpr = new javax.swing.JButton();
		jButtonUpdSpr = new javax.swing.JButton();
		jButtonUpdCSpr = new javax.swing.JButton();
		
		jComboBoxCurrencySpr.setVisible(false);
		jLabel17.setVisible(false);
		jComboBoxCategorySpr.setVisible(false);
		jLabel16.setVisible(false);

		
		jLabel1.setText("Id");
		jLabel2.setText("Date");
		jLabel3.setText("Account");
		jLabel5.setText("Income");
		jLabel6.setText("Category");
		jLabel7.setText("Comment");
		jLabel8.setText("Date from ");
		jLabel9.setText("Account");
		jLabel10.setText("Currency");
		jLabel11.setText("Category");
		jLabel12.setText("Date to ");
		jLabel13.setText("Types of directories");
		jLabel14.setText("Id");
		jLabel15.setText("Name");
		jLabel16.setText("CategoryMain");
		jLabel17.setText("Currency");
		jLabel20.setText("Date from");
		jLabel21.setText("Date to");
		jLabel22.setText("Date from");
		jLabel23.setText("Date to");
		
		jButtonAddVV.setText("Add");
		jButtonEditVV.setText("Edit");
		jButtonDeleteVV.setText("Delete");
		jButtonOkVV.setText("OK");
		jButtonUpdVV.setText("Balance");

		jButtonOkViv.setText("OK");
		jButtonOkAn.setText("OK");
		jButtonOkAn2.setText("OK");
		jButtonOkAn2.setVisible(false);
		jButtonChartAn1.setText("Chart");
		jButtonChartAn2.setText("Chart");

		jButtonAddSpr.setText("Add");
		jButtonEditSpr.setText("Edit");
		jButtonDeleteSpr.setText("Delete");
		jButtonOkSpr.setText("Ok");
		jButtonUpdSpr.setText("Upd");
		jButtonUpdSpr.setVisible(false);
		jButtonUpdCSpr.setText("UpdCatM");
		jButtonUpdCSpr.setVisible(false);

		jTabbedPane1.addTab("Input", jPanel1);

		jPanel2.setBackground(new java.awt.Color(204, 255, 204));
		jPanel1.setBackground(new java.awt.Color(204, 255, 255));
		jPanel3.setBackground(new java.awt.Color(204, 204, 255));
		jPanelAn1.setBackground(new java.awt.Color(255, 255, 255));
		jPanelAn2.setBackground(new java.awt.Color(255, 255, 255));
/**
 *  Add empty item for combo box 
 */
		ArrayList<String> combo=new ArrayList<>();	  combo.add("");    combo.addAll(Model.combo);
		ArrayList<String> comboAcc=new ArrayList<>(); comboAcc.add(""); comboAcc.addAll(Model.comboAcc);
		ArrayList<String> comboCat=new ArrayList<>(); comboCat.add(""); comboCat.addAll(Model.comboCat);
	
/**
 *  Init combo box	
 */
		jComboBoxAccountViv.setModel(new javax.swing.DefaultComboBoxModel<>(
				comboAcc.toArray(new String[Model.comboAcc.size()])));		
		jComboBoxCurrencyViv.setModel(new javax.swing.DefaultComboBoxModel<>(
				Model.combo.toArray(new String[Model.combo.size()])));
		jComboBoxCategoryViv.setModel(new javax.swing.DefaultComboBoxModel<>(
				comboCat.toArray(new String[Model.comboCat.size()])));
		
		jComboBoxCategorySpr.setModel(new javax.swing.DefaultComboBoxModel<>(
				Model.comboCatM.toArray(new String[Model.comboCatM.size()])));

		jComboBoxCategoryVV.setModel(new javax.swing.DefaultComboBoxModel<>(
				Model.comboCat.toArray(new String[Model.comboCat.size()])));
	
		jComboBoxAccountVV.setModel(new javax.swing.DefaultComboBoxModel<>(
				Model.comboAcc.toArray(new String[Model.comboAcc.size()])));

		jComboBoxCurrencyAn.setModel(new javax.swing.DefaultComboBoxModel<>(
				Model.combo.toArray(new String[Model.combo.size()])));
	

		frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		
		jScrollPane1.setViewportView(jTableVV);
		

		javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(
				jPanel5);
		jPanel5.setLayout(jPanel5Layout);
		jPanel5Layout.setHorizontalGroup(jPanel5Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING,
				javax.swing.GroupLayout.DEFAULT_SIZE, 675, Short.MAX_VALUE));
		jPanel5Layout.setVerticalGroup(jPanel5Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jPanel5Layout
						.createSequentialGroup()
						.addComponent(jScrollPane1,
								javax.swing.GroupLayout.PREFERRED_SIZE, 392,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(0, 2, Short.MAX_VALUE)));

				
		jScrollPane2.setViewportView(jTextPaneBalVV);

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(
				jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		 jPanel1Layout.setHorizontalGroup(
		            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		            .addGroup(jPanel1Layout.createSequentialGroup()
		                .addContainerGap()
		                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                    .addGroup(jPanel1Layout.createSequentialGroup()
		                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
		                            .addGroup(jPanel1Layout.createSequentialGroup()
		                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                                    .addComponent(jLabel2)
		                                    .addGroup(jPanel1Layout.createSequentialGroup()
		                                        .addComponent(jLabel1)
		                                        .addGap(18, 18, 18)
		                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                                            .addComponent(datePickerVV, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
		                                            .addComponent(jTextFieldIdVV, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))))
		                                .addGap(41, 41, 41)
		                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
		                                    .addGroup(jPanel1Layout.createSequentialGroup()
		                                        .addComponent(jLabel5)
		                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		                                        .addComponent(jTextFieldAmountInVV, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
		                                    .addGroup(jPanel1Layout.createSequentialGroup()
		                                        .addComponent(jLabel4)
		                                        .addGap(18, 18, 18)
		                                        .addComponent(jTextFieldAmountOutVV, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
		                                .addGap(39, 39, 39)
		                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                                    .addComponent(jLabel6)
		                                    .addComponent(jLabel3))
		                                .addGap(27, 27, 27)
		                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
		                                    .addComponent(jComboBoxAccountVV, 0, 287, Short.MAX_VALUE)
		                                    .addComponent(jComboBoxCategoryVV, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
		                            .addGroup(jPanel1Layout.createSequentialGroup()
		                                .addGap(181, 181, 181)
		                                .addComponent(jLabel7)
		                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
		                                .addComponent(jTextFieldCommentVV)))
		                        .addGap(26, 26, 26)
		                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
		                            .addComponent(jButtonAddVV, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
		                            .addComponent(jButtonEditVV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		                            .addComponent(jButtonDeleteVV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
		                    .addGroup(jPanel1Layout.createSequentialGroup()
		                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                            .addGroup(jPanel1Layout.createSequentialGroup()
		                                .addGap(3, 3, 3)
		                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
		                            .addGroup(jPanel1Layout.createSequentialGroup()
		                                .addGap(20, 20, 20)
		                                .addComponent(jButtonUpdVV)))
		                        .addGap(18, 18, 18)
		                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
		                                .addComponent(jLabel20)
		                                .addGap(18, 18, 18)
		                                .addComponent(datePickerFromVV, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
		                                .addGap(50, 50, 50)
		                                .addComponent(jLabel21)
		                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
		                                .addComponent(datePickerToVV, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
		                                .addGap(40, 40, 40)
		                                .addComponent(jButtonOkVV)
		                                .addGap(115, 115, 115))
		                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
		                .addContainerGap(16, Short.MAX_VALUE))
		        );
		        jPanel1Layout.setVerticalGroup(
		            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		            .addGroup(jPanel1Layout.createSequentialGroup()
		                .addGap(13, 13, 13)
		                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
		                    .addComponent(jButtonOkVV)
		                    .addComponent(datePickerToVV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
		                    .addComponent(jLabel21)
		                    .addComponent(datePickerFromVV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
		                    .addComponent(jLabel20)
		                    .addComponent(jButtonUpdVV))
		                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
		                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
		                    .addComponent(jScrollPane2)
		                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		                .addGap(6, 6, 6)
		                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
		                    .addComponent(jTextFieldAmountOutVV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
		                    .addComponent(jTextFieldIdVV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
		                    .addComponent(jLabel1)
		                    .addComponent(jLabel4)
		                    .addComponent(jLabel6)
		                    .addComponent(jComboBoxCategoryVV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
		                    .addComponent(jButtonAddVV))
		                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
		                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
		                    .addGroup(jPanel1Layout.createSequentialGroup()
		                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
		                            .addComponent(datePickerVV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
		                            .addComponent(jLabel2)
		                            .addComponent(jLabel5)
		                            .addComponent(jTextFieldAmountInVV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
		                            .addComponent(jLabel3)
		                            .addComponent(jComboBoxAccountVV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
		                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
		                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
		                            .addComponent(jTextFieldCommentVV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
		                            .addComponent(jLabel7)
		                            .addComponent(jButtonDeleteVV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		                        .addContainerGap(12, Short.MAX_VALUE))
		                    .addGroup(jPanel1Layout.createSequentialGroup()
		                        .addComponent(jButtonEditVV)
		                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
		        );
		javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(
				jPanel6);
		jPanel6.setLayout(jPanel6Layout);
		jPanel6.setLayout(jPanel6Layout);
		jPanel6Layout.setHorizontalGroup(
	            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel6Layout.createSequentialGroup()
	                .addContainerGap()
	                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addComponent(jLabel8)
	                    .addComponent(jLabel9)
	                    .addComponent(jLabel10)
	                    .addComponent(jLabel11))
	                .addGap(38, 38, 38)
	                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addGroup(jPanel6Layout.createSequentialGroup()
	                        .addComponent(datePickerFromViv, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
	                        .addGap(27, 27, 27)
	                        .addComponent(jLabel12)
	                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                        .addComponent(datePickerToViv, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
	                    .addGroup(jPanel6Layout.createSequentialGroup()
	                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
	                            .addComponent(jComboBoxCategoryViv, javax.swing.GroupLayout.Alignment.LEADING, 0, 175, Short.MAX_VALUE)
	                            .addComponent(jComboBoxCurrencyViv, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                            .addComponent(jComboBoxAccountViv, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	                        .addGap(17, 17, 17)
	                        .addComponent(jButtonOkViv, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
	                .addContainerGap(480, Short.MAX_VALUE))
	        );
	        jPanel6Layout.setVerticalGroup(
	            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel6Layout.createSequentialGroup()
	                .addContainerGap()
	                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel8)
	                    .addComponent(datePickerFromViv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jLabel12)
	                    .addComponent(datePickerToViv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel9)
	                    .addComponent(jComboBoxAccountViv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel10)
	                    .addComponent(jComboBoxCurrencyViv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jButtonOkViv))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(jComboBoxCategoryViv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	        );

		jScrollPane3.setViewportView(jTableViv);

		javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(
				jPanel7);
		jPanel7.setLayout(jPanel7Layout);
		jPanel7Layout.setHorizontalGroup(jPanel7Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jPanel7Layout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(jScrollPane3,
								javax.swing.GroupLayout.DEFAULT_SIZE, 823,
								Short.MAX_VALUE).addContainerGap()));
		jPanel7Layout.setVerticalGroup(jPanel7Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				javax.swing.GroupLayout.Alignment.TRAILING,
				jPanel7Layout
						.createSequentialGroup()
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addComponent(jScrollPane3,
								javax.swing.GroupLayout.PREFERRED_SIZE, 349,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));

		javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(
				jPanel2);
		jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout
				.setHorizontalGroup(jPanel2Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel2Layout
										.createSequentialGroup()
										.addGap(32, 32, 32)
										.addGroup(
												jPanel2Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING,
																false)
														.addComponent(
																jPanel6,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																jPanel7,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE))
										.addContainerGap(27, Short.MAX_VALUE)));
		jPanel2Layout
				.setVerticalGroup(jPanel2Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel2Layout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(
												jPanel6,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(
												jPanel7,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addContainerGap(21, Short.MAX_VALUE)));

		jTabbedPane1.addTab("Conclusion", jPanel2);
		
		javax.swing.GroupLayout jPanelAn1Layout = new javax.swing.GroupLayout(
				jPanelAn1);
		
		
		 // jPanelAn1.add(jTextPane1An);
		
		
		jPanelAn1.setLayout(jPanelAn1Layout);
		jPanelAn1Layout.setHorizontalGroup(jPanelAn1Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 353,
				Short.MAX_VALUE));
		jPanelAn1Layout.setVerticalGroup(jPanelAn1Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 377,
				Short.MAX_VALUE));

		javax.swing.GroupLayout jPanelAn2Layout = new javax.swing.GroupLayout(
				jPanelAn2);
		
		
		 // jPanelAn2.add(jTextPane2An);
		 
	
		
		
		jPanelAn2.setLayout(jPanelAn2Layout);
		jPanelAn2Layout.setHorizontalGroup(jPanelAn2Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 350,
				Short.MAX_VALUE));
		jPanelAn2Layout.setVerticalGroup(jPanelAn2Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 0,
				Short.MAX_VALUE));
		
		javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(
				jPanel3);
		jPanel3.setLayout(jPanel3Layout);
		jScrollPane5.setViewportView(jTextPane1An);
		jScrollPane7.setViewportView(jTextPane2An);
		 jPanel3Layout.setHorizontalGroup(
		            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		            .addGroup(jPanel3Layout.createSequentialGroup()
		                .addGap(24, 24, 24)
		                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                    .addGroup(jPanel3Layout.createSequentialGroup()
		                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                            .addComponent(jButtonChartAn1)
		                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE))
		                        .addGap(55, 55, 55)
		                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE)
		                            .addComponent(jButtonChartAn2)))
		                    .addGroup(jPanel3Layout.createSequentialGroup()
		                        .addComponent(jLabel22)
		                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
		                        .addComponent(datePickerFromAn, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
		                        .addGap(18, 18, 18)
		                        .addComponent(jLabel23)
		                        .addGap(18, 18, 18)
		                        .addComponent(datePickerToAn, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
		                        .addGap(18, 18, 18)
		                        .addComponent(jComboBoxCurrencyAn, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
		                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
		                        .addComponent(jButtonOkAn)
		                        .addGap(18, 18, 18)
		                        .addComponent(jButtonOkAn2)))
		                .addContainerGap(16, Short.MAX_VALUE))
		        );
		        jPanel3Layout.setVerticalGroup(
		            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		            .addGroup(jPanel3Layout.createSequentialGroup()
		                .addGap(19, 19, 19)
		                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
		                    .addComponent(jLabel22)
		                    .addComponent(jLabel23)
		                    .addComponent(datePickerFromAn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
		                    .addComponent(datePickerToAn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
		                    .addComponent(jButtonOkAn)
		                    .addComponent(jComboBoxCurrencyAn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
		                    .addComponent(jButtonOkAn2))
		                .addGap(18, 18, 18)
		                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
		                    .addComponent(jScrollPane7))
		                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
		                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
		                    .addComponent(jButtonChartAn1)
		                    .addComponent(jButtonChartAn2))
		                .addGap(59, 59, 59))
		        );

		jTabbedPane1.addTab("Analytics", jPanel3);

		jPanel4.setBackground(new java.awt.Color(255, 255, 204));

		jScrollPane4.setViewportView(jTableSpr);

		javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(
				jPanel8);
		jPanel8.setLayout(jPanel8Layout);
		jPanel8Layout.setHorizontalGroup(jPanel8Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 640,
				Short.MAX_VALUE));
		jPanel8Layout.setVerticalGroup(jPanel8Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 324,
				Short.MAX_VALUE));

		jComboBoxTypeSpr.setModel(new javax.swing.DefaultComboBoxModel<>(
				Model.comboType));

		jButtonOkSpr.setText("Ok");

		jComboBoxCurrencySpr.setModel(new javax.swing.DefaultComboBoxModel<>(
				Model.combo.toArray(new String[Model.combo.size()])));
	    jComboBoxCategorySpr.setModel(new javax.swing.DefaultComboBoxModel<>(
	    		Model.comboCatM.toArray(new String[Model.comboCatM.size()])));


		javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(
				jPanel4);
		jPanel4.setLayout(jPanel4Layout);
		 jPanel4Layout.setHorizontalGroup(
		            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		            .addGroup(jPanel4Layout.createSequentialGroup()
		                .addGap(20, 20, 20)
		                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                    .addGroup(jPanel4Layout.createSequentialGroup()
		                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                            .addGroup(jPanel4Layout.createSequentialGroup()
		                                .addGap(2, 2, 2)
		                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
		                                    .addComponent(jLabel16)
		                                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)))
		                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
		                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)))
		                        .addGap(18, 18, 18)
		                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                            .addComponent(jComboBoxCurrencySpr, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
		                            .addComponent(jComboBoxCategorySpr, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
		                            .addComponent(jTextFieldNameSpr, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
		                            .addComponent(jTextFieldIdSpr, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
		                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 115, Short.MAX_VALUE)
		                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                            .addComponent(jButtonEditSpr, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
		                            .addComponent(jButtonAddSpr, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
		                            .addComponent(jButtonDeleteSpr, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
		                        .addGap(339, 339, 339))
		                    .addGroup(jPanel4Layout.createSequentialGroup()
		                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                            .addGroup(jPanel4Layout.createSequentialGroup()
		                                .addComponent(jLabel13)
		                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
		                                .addComponent(jComboBoxTypeSpr, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
		                                .addGap(18, 18, 18)
		                                .addComponent(jButtonOkSpr)
		                                .addGap(70, 70, 70)
		                                .addComponent(jButtonUpdSpr)
		                                .addGap(18, 18, 18)
		                                .addComponent(jButtonUpdCSpr))
		                            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
		                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
		        );
		        jPanel4Layout.setVerticalGroup(
		            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		            .addGroup(jPanel4Layout.createSequentialGroup()
		                .addContainerGap()
		                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
		                    .addComponent(jLabel13)
		                    .addComponent(jComboBoxTypeSpr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
		                    .addComponent(jButtonOkSpr)
		                    .addComponent(jButtonUpdSpr)
		                    .addComponent(jButtonUpdCSpr))
		                .addGap(31, 31, 31)
		                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
		                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
		                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                    .addGroup(jPanel4Layout.createSequentialGroup()
		                        .addComponent(jButtonAddSpr)
		                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
		                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                            .addComponent(jButtonEditSpr)
		                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
		                                .addComponent(jLabel15)
		                                .addComponent(jTextFieldNameSpr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
		                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
		                        .addComponent(jTextFieldIdSpr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
		                        .addComponent(jLabel14)))
		                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
		                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		                    .addComponent(jButtonDeleteSpr)
		                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
		                        .addComponent(jLabel16)
		                        .addComponent(jComboBoxCategorySpr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
		                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
		                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
		                    .addComponent(jComboBoxCurrencySpr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
		                    .addComponent(jLabel17))
		                .addContainerGap(35, Short.MAX_VALUE))
		        );

		jTabbedPane1.addTab("Directory", jPanel4);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				frame.getContentPane());
		frame.getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				javax.swing.GroupLayout.Alignment.TRAILING,
				layout.createSequentialGroup()
						.addContainerGap(18, Short.MAX_VALUE)
						.addComponent(jTabbedPane1,
								javax.swing.GroupLayout.PREFERRED_SIZE, 907,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(20, 20, 20)));
		layout.setVerticalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				javax.swing.GroupLayout.Alignment.TRAILING,
				layout.createSequentialGroup()
						.addContainerGap(81, Short.MAX_VALUE)
						.addComponent(jTabbedPane1,
								javax.swing.GroupLayout.PREFERRED_SIZE, 565,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));

		jButtonOkVV.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					ArrayList<String> paramSel=new ArrayList<>();
					paramSel.add(dateFormat.format((java.util.Date) datePickerFromVV.getModel().getValue()));
					paramSel.add(dateFormat.format((java.util.Date) datePickerToVV.getModel().getValue()));
					jTextFieldIdVV.setText("");
					jTextFieldAmountInVV.setText("");
					jTextFieldAmountOutVV.setText("");
					jTextFieldCommentVV.setText("");
					viewmodel.getTablesV(5, paramSel);
					//jButtonUpdVV.doClick();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
       
		selectVV.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {

				int[] selectedRow = jTableVV.getSelectedRows();

				for (int i = 0; i < selectedRow.length; i++) {
					jTextFieldAmountInVV.setText(jTableVV.getValueAt(selectedRow[i], 2).toString());
					jTextFieldAmountOutVV.setText(jTableVV.getValueAt(selectedRow[i], 3).toString());
					jTextFieldCommentVV.setText((String) jTableVV.getValueAt(selectedRow[i], 6));
					try {
						java.util.Date d1 = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.GERMANY).parse(jTableVV.getValueAt(selectedRow[i], 1).toString().trim());
						 java.sql.Date date = new java.sql.Date(d1.getTime());
							if (date != null) {
							     modelVV.setDate(Integer.parseInt((new SimpleDateFormat("yyyy")).format(date)),
							                   Integer.parseInt((new SimpleDateFormat("MM")).format(date))-1,
							                   Integer.parseInt((new SimpleDateFormat("dd")).format(date)));
					   	    }
							modelVV.setSelected(true);
			        } catch (ParseException ex) {}
						
					jTextFieldIdVV.setText(jTableVV.getValueAt(selectedRow[i],0).toString());
					String id = jTableVV.getValueAt(selectedRow[i], 7).toString().trim();
					for (int j = 0; j < jComboBoxAccountVV.getItemCount(); j++) {
						if (id.trim().equals(jComboBoxAccountVV.getModel().getElementAt(j).substring(0, 5).trim())) {
							jComboBoxAccountVV.setSelectedIndex(j);
							break;
						}
					}
					
					id = jTableVV.getValueAt(selectedRow[i], 8).toString().trim();
					for (int j = 0; j < jComboBoxCategoryVV.getItemCount(); j++) {
						if (id.equals(jComboBoxCategoryVV.getModel().getElementAt(j).substring(0, 5).trim())) {
							jComboBoxCategoryVV.setSelectedIndex(j);
							break;
						}
					}
				}
			}

		});
        
		jButtonAddVV.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
          			 
					ArrayList<String> paramSel=new ArrayList<>();
					paramSel.add("0");
					paramSel.add(dateFormat.format((java.util.Date) datePickerVV.getModel().getValue()));
					paramSel.add(jTextFieldAmountInVV.getText().trim());
					paramSel.add(jTextFieldAmountOutVV.getText().trim());
					paramSel.add((String) jComboBoxCategoryVV.getSelectedItem());
					paramSel.add((String) jComboBoxAccountVV.getSelectedItem());
					paramSel.add(jTextFieldCommentVV.getText().trim());
					
					viewmodel.addItem(5,paramSel);
					jButtonOkVV.doClick();
					jButtonUpdVV.doClick();
				} catch (MVVMDemoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
        
		jButtonEditVV.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					 
				 if (jTextFieldIdVV.getText().trim().length()>0){
      				ArrayList<String> paramSel=new ArrayList<>();
					paramSel.add(jTextFieldIdVV.getText().trim());
					paramSel.add(dateFormat.format((java.util.Date) datePickerVV.getModel().getValue()));
					paramSel.add(jTextFieldAmountInVV.getText().trim());
					paramSel.add(jTextFieldAmountOutVV.getText().trim());
					paramSel.add((String) jComboBoxCategoryVV.getSelectedItem());
					paramSel.add((String) jComboBoxAccountVV.getSelectedItem());
					paramSel.add(jTextFieldCommentVV.getText().trim());
						
					viewmodel.editItem(5,paramSel);
					jButtonOkVV.doClick();
					jButtonUpdVV.doClick();
				 }
				} catch (MVVMDemoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
      
		jButtonDeleteVV.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
				 if (jTextFieldIdVV.getText().trim().length()>0){
					viewmodel.deleteItem(5,jTextFieldIdVV.getText().trim());
					jButtonOkVV.doClick();
					jButtonUpdVV.doClick();
				 }
				} catch (MVVMDemoException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
     
		jButtonUpdVV.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					viewmodel.getTablesV(6, null);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
       
		jButtonOkViv.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					ArrayList<String> paramSel=new ArrayList<>();
					paramSel.add(dateFormat.format((java.util.Date) datePickerFromViv.getModel().getValue()));
					paramSel.add(dateFormat.format((java.util.Date) datePickerToViv.getModel().getValue()));

					paramSel.add((String) jComboBoxAccountViv.getSelectedItem());
					paramSel.add((String) jComboBoxCategoryViv.getSelectedItem());
					paramSel.add((String) jComboBoxCurrencyViv.getSelectedItem());
	
					viewmodel.getTablesV(7, paramSel);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		jButtonOkAn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					ArrayList<String> paramSel=new ArrayList<>();
					paramSel.add(dateFormat.format((java.util.Date) datePickerFromAn.getModel().getValue()));
					paramSel.add(dateFormat.format((java.util.Date) datePickerToAn.getModel().getValue()));

					paramSel.add((String) jComboBoxCurrencyAn.getSelectedItem());
					viewmodel.getTablesV(9, paramSel);
					jButtonOkAn2.doClick();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		jButtonChartAn1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					ArrayList<String> paramSel=new ArrayList<>();
					paramSel.add(dateFormat.format((java.util.Date) datePickerFromAn.getModel().getValue()));
					paramSel.add(dateFormat.format((java.util.Date) datePickerToAn.getModel().getValue()));

					paramSel.add((String) jComboBoxCurrencyAn.getSelectedItem());
					viewmodel.getTablesV(8, paramSel);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		jButtonOkAn2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					ArrayList<String> paramSel=new ArrayList<>();
					paramSel.add(dateFormat.format((java.util.Date) datePickerFromAn.getModel().getValue()));
					paramSel.add(dateFormat.format((java.util.Date) datePickerToAn.getModel().getValue()));

					paramSel.add((String) jComboBoxCurrencyAn.getSelectedItem());
					viewmodel.getTablesV(10, paramSel);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		jButtonChartAn2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					ArrayList<String> paramSel=new ArrayList<>();
					paramSel.add(dateFormat.format((java.util.Date) datePickerFromAn.getModel().getValue()));
					paramSel.add(dateFormat.format((java.util.Date) datePickerToAn.getModel().getValue()));

					paramSel.add((String) jComboBoxCurrencyAn.getSelectedItem());
					viewmodel.getTablesV(11, paramSel);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		jButtonOkSpr.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					jTextFieldNameSpr.setEditable(true);
					jTextFieldNameSpr.setText("");
					jTextFieldIdSpr.setText("");
					jComboBoxCurrencySpr.setVisible(false);
					jLabel17.setVisible(false);
					jComboBoxCategorySpr.setVisible(false);
					jLabel16.setVisible(false);
					viewmodel.getTablesV(1 + jComboBoxTypeSpr.getSelectedIndex(), null);
					
					if (jComboBoxTypeSpr.getSelectedIndex() == 1) {
						jComboBoxCurrencySpr.setVisible(true);
						jLabel17.setVisible(true);

					}
					if (jComboBoxTypeSpr.getSelectedIndex() == 2) {
						jComboBoxCategorySpr.setVisible(true);
						jLabel16.setVisible(true);

					}

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		selectSpr.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {

				int[] selectedRow = jTableSpr.getSelectedRows();

				for (int i = 0; i < selectedRow.length; i++) {
					jTextFieldIdSpr.setText(jTableSpr.getValueAt(selectedRow[i], 0).toString());
					if (jComboBoxTypeSpr.getSelectedIndex()==2){
						jTextFieldNameSpr.setText(jTableSpr.getValueAt(selectedRow[i], 2).toString());
						if (jTableSpr.getValueAt(selectedRow[i], 2).toString().trim().length()==0)
							jTextFieldNameSpr.setText(jTableSpr.getValueAt(selectedRow[i], 1).toString());
					}
					else
					    jTextFieldNameSpr.setText(jTableSpr.getValueAt(selectedRow[i], 1).toString());

					if (jComboBoxTypeSpr.getSelectedIndex() == 1) {
						String id = jTableSpr.getValueAt(selectedRow[i], 3).toString();
						for (int j = 1; j < jComboBoxCurrencySpr.getItemCount(); j++) {
							if (id.trim().equals(jComboBoxCurrencySpr.getModel().getElementAt(j).substring(0, 5).trim())) {
								jComboBoxCurrencySpr.setSelectedIndex(j);
								break;
							}
						}
					}
					
					if ((jComboBoxTypeSpr.getSelectedIndex() == 2) 
							&& (jTableSpr.getValueAt(selectedRow[i], 2).toString().trim().length()>0)) {
						String id = jTableSpr.getValueAt(selectedRow[i], 3).toString();
						for (int j = 1; j < jComboBoxCategorySpr.getItemCount(); j++) {
							if (id.trim().equals(jComboBoxCategorySpr.getModel().getElementAt(j).substring(0, 5).trim())) {
								jComboBoxCategorySpr.setSelectedIndex(j);
								break;
							}
						}
					}
				}
			}

		});

		jButtonAddSpr.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					ArrayList<String> paramSel=new ArrayList<>();
					paramSel.add("0");
					paramSel.add(jTextFieldNameSpr.getText().trim());
					
					if (jComboBoxTypeSpr.getSelectedIndex()==1)
						paramSel.add(((String) jComboBoxCurrencySpr.getSelectedItem()).trim());
					
					if (jComboBoxTypeSpr.getSelectedIndex()==2)
						paramSel.add(((String) jComboBoxCategorySpr.getSelectedItem()).trim());
				
					viewmodel.addItem(1 + jComboBoxTypeSpr.getSelectedIndex(), paramSel);
					viewmodel.getTablesV(1 + jComboBoxTypeSpr.getSelectedIndex(), null);
					jButtonUpdSpr.doClick();
				} catch (MVVMDemoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		jButtonEditSpr.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
				 if (jTextFieldIdSpr.getText().trim().length()>0){
					ArrayList<String> paramSel=new ArrayList<>();
					paramSel.add(jTextFieldIdSpr.getText().trim());
					paramSel.add(jTextFieldNameSpr.getText().trim());
						
					if (jComboBoxTypeSpr.getSelectedIndex()==1)
						paramSel.add(((String) jComboBoxCurrencySpr.getSelectedItem()).trim());
						
					if (jComboBoxTypeSpr.getSelectedIndex()==2)
						paramSel.add(((String) jComboBoxCategorySpr.getSelectedItem()).trim());
					
					viewmodel.editItem(1 + jComboBoxTypeSpr.getSelectedIndex(),paramSel);
					viewmodel.getTablesV(1 + jComboBoxTypeSpr.getSelectedIndex(), null);
					jButtonUpdSpr.doClick();
				 }	
				} catch (MVVMDemoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		jButtonDeleteSpr.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
				 if (jTextFieldIdSpr.getText().trim().length()>0){
					viewmodel.deleteItem(1 + jComboBoxTypeSpr.getSelectedIndex(),jTextFieldIdSpr.getText().trim());
					viewmodel.getTablesV(1 + jComboBoxTypeSpr.getSelectedIndex(), null);
					jButtonUpdSpr.doClick();
				 }
				} catch (MVVMDemoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		jButtonUpdSpr.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					viewmodel.getlComboAll(jComboBoxTypeSpr.getSelectedIndex() + 1);
					if (jComboBoxTypeSpr.getSelectedIndex()==2)
						jButtonUpdCSpr.doClick();	
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		jButtonUpdCSpr.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					viewmodel.getlComboAll(4);	
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

	}

	public void start() {

		frame.getContentPane().add(jTabbedPane1);
		frame.pack();
		frame.setVisible(true);
		
	}// </editor-fold>
	@Override
	public void showTables(DefaultTableModel model, int reg) throws MVVMDemoException {
        if (reg==7){
            jTableViv.setModel(model);
     	    jTableViv.getColumnModel().getColumn(0).setMinWidth(0);
     	   	jTableViv.getColumnModel().getColumn(0).setMaxWidth(0);
     		jTableViv.getColumnModel().getColumn(0).setWidth(0);
        } else
		if (reg == 5) {
			jTableVV.setModel(model);
			jTableVV.getColumnModel().getColumn(8).setMinWidth(0);
			jTableVV.getColumnModel().getColumn(8).setMaxWidth(0);
			jTableVV.getColumnModel().getColumn(8).setWidth(0);
			jTableVV.getColumnModel().getColumn(7).setMinWidth(0);
			jTableVV.getColumnModel().getColumn(7).setMaxWidth(0);
			jTableVV.getColumnModel().getColumn(7).setWidth(0);
			jTableVV.getColumnModel().getColumn(0).setMinWidth(30);
			jTableVV.getColumnModel().getColumn(0).setMaxWidth(30);
			jTableVV.getColumnModel().getColumn(0).setWidth(30);
			jTableVV.getColumnModel().getColumn(1).setMinWidth(70);
			jTableVV.getColumnModel().getColumn(1).setMaxWidth(70);
			jTableVV.getColumnModel().getColumn(1).setWidth(70);
			jTableVV.getColumnModel().getColumn(2).setMinWidth(70);
			jTableVV.getColumnModel().getColumn(2).setMaxWidth(70);
			jTableVV.getColumnModel().getColumn(2).setWidth(70);
			jTableVV.getColumnModel().getColumn(3).setMinWidth(70);
			jTableVV.getColumnModel().getColumn(3).setMaxWidth(70);
			jTableVV.getColumnModel().getColumn(3).setWidth(70);
			jTableVV.getColumnModel().getColumn(6).setMinWidth(50);
			jTableVV.getColumnModel().getColumn(6).setMaxWidth(50);
			jTableVV.getColumnModel().getColumn(6).setWidth(50);
		} else if (reg <= 3) {
			jTableSpr.setModel(model);
			if (reg > 1) {
				jTableSpr.getColumnModel().getColumn(3).setMinWidth(0);
				jTableSpr.getColumnModel().getColumn(3).setMaxWidth(0);
				jTableSpr.getColumnModel().getColumn(3).setWidth(0);
			}
		}
		model.fireTableDataChanged();
	}

	@Override
	public void showTables(ArrayList<ArrayList<Object>> datas, String columns[], int reg) throws MVVMDemoException {

		DefaultTableModel model = new DefaultTableModel();
		model.setColumnIdentifiers(columns);
		
		for (ArrayList<Object> list : datas) {
			model.addRow(list.toArray());
		}

	    jTableViv.setModel(model);
	    jTableViv.getColumnModel().getColumn(0).setMinWidth(0);
	   	jTableViv.getColumnModel().getColumn(0).setMaxWidth(0);
		jTableViv.getColumnModel().getColumn(0).setWidth(0);
	
		model.fireTableDataChanged();
	}

	@Override
	 public void showCombo(ArrayList<String> items, int reg) throws MVVMDemoException {
		
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		DefaultComboBoxModel<String> model1 = new DefaultComboBoxModel<String>();
		model1.addElement("");
		for (int i = 0; i < items.size(); i++) {
			model.addElement(items.get(i));
			model1.addElement(items.get(i));
		}
		
		switch (reg) {
		case 1:
			jComboBoxCurrencySpr.setModel(model);
			jComboBoxCurrencyAn.setModel(model);
			jComboBoxCurrencyViv.setModel(model1);
			break;
		case 2:
			jComboBoxAccountViv.setModel(model1);
			jComboBoxAccountVV.setModel(model);
    		break;
		case 4:
			jComboBoxCategorySpr.setModel(model);
    		break;
		default:
			jComboBoxCategoryVV.setModel(model);
			jComboBoxCategoryViv.setModel(model1);
			break;
		}
		
	}
	@Override
	 public void showChart(ArrayList<ArrayList<Object>> datas, int reg) throws MVVMDemoException {
		if (reg==8)
		{
		 JFrame frame1=new JFrame("by currency");
		 frame1.setSize(800, 500);
		 frame1.setVisible(true);
		 DefaultPieDataset dataset = new DefaultPieDataset();

		 for (ArrayList<Object> list : datas) {
        	 dataset.setValue( list.get(0).toString(), Double.parseDouble(list.get(2).toString()));  
		 }
		 JFreeChart chart = ChartFactory.createPieChart("Consumption",dataset,true,true,false);
		
		 ChartPanel cp  = new  ChartPanel( chart );
		 frame1.getContentPane().add(cp);
		}
		if (reg==11)
		{
			JFrame frame2=new JFrame("by account");
			frame2.setSize(800, 500);
			frame2.setVisible(true);
			final DefaultCategoryDataset dataset =  new DefaultCategoryDataset( );  	
			for (ArrayList<Object> list : datas) {
				 dataset.addValue( Double.parseDouble(list.get(2).toString()) ,list.get(0).toString() , list.get(5).toString()); 
			}
			JFreeChart barChart = ChartFactory.createBarChart(
			         "Consumption by account" ,           
			         "Category",            
			         "Amount",            
			         dataset,          
			         PlotOrientation.VERTICAL,           
			         true, true, false);
			ChartPanel cp2  = new  ChartPanel( barChart );
			frame2.getContentPane().add(cp2);
	  }
	}
	
	@Override
	public void showItemsText(String text,int reg) throws MVVMDemoException {
		if (reg==6)		jTextPaneBalVV.setText(text);
		if (reg==9)		jTextPane1An.setText(text);
		if (reg==10)	jTextPane2An.setText(text);
	}
	
	@Override
	public void setMessage(String text) throws MVVMDemoException {
		//jLabel18.setText(text);
	}

	@Override
	public void setViewModel(IViewModel ob) {
		setViewmodel(ob);
	}

}
