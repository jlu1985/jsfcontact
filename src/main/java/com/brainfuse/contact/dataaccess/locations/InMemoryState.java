package com.brainfuse.contact.dataaccess.locations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brainfuse.contact.dataaccess.BasicAccess;
import com.brainfuse.contact.models.locations.City;
import com.brainfuse.contact.models.locations.State;
import com.brainfuse.contact.models.locations.State.StateBuilder;

@ManagedBean(name="stateDao",eager=true)
@ApplicationScoped
public class InMemoryState implements BasicAccess<State> {
	
	private static final Logger logger = LoggerFactory.getLogger(InMemoryState.class);
	private Map<Long,State> states;
	
	public InMemoryState() {
		states = new HashMap<Long, State>();
		
		addMocks();
	}
	
	private void addMocks() {
		City cityA = new City(1L,"City A");
		City cityB = new City(2L, "City B");
		City cityC = new City(3L, "City C");
		City cityD = new City(4L, "City D");
		City cityE = new City(5L, "City E");
		City cityF = new City(6L, "City F");
		states.put(1L, StateBuilder.getInstance().id(1).name("State A").shortName("State A").addCities(cityA,cityB).build());
		states.put(2L, StateBuilder.getInstance().id(2).name("State B").shortName("State B").addCities(cityC,cityD,cityE).build());
		states.put(3L, StateBuilder.getInstance().id(3).name("State C").shortName("State C").addCities(cityF).build());
	}
	
	@Override
	public boolean create(State t) {
		return false;
	}

	@Override
	public int update(State t) {
		return 0;
	}

	@Override
	public List<State> find() {
		logger.debug("find states {}",states);
		return new ArrayList<State>(states.values());
	}

	@Override
	public int delete(long id) {
		return 0;
	}

	@Override
	public int delete(long... ids) {
		return 0;
	}
	
	@PostConstruct
	public void init(){
		logger.debug("{} created by container",this);
	}

	@Override
	public State findById(long id) {
		return states.get(id);
	}
	

}
