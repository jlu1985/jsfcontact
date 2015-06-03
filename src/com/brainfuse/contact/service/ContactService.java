package com.brainfuse.contact.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.brainfuse.contact.dataaccess.BasicAccess;
import com.brainfuse.contact.models.Contact;

@ManagedBean(name = "contactService")
@SessionScoped
public class ContactService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6733222503219403705L;
	private final BasicAccess<Contact> dao;

	public ContactService() {
		dao = new BasicAccess<Contact>() {
			
			private Map<Long, Contact> contacts = new HashMap<>();
			private long id;
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
		};
	}

	public List<Contact> getContacts() {
		return dao.find();
	}

	public boolean newContact(Contact c) {
		return dao.create(c);
	}

	public void deleteContact(long id) {
		dao.delete(id);
	}
}
