package com.brainfuse.contact.service;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.brainfuse.contact.dataaccess.BasicAccess;
import com.brainfuse.contact.models.Contact;
import com.brainfuse.contact.models.locations.Address;
import com.brainfuse.contact.models.locations.State;

@ManagedBean(name = "addressService")
@ViewScoped
public class AddressService implements Serializable {

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
		return states;
	}

	public void setStates(List<State> states) {
		this.states = states;
	}
	
	
	@PostConstruct
	public void init(){
		states = stateDao.find();
	}
}
