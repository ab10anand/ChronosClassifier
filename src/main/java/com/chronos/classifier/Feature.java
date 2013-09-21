package com.chronos.classifier;

import java.util.Arrays;
import java.util.List;

public enum Feature {
	_3G("3G","on,off"),
	WIFI("WIFI", "on,off"),
	HEADSET("HEADSET", "in,out"),
	BLUETOOTH("BLUETOOTH","on,off"),
	CAMERA("CAMERA", "on,off"),
	AIRPLANEMODE("AIRPLANEMODE", "on,off"),
	HOURS("HOURS","00,01,02,03,04,05");
	
	private String name;
	private List<String> values;
	Feature(String name, String values) {
		this.name = name;
		this.values = Arrays.asList(values.split(","));
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getValues() {
		return values;
	}
	public void setValues(List<String> values) {
		this.values = values;
	}
}
