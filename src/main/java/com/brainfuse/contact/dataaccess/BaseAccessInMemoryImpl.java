package com.brainfuse.contact.dataaccess;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brainfuse.contact.models.Contact;
@ManagedBean(eager=true)
@ApplicationScoped
public class BaseAccessInMemoryImpl implements
		BasicAccess<Contact>, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6549720693403630961L;
	private static final Logger logger = LoggerFactory.getLogger(BaseAccessInMemoryImpl.class);
	private Map<Long, Contact> contacts;
	private long id;
	
	public BaseAccessInMemoryImpl() {
		contacts = new HashMap<>();
	}

	@Override
	public boolean create(Contact t) {
		logger.debug("dao calls: create");
		t.setContactId(++id);
		contacts.put(t.getContactId(), t);
		return true;
	}

	@Override
	public int update(Contact t) {
		logger.debug("dao calls: update");
		return 0;
	}

	@Override
	public List<Contact> find() {
		logger.debug("dao calls: find all");
		return new ArrayList<Contact>(contacts.values());
	}

	@Override
	public int delete(long id) {
		logger.debug("dao calls: delete {}",id);
		if (contacts.remove(id) != null) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public int delete(long... ids) {
		logger.debug("dao calls: delete all {}",ids);
		int beforeSize = contacts.size();
		for (long id:ids){
			contacts.remove(id);
		}
		int afterSize = contacts.size();
		return beforeSize-afterSize;
	}
	
	@PostConstruct
	public void init(){
		logger.debug("{} is created by container",this);
	}

	@Override
	public Contact findById(long id) {
		logger.debug("Finding contact id {}",id);
		Contact c = contacts.get(id);
		logger.debug("Returning {}",c);
		return c;
	}
}