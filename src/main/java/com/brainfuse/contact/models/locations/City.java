package com.brainfuse.contact.models.locations;

import com.brainfuse.contact.service.ModelContants;

public class City {

	@Override
	public String toString() {
		return String.format("City [cityId=%s, name=%s]", cityId, name);
	}

	public long getCityId() {
		return cityId;
	}

	public String getName() {
		return name;
	}

	private final long cityId;
	private final String name;

	public City(long cityId, String name) {
		this.cityId = cityId;
		this.name = name;
	}

	public City(String name) {
		this.cityId = ModelContants.DEFAULT_ID;
		this.name = name;
	}
}
