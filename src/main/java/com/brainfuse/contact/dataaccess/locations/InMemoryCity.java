package com.brainfuse.contact.dataaccess.locations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.LongStream;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import com.brainfuse.contact.dataaccess.BasicAccess;
import com.brainfuse.contact.models.locations.City;

@ManagedBean(name="cityDao", eager=true)
@ApplicationScoped
public class InMemoryCity implements BasicAccess<City>{
	
	private Map<Long, City> cities;
	
	public InMemoryCity() {
		cities = new HashMap<>();
		
		addMocks();
	}
	private void addMocks(){
		City cityA = new City(1L,"City A");
		City cityB = new City(2L, "City B");
		City cityC = new City(3L, "City C");
		City cityD = new City(4L, "City D");
		City cityE = new City(5L, "City E");
		City cityF = new City(6L, "City F");
		
		cities.put(cityA.getCityId(), cityA);
		cities.put(cityB.getCityId(), cityB);
		cities.put(cityC.getCityId(), cityC);
		cities.put(cityD.getCityId(), cityD);
		cities.put(cityE.getCityId(), cityE);
		cities.put(cityF.getCityId(), cityF);
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
	public City findById(long id) {
		return cities.get(id);
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
