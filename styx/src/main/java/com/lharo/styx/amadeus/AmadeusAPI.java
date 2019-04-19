package com.lharo.styx.amadeus;

import com.amadeus.Amadeus;
import com.amadeus.Params;
import com.amadeus.exceptions.ResponseException;
import com.amadeus.referenceData.Locations;
import com.amadeus.resources.AirTraffic;
import com.amadeus.resources.FlightDate;
import com.amadeus.resources.FlightDestination;
import com.amadeus.resources.HotelOffer;
import com.amadeus.resources.Location;
import com.lharo.styx.utils.Json;

public class AmadeusAPI extends Json {
	
	public Location[] getAirportsByKeyword(String keyword) throws ResponseException {
		Amadeus amadeus = Amadeus
	            .builder("REeZALAU0zdDojx31JBHiqsohCfWvsIT", "X1F75E0mYbasdM4f")
	            .build();

	    Location[] locations = amadeus.referenceData.locations.get(Params
	      .with("keyword", keyword)
	      .and("subType", Locations.ANY));
	    return locations;
	}	
	
	public static Location[] getCities(String search) throws ResponseException {
		Amadeus amadeus = Amadeus
	            .builder("REeZALAU0zdDojx31JBHiqsohCfWvsIT", "X1F75E0mYbasdM4f")
	            .build();

	    Location[] locations = amadeus.referenceData.locations.get(Params
	      .with("subType", Locations.CITY)
	      .and("keyword", search));
	    return locations;		
	}

	public static FlightDate[] getFlightDates(String from, String to) throws ResponseException {
		Amadeus amadeus = Amadeus
	            .builder("REeZALAU0zdDojx31JBHiqsohCfWvsIT", "X1F75E0mYbasdM4f")
	            .build();
		FlightDate[] flights = amadeus.shopping.flightDates.get(Params.with
				("origin", to)
				.and("destination", from));		
	    return flights;		
		
	}
	public static HotelOffer[] getHotelOffersCity(String cityCode) throws ResponseException {
		Amadeus amadeus = Amadeus
	            .builder("REeZALAU0zdDojx31JBHiqsohCfWvsIT", "X1F75E0mYbasdM4f")
	            .build();
		HotelOffer[] offers = amadeus.shopping.hotelOffers.get(Params.with("cityCode", cityCode));
		return offers;
	}

	public static HotelOffer getHotelDetailsById(String hotelId) throws ResponseException{
		Amadeus amadeus = Amadeus
	            .builder("REeZALAU0zdDojx31JBHiqsohCfWvsIT", "X1F75E0mYbasdM4f")
	            .build();		
		HotelOffer hotelOffer = amadeus.shopping.hotelOffersByHotel.get(Params.with("hotelId", hotelId));
		return hotelOffer;
	}
	
	public static AirTraffic[] getMostTraveledFromByCityAndDate(String cityCode, String dateMonthYear) throws ResponseException{
		Amadeus amadeus = Amadeus
	            .builder("REeZALAU0zdDojx31JBHiqsohCfWvsIT", "X1F75E0mYbasdM4f")
	            .build();		
		AirTraffic[] airTraffics = amadeus.travel.analytics.airTraffic.traveled.get(Params.with("originCityCode", cityCode).and("period", dateMonthYear));
		return airTraffics;
	}
	
	public static FlightDestination[] getCheapestDestinationsFromCity(String cityCode) throws ResponseException {
		Amadeus amadeus = Amadeus
	            .builder("REeZALAU0zdDojx31JBHiqsohCfWvsIT", "X1F75E0mYbasdM4f")
	            .build();		
		FlightDestination[] flightDestinations = amadeus.shopping.flightDestinations.get(Params.with("origin", cityCode));
		return flightDestinations;
	}
}