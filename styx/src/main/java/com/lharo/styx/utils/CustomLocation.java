package com.lharo.styx.utils;

import com.amadeus.resources.Location.GeoCode;

public class CustomLocation {
	private String toValue;
	private String toFull;
	private String fromValue;
	private String fromFull;
	private GeoCode fromGeo;	
	private GeoCode toGeo;
	
	public String getToValue() {
		return toValue;
	}
	public void setToValue(String toValue) {
		this.toValue = toValue;
	}
	public String getToFull() {
		return toFull;
	}
	public void setToFull(String toFull) {
		this.toFull = toFull;
	}
	public String getFromValue() {
		return fromValue;
	}
	public void setFromValue(String fromValue) {
		this.fromValue = fromValue;
	}
	public String getFromFull() {
		return fromFull;
	}
	public void setFromFull(String fromFull) {
		this.fromFull = fromFull;
	}
	public GeoCode getFromGeo() {
		return fromGeo;
	}
	public void setFromGeo(GeoCode fromGeo) {
		this.fromGeo = fromGeo;
	}
	public GeoCode getToGeo() {
		return toGeo;
	}
	public void setToGeo(GeoCode toGeo) {
		this.toGeo = toGeo;
	}
}
