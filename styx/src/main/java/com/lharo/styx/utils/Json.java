package com.lharo.styx.utils;

import com.google.gson.Gson;

public class Json {
	
	private Gson gson = new Gson();

	public Gson getGson() {
		return gson;
	}
	public void setGson(Gson gson) {
		this.gson = gson;
	}
	
}
