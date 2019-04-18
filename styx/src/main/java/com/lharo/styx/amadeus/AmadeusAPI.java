package com.lharo.styx.amadeus;

import com.amadeus.Amadeus;
import com.amadeus.Params;
import com.amadeus.Shopping;
import com.amadeus.exceptions.ResponseException;
import com.amadeus.referenceData.Locations;
import com.amadeus.resources.FlightDate;
import com.amadeus.resources.Location;
import com.google.gson.Gson;
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
	/*public static void main(String[] args) throws ResponseException {
  		Gson gson = new Gson();
		Location[] locations = getCities();
	    for(Location loc : locations) {
		    System.out.println(gson.toJson(loc.getAddress().getCityName()));
	    }
	}*/
	
}