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
		/*dataset.addValue( 15 , "schools" , "1970" );
		dataset.addValue( 30 , "schools" , "1980" );
		dataset.addValue( 60 , "schools" ,  "1990" );
		dataset.addValue( 120 , "schools" , "2000" );
		dataset.addValue( 240 , "schools" , "2010" );
		dataset.addValue( 300 , "schools" , "2014" );*/
		for(FlightDate flight : flightdates) {
			
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
