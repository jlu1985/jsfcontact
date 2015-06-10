package com.brainfuse.contact.models.locations;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brainfuse.contact.service.AddressService;

@ManagedBean
@RequestScoped
public class StateConverter implements Converter {

	
	private static final Logger logger = LoggerFactory.getLogger(StateConverter.class);
	@ManagedProperty(value = "#{addressService}")
	private AddressService addressService;

	public AddressService getAddressService() {
		return addressService;
	}

	public void setAddressService(AddressService addressService) {
		this.addressService = addressService;
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent ui, String value) {
		logger.debug("Converting {} to object",value);
		if (value == null){ return null;}
		for (State state : addressService.getStates()) {

			if (state.getName().equals(value)) {
				logger.debug("Found object {} that matches {}",state,value);
				logger.debug("Returning {}",state);
				return state;
			}
		}
		return null;
		
	}

	@Override
	public String getAsString(FacesContext context, UIComponent ui, Object obj) {
		logger.debug("Converting {} to String",obj);
		if (obj==null) return null;
		
		return obj.toString();
	}
	
	@PostConstruct
	public void init(){
		logger.debug("{} is created by container",this);
		logger.debug("addressService is loaded with {}", addressService);
	}

}
