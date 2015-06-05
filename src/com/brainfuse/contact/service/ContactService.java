package com.brainfuse.contact.service;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brainfuse.contact.dataaccess.BasicAccess;
import com.brainfuse.contact.models.Contact;

@ManagedBean(name = "contactService", eager = true)
@ViewScoped
public class ContactService implements Serializable {
	
	private static final Logger logger = LoggerFactory.getLogger(ContactService.class);
	
	private static final long serialVersionUID = -6733222503219403705L;
	private Contact currentContact;

	@ManagedProperty(value = "#{baseAccessInMemoryImpl}")
	private BasicAccess<Contact> dao;

	public BasicAccess<Contact> getDao() {
		return dao;
	}
	
	
	public void setDao(BasicAccess<Contact> dao) {
		this.dao = dao;
	}

	public List<Contact> getContacts() {
		logger.debug("get all contacts from dao");
		return dao.find();
	}

	public boolean newContact(Contact c) {
		logger.debug("add contact using dao");
		return dao.create(c);
	}

	public void deleteContact(long id) {
		logger.debug("deleting contact id {}",id);
		dao.delete(id);
	}

	public void updateCurrentContact() {
		logger.debug("updating current contact {}", currentContact);
		if (currentContact != null) {
			if (currentContact.getContactId() <= 0) {
				logger.debug("Contact is a new Contact");
				newContact(currentContact);
			} else {
				logger.debug("Contact is an existing contact");
				dao.update(currentContact);
			}
			currentContact = null;
		}
	}
	
	public String setCurrentContact(){
		logger.debug("Setting new contact to current Contact");
		currentContact = new Contact();
		return null;
	}
	
	public void setCurrentContact(Contact c) {
		logger.debug("Setting Current contact to {}",c);
		currentContact = c;
	}

	public Contact getCurrentContact() {
		logger.debug("get current contact {}",currentContact);
		return currentContact;
	}

	public boolean hasContacts() {
		if (dao.find().size() > 0) {
			logger.debug("has contacts");
			return true;
		}
		logger.debug("has no contacts");
		return false;

	}

	public String newOrEditTitle() {
		logger.debug("getting new or edit form title");
		if (currentContact != null) {
			if (currentContact.getContactId() <= 0)
				
				return "New Contact";
			else
				return "Edit Contact";

		} else
			return "";
	}
	
	@PostConstruct
	public void init(){
		logger.debug("{} is created by container",this);
		logger.debug("Dao object: {}", dao);
		
	}
	@PreDestroy
	public void preDestroy(){
		logger.debug("{} is in pre destroy", this);
	}
}
