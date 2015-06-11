package com.brainfuse.contact.service;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brainfuse.contact.dataaccess.BasicAccess;
import com.brainfuse.contact.models.locations.State;

@ManagedBean(name = "addressService")
@ViewScoped
public class AddressService implements Serializable {
	
	private final static Logger logger = LoggerFactory.getLogger(AddressService.class);
	@ManagedProperty(value="#{stateDao}")
	private BasicAccess<State> stateDao;
		
	
	public BasicAccess<State> getStateDao() {
		return stateDao;
	}

	public void setStateDao(BasicAccess<State> stateDao) {
		this.stateDao = stateDao;
	}


	private List<State> states;
	
	public List<State> getStates() {
		logger.debug("get states {}",states);
		return states;
	}
	
	public List<String> getStatesAsString(){
		return getStates().stream().map(e->e.getName()).collect(Collectors.toList());
	}
	
	public List<SelectItem> getStatesAsSelectItems(){
		
		return getStates().stream().map(state->new SelectItem(state, state.getName())).collect(Collectors.toList());
	}
	
	public void setStates(List<State> states) {
		logger.debug("set states {}",states);
		this.states = states;
	}
	
	public List<String> getCities(String state) {
			for (State el : getStates()){
					if (el.getName().equals(state))
						return el.getCities().stream().map(city->city.getName()).collect(Collectors.toList());
			}
			return null;
	}
	
	
	@PostConstruct
	public void init(){
		logger.debug("{} is created by the container", this);
		logger.debug("stateDao is loaded with {}",stateDao);
		states = stateDao.find();
		logger.debug("states is loaded with {} ",states);
	}
}
