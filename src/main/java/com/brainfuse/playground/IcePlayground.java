package com.brainfuse.playground;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.LongStream;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean(name="ice")
@ViewScoped
public class IcePlayground {
	
	private final static Logger logger = LoggerFactory.getLogger(IcePlayground.class);


	public void setIcecubes(Map<Long, Icecube> icecubes) {
		logger.debug("Set icecubes {}", icecubes);
		this.icecubes = icecubes;
	}
	
	public List<Icecube> getIcecubes() {
		logger.debug("Get icecubes {}",icecubes);
		return new ArrayList<>(icecubes.values());
	}
	
	
	
	private Map<Long,Icecube> icecubes;
	
	public IcePlayground() {
		icecubes = new HashMap<>();
		
		for (int i = 0; i<21;i++){
			icecubes.put(new Long(i), new Icecube(i,3.14*i,"Text "+i));
		}
		
	}
	
	
}


