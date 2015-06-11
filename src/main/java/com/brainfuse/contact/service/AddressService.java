package com.brainfuse.contact.service;

import java.io.Serializable;
import java.util.ArrayList;
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
import com.brainfuse.contact.models.locations.City;
import com.brainfuse.contact.models.locations.State;

@ManagedBean(name = "addressService")
@ViewScoped
public class AddressService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2845903386206287771L;
	private final static Logger logger = LoggerFactory
			.getLogger(AddressService.class);
	
	@ManagedProperty(value = "#{stateDao}")
	private BasicAccess<State> stateDao;

	public BasicAccess<State> getStateDao() {
		return stateDao;
	}

	public void setStateDao(BasicAccess<State> stateDao) {
		this.stateDao = stateDao;
	}

	private List<State> states;

	public List<State> getStates() {
		logger.debug("get states {}", states);
		return states;
	}

	public List<String> getStatesAsString() {
		List<String> result = new ArrayList<>();
		for (State state : getStates()) {
			result.add(state.getName());
		}
		return result;
	}

	public List<SelectItem> getStatesAsSelectItems(){
		
		List<SelectItem> result = new ArrayList<>();
		
		for (State state:getStates()){
			
			result.add(new SelectItem(state.getName()));
		}
		return result;
	}

	public void setStates(List<State> states) {
		logger.debug("set states {}", states);
		this.states = states;
	}

	public List<String> getCities(String state) {
		for (State el : getStates()) {
			if (el.getName().equals(state)) {

				List<String> result = new ArrayList<>();
				for (City city : el.getCities()) {
					result.add(city.getName());
				}

				return result;

			}
		}
		return null;
	}
	
	public List<SelectItem> getCitiesAsSelectItem(String state){
		logger.debug("get cities to dispaly for {}",state);
		for (State el : getStates()) {
			if (el.getName().equals(state)) {

				List<SelectItem> result = new ArrayList<>();
				for (City city : el.getCities()) {
					result.add(new SelectItem(city.getName()));
				}
				logger.debug("returning display items {}",result);
				return result;

			}
		}
		logger.debug("no match, return null");
		return null;
	}

	@PostConstruct
	public void init() {
		logger.debug("{} is created by the container", this);
		logger.debug("stateDao is loaded with {}", stateDao);
		states = stateDao.find();
	}
}
