package com.brainfuse.contact.service;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.brainfuse.contact.dataaccess.BasicAccess;
import com.brainfuse.contact.models.Contact;

@ManagedBean(name = "contactService")
@ViewScoped
public class ContactService implements Serializable {

	private static final long serialVersionUID = -6733222503219403705L;
	
	@ManagedProperty(value="#{baseAccessInMemoryImpl}")
	private BasicAccess<Contact> dao;

	public BasicAccess<Contact> getDao() {
		return dao;
	}

	public void setDao(BasicAccess<Contact> dao) {
		this.dao = dao;
	}

	public ContactService() {
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
