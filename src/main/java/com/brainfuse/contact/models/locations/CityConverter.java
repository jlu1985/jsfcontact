package com.brainfuse.contact.models.locations;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import com.brainfuse.contact.dataaccess.BasicAccess;

@ManagedBean(name="cityConverter")
@RequestScoped
public class CityConverter implements Converter{
	
	private static final Logger logger = LoggerFactory.getLogger(CityConverter.class);
	
	@ManagedProperty("#{cityDao}")
	private BasicAccess<City> cityDao;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		
		
		
		logger.debug("Converting {} to object",value);
		logger.debug("Getting component attribute State");
		Object  obj = component.getAttributes().get("state");
		
		List<City> cities;
		if (obj instanceof State){
			logger.debug("Component attribute has a state object {}", obj);
			State state = (State) obj;
			cities = state.getCities();
		}
	
		if(value== null){
			return null;
		}
		
		for (City city:cityDao.find()){
			if (city.toString().equals(value)){
				logger.debug("returning {}",city);
				return city;
			}
				
		}
		logger.debug("Didn't find anything returning null");
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		logger.debug("converter value {} to String",value);
		if (value==null)return null;
		return value.toString();
	}
	
	@PostConstruct
	public void init(){
		logger.debug("{} is created by container",this);
		logger.debug("dao is loaded with {}",cityDao);
	}

	public BasicAccess<City> getCityDao() {
		return cityDao;
	}

	public void setCityDao(BasicAccess<City> cityDao) {
		this.cityDao = cityDao;
	}

}
