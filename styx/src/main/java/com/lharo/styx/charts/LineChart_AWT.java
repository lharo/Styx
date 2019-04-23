package com.lharo.styx.charts;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import com.amadeus.resources.FlightDate;

public class LineChart_AWT extends ApplicationFrame {

	private static final long serialVersionUID = -8102339579418472182L;

	public LineChart_AWT( String applicationTitle , String chartTitle, FlightDate[] flightdates ) {
		super(applicationTitle);
		JFreeChart lineChart = ChartFactory.createLineChart(
				chartTitle,
				"Date","Price",
				createDataset(flightdates),
				PlotOrientation.VERTICAL,
				true,true,false);

		ChartPanel chartPanel = new ChartPanel( lineChart );
		chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
		setContentPane( chartPanel );
	}

	private DefaultCategoryDataset createDataset(FlightDate[] flightdates ) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
		Integer cont = 0;
		for(FlightDate flight : flightdates) {
			//if(cont < 200) {
				dataset.addValue( flight.getPrice().getTotal() , "Date" , flight.getDepartureDate() );
				cont++;
			//}
		}
		return dataset;
	}

	public void showGraph(String applicationTitle , String chartTitle, FlightDate[] flightdates) {
		LineChart_AWT chart = new LineChart_AWT(chartTitle, chartTitle, flightdates);
		chart.pack( );
		RefineryUtilities.centerFrameOnScreen( chart );
		chart.setVisible( true );
	}
}
