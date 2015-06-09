package com.brainfuse.contact.models.locations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.brainfuse.contact.service.ModelContants;

public class State {

	@Override
	public String toString() {
		return String.format(
				"State [stateId=%s, name=%s, shortName=%s, cities=%s]",
				stateId, name, shortName, cities);
	}

	private final int stateId;
	private final String name;
	private final String shortName;
	
	private List<City> cities;

	private State(StateBuilder builder) {
		this.stateId = builder.getStateId();
		this.name = builder.getName();
		this.shortName = builder.getShortName();
		this.cities = builder.getCities();
	}
	
	public List<City> getCities(){
		return Collections.unmodifiableList(cities);
	}

	public boolean isNew() {
		return stateId == ModelContants.DEFAULT_ID;
	}

	public int getStateId() {
		return stateId;
	}

	public  String getName() {
		return name;
	}

	public synchronized String getShortName() {
		return shortName;
	}

	public static class StateBuilder {
		private int stateId = ModelContants.DEFAULT_ID;
		private String name;
		private String shortName;
		private List<City> cities;
		
		public static StateBuilder getInstance() {
			return new StateBuilder();
		}

		
		public StateBuilder id(int stateId){
			this.stateId = stateId;
			return this;
		}
		public StateBuilder name(String name) {
			this.name = name;
			return this;
		}

		public StateBuilder shortName(String shortName) {
			this.shortName = shortName;
			return this;
		}

		public StateBuilder addCity(City city){
			getCities().add(city);
			return this;
		}
		
		public StateBuilder addCities(City... cities){
			getCities().addAll(Arrays.asList(cities));
			return this;
		}
		
		public State build() {
			return new State(this);
		}
		
		private List<City> getCities() {
			if (cities != null){
				return cities;
			} else {
				cities = new ArrayList<>();
				return cities;
			}
		}
		private String getName(){
			return  StringUtils.isBlank(this.name) ? StringUtils.EMPTY
					: StringUtils.normalizeSpace(name);
		}
		
		private String getShortName(){
			return StringUtils.isBlank(this.shortName) ? StringUtils.EMPTY
					: StringUtils.normalizeSpace(shortName);
		}
		
		private int getStateId(){
			return (stateId >= 0)?stateId:ModelContants.DEFAULT_ID;
		}
		

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((shortName == null) ? 0 : shortName.hashCode());
		result = prime * result + stateId;
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
		State other = (State) obj;
		if (stateId != other.stateId)
			return false;
		return true;
	}
}
