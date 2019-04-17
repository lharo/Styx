package com.lharo.styx.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JList;
import java.awt.Color;
import javax.swing.border.SoftBevelBorder;

import com.amadeus.exceptions.ResponseException;
import com.amadeus.resources.Location;
import com.lharo.styx.amadeus.AmadeusAPI;
import com.lharo.styx.utils.ComboItem;
import com.lharo.styx.utils.CustomLocation;

import javax.swing.border.BevelBorder;

public class StyxApp {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private static AmadeusAPI api; 
	private static CustomLocation cusLocation;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StyxApp window = new StyxApp();
					api = new AmadeusAPI();
					cusLocation = new CustomLocation();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public StyxApp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 204));
		frame.setBounds(100, 100, 285, 434);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(10, 23, 248, 23);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(61, 88, 136, 20);
		frame.getContentPane().add(comboBox);

		
		JButton btnSearchFrom = new JButton("Set From");
		btnSearchFrom.setBackground(new Color(255, 255, 255));
		btnSearchFrom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object item = comboBox.getSelectedItem();
				String value = ((ComboItem)item).getValue();
				String key = ((ComboItem)item).getKey();				
				cusLocation.setFromFull(value);
				cusLocation.setFromValue(key);
				updateTextFieldWithFlight();		
			}
		});
		btnSearchFrom.setBounds(10, 114, 108, 23);
		frame.getContentPane().add(btnSearchFrom);
		
		JButton button = new JButton("Set To");
		button.setBackground(new Color(255, 255, 255));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object item = comboBox.getSelectedItem();
				String value = ((ComboItem)item).getValue();
				String key = ((ComboItem)item).getKey();				
				cusLocation.setToFull(value);
				cusLocation.setToValue(key);
				updateTextFieldWithFlight();		
			}
		});
		button.setBounds(150, 114, 108, 23);
		frame.getContentPane().add(button);
				
		JButton btnCheapestFlight = new JButton("Get Flights");
		btnCheapestFlight.setBackground(new Color(255, 255, 255));
		btnCheapestFlight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCheapestFlight.setBounds(61, 194, 136, 23);
		frame.getContentPane().add(btnCheapestFlight);
		
		JButton button_1 = new JButton("Get Best Date");
		button_1.setBackground(new Color(255, 255, 255));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button_1.setBounds(61, 228, 136, 23);
		frame.getContentPane().add(button_1);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setBounds(10, 148, 249, 39);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JList list = new JList();
		list.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		list.setBounds(10, 262, 249, 109);
		frame.getContentPane().add(list);
		
		JButton button_2 = new JButton("Search");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Location[] cities = api.getCities(textField.getText());
					comboBox.removeAllItems();
					for(Location city : cities) {
						comboBox.addItem(new ComboItem(city.getAddress().getCityName(), city.getAddress().getCityCode()));						
						//Object item = comboBox.getSelectedItem();
						//String value = ((ComboItem)item).getValue();
					}
				} catch (ResponseException e1) {
					e1.printStackTrace();
				}
			}
		});
		button_2.setBackground(new Color(255, 255, 255));
		button_2.setBounds(93, 57, 78, 23);
		frame.getContentPane().add(button_2);
	}

	protected void updateTextFieldWithFlight() {
		String flightFrom = "";//"Starting Location: " + cusLocation.getFromFull() + " Destination ";
		String flightTo = "";
		String flightInfo = "";
		
		if(cusLocation.getFromValue() != null) flightFrom = "Starting Location: " + cusLocation.getFromFull(); 	
		if(cusLocation.getToValue() != null) flightTo = "Destination: " + cusLocation.getToFull();

		if(!flightFrom.equals("")) flightInfo = flightFrom + " " + flightTo;
		else flightInfo = flightTo;
		textField_1.setText(flightInfo);
	}
}
