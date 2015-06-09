package com.brainfuse.contact.models.locations;

import java.io.Serializable;

import com.brainfuse.contact.service.ModelContants;

public class City implements Serializable{

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (cityId ^ (cityId >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		City other = (City) obj;
		if (cityId != other.cityId)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
