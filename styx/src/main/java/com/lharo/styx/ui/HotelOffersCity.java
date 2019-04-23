package com.lharo.styx.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;

import com.amadeus.Amadeus;
import com.amadeus.Params;
import com.amadeus.exceptions.ResponseException;
import com.amadeus.resources.HotelOffer;
import com.amadeus.resources.HotelOffer.Offer;
import com.lharo.styx.amadeus.AmadeusAPI;
import com.lharo.styx.images.TextOverlay;
import com.lharo.styx.twitter.ConfigurationBuilderTwitter;
import com.lharo.styx.utils.ComboItem;
import com.lharo.styx.utils.CreateKMLgeoPath;

import twitter4j.TwitterException;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.TextArea;

import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JTextArea;

import java.nio.file.Path;
import java.nio.file.Paths;

public class HotelOffersCity {

	private JFrame frame;
	private static String city;
	private static HotelOffer[] hotelOffers;
	private static Integer index = 0;
	private static AmadeusAPI api; 
	private static String msg;
	/**
	 * Launch the application.
	 * @param city 
	 * @param cityCode 
	 */
	public static void main(String city, String cityCode) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//hotelOffers = hotels;
					//System.out.println(cityCode);
					api = new AmadeusAPI();
					HotelOffersCity window = new HotelOffersCity(city, cityCode);
					//if(hotel.length < 1)System.out.println("a");
					//city = hotelOffers;
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param city 
	 * @param string 
	 * @param string 
	 * @throws ResponseException 
	 */
	public HotelOffersCity(String city, String cityCode) throws ResponseException {
		//System.out.println(cityCode);
		initialize(cityCode, city);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param hotelOffers 
	 * @param city 
	 * @param hotel 
	 * @throws ResponseException 
	 */
	private void initialize(String cityCode, String city) throws ResponseException {
		//System.out.println(cityCode);
		/*HotelOffer[] hotel = api.getHotelOffersCity(cityCode);
		setHotelOffers(hotel);*/

		frame = new JFrame();
		frame.setBounds(100, 100, 450, 336);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel(city);
		lblNewLabel.setFont(new Font("Baskerville Old Face", Font.BOLD | Font.ITALIC, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(27, 11, 380, 44);
		frame.getContentPane().add(lblNewLabel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(122, 66, 191, 20);
		frame.getContentPane().add(comboBox);		

		JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 105, 414, 145);
		frame.getContentPane().add(textArea);
		textArea.setText(updateHotelShown(cityCode));
		JButton btnSiguiente = new JButton("View on Earth");
		btnSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Path currentRelativePath = Paths.get("");
				CreateKMLgeoPath flight = new CreateKMLgeoPath();
				//flight.writeFile(currentRelativePath, "ejemplo1", );
				
			}
		});
		btnSiguiente.setBounds(325, 263, 99, 23);
		frame.getContentPane().add(btnSiguiente);
		
		JButton button = new JButton("Tweet");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					TextOverlay to = new TextOverlay(msg);
					to.showImage(msg);
					Boolean success = false;
					while(!success) {
						try {
							File f = new File(to.getImagePath());
							ConfigurationBuilderTwitter cbt = new ConfigurationBuilderTwitter();
							cbt.createTweetWithImage("Check out the deal I got for: " + city , f);
							success = true;
						}catch(NullPointerException nep) {
							System.out.println("Retrying");
						}
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (TwitterException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		button.setBounds(27, 261, 99, 23);
		frame.getContentPane().add(button);
	}
	protected String updateHotelShown(String cityCode) throws ResponseException {
		String text = "";
		setHotelOffers(api.getHotelOffersCity(cityCode));
		if(getHotelOffers().length < 1) {
			text = "No hotel data available for " + cityCode;
		}else {
			if(index == null || index == getHotelOffers().length) index = 0;
			HotelOffer offer = getHotelOffers()[index];
			text = "Hotel " + offer.getHotel().getName() + "\n"
					+ "Rating " + offer.getHotel().getRating() + "\n" + 
					"Desc" + offer.getHotel().getDescription().getText() + "\n" +
					"Lat " + offer.getHotel().getLatitude() + " Lon " + offer.getHotel().getLongitude();					
		}
		msg = text;
		return text;
	}

	public static HotelOffer[] getHotelOffers() {
		return hotelOffers;
	}

	public static void setHotelOffers(HotelOffer[] hotelOffers) {
		HotelOffersCity.hotelOffers = hotelOffers;
	}
}
