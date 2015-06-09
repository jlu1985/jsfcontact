package com.brainfuse.contact.dataaccess.locations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.LongStream;

import com.brainfuse.contact.dataaccess.BasicAccess;
import com.brainfuse.contact.models.locations.City;

public class InMemoryCity implements BasicAccess<City>{
	
	private Map<Long, City> cities;
	
	public InMemoryCity() {
		cities = new HashMap<>();
		
		addMocks();
	}
	private void addMocks(){
		LongStream.rangeClosed(1L, 10L).forEach(key -> cities.put(key, new City(key, "City " + key)));
	}
	@Override
	public boolean create(City t) {
		return false;
	}

	@Override
	public int update(City t) {
		return 0;
	}

	@Override
	public List<City> find() {
		return new ArrayList<City>(cities.values());
	}

	@Override
	public int delete(long id) {
		return 0;
	}

	@Override
	public int delete(long... ids) {
		return 0;
	}

}
