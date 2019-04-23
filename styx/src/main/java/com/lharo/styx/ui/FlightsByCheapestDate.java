package com.lharo.styx.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JScrollPane;

import java.awt.Color;
import javax.swing.border.SoftBevelBorder;

import com.amadeus.exceptions.ResponseException;
import com.amadeus.resources.FlightDate;
import com.amadeus.resources.HotelOffer;
import com.amadeus.resources.Location;
import com.amadeus.resources.Location.GeoCode;
import com.lharo.styx.amadeus.AmadeusAPI;
import com.lharo.styx.charts.LineChart_AWT;
import com.lharo.styx.utils.ComboItem;
import com.lharo.styx.utils.CustomLocation;

import javax.swing.border.BevelBorder;

public class FlightsByCheapestDate {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private static AmadeusAPI api; 
	private static CustomLocation cusLocation;
	private FlightDate[] flightdates;
	private GeoCode geoCodeSelected;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FlightsByCheapestDate window = new FlightsByCheapestDate();
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
	public FlightsByCheapestDate() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Flights By Cheapest Date");
		frame.getContentPane().setBackground(new Color(255, 255, 204));
		frame.setBounds(100, 100, 397, 503);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(10, 23, 361, 23);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(111, 88, 136, 20);
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
				cusLocation.setFromGeo(geoCodeSelected);
				updateTextFieldWithFlight();		
			}
		});
		btnSearchFrom.setBounds(52, 114, 108, 23);
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
				cusLocation.setToGeo(geoCodeSelected);
				updateTextFieldWithFlight();		
			}
		});
		button.setBounds(200, 114, 108, 23);
		frame.getContentPane().add(button);
	
		DefaultListModel listModel;
		listModel = new DefaultListModel();
		JScrollPane scrollPane = new JScrollPane();

		JList list = new JList(listModel);
		scrollPane.setViewportView(list);
		scrollPane.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		scrollPane.setBounds(10, 228, 361, 196);
		frame.getContentPane().add(scrollPane);
				
		JButton btnCheapestFlight = new JButton("Get Flights");
		btnCheapestFlight.setBackground(new Color(255, 255, 255));
		btnCheapestFlight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					flightdates = api.getFlightDates(cusLocation.getFromFull(), cusLocation.getToFull());
					for(FlightDate date : flightdates) {
						System.out.println("Precio " + date.getPrice() + "|" + "Tipo " + date.getType() + "|Salida" + date.getDepartureDate() + "|Retorno" + date.getReturnDate());
						listModel.addElement("Precio " + date.getPrice().getTotal() + 
								" |Salida: " + date.getDepartureDate().getDay() + "/" + date.getDepartureDate().getMonth() + "|Retorno:" + date.getReturnDate().getDay() + "/" + date.getReturnDate().getMonth());
					}
				} catch (ResponseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnCheapestFlight.setBounds(83, 194, 200, 23);
		frame.getContentPane().add(btnCheapestFlight);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setBounds(52, 144, 256, 39);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		
		JButton button_2 = new JButton("Search");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Location[] cities = api.getCities(textField.getText());
					comboBox.removeAllItems();
					for(Location city : cities) {
						geoCodeSelected = city.getGeoCode();
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
		button_2.setBounds(146, 54, 78, 23);
		frame.getContentPane().add(button_2);
		
		JButton btnFidHotels = new JButton("Find Hotels");
		btnFidHotels.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HotelOffersCity frameHotel;
				try {
/*					HotelOffer[] hotel = api.getHotelOffersCity(cusLocation.getToFull());
					for(HotelOffer h:hotel) {
						System.out.println(h.getHotel().getName());
					}*/

					frameHotel = new HotelOffersCity(cusLocation.getToValue(), cusLocation.getToFull());
					frameHotel.main(cusLocation.getToValue(), cusLocation.getToFull());
				} catch (ResponseException e1) {
					// TODO Auto-generated					
					e1.printStackTrace();
				}
			}
		});
		btnFidHotels.setBounds(245, 435, 126, 23);
		frame.getContentPane().add(btnFidHotels);
		
		JButton btnGraphs = new JButton("Graphs");
		btnGraphs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LineChart_AWT lcAWT = new LineChart_AWT("Styx", cusLocation.getFromFull() + " to " + cusLocation.getToFull(), flightdates);
				lcAWT.showGraph("Styx", cusLocation.getFromFull() + " to " + cusLocation.getToFull(), flightdates);
			}
		});
		btnGraphs.setBounds(10, 435, 102, 23);
		frame.getContentPane().add(btnGraphs);
		
		JButton btnMap = new JButton("Map");
		btnMap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cusLocation.getFromGeo();
				cusLocation.getToGeo();
			}
		});
		btnMap.setBounds(135, 435, 89, 23);
		frame.getContentPane().add(btnMap);
		
	}

	protected void updateTextFieldWithFlight() {
		String flightFrom = "";
		String flightTo = "";
		String flightInfo = "";
		
		if(cusLocation.getFromValue() != null) flightFrom = "Starting Location: " + cusLocation.getFromFull(); 	
		if(cusLocation.getToValue() != null) flightTo = "Destination: " + cusLocation.getToFull();

		if(!flightFrom.equals("")) flightInfo = flightFrom + " " + flightTo;
		else flightInfo = flightTo;
		textField_1.setText(flightInfo);
	}
}
