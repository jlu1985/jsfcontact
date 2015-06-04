package com.brainfuse.contact.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import com.brainfuse.contact.dataaccess.BasicAccess;
import com.brainfuse.contact.models.Contact;
@ManagedBean(eager=true)
@ApplicationScoped
public class BaseAccessInMemoryImpl implements
		BasicAccess<Contact>, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6549720693403630961L;
	private Map<Long, Contact> contacts;
	private long id;
	
	public BaseAccessInMemoryImpl() {
		contacts = new HashMap<>();
	}

	@Override
	public boolean create(Contact t) {
		t.setContactId(++id);
		contacts.put(t.getContactId(), t);
		return true;
	}

	@Override
	public int update(Contact t) {
		return 0;
	}

	@Override
	public List<Contact> find() {
		return new ArrayList<Contact>(contacts.values());
	}

	@Override
	public int delete(long id) {
		if (contacts.remove(id) != null) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public int delete(long... ids) {
		int beforeSize = contacts.size();
		Arrays.stream(ids).forEach(id -> contacts.remove(id));
		int afterSize = contacts.size();
		return beforeSize-afterSize;
	}
}