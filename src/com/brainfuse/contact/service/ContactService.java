package com.brainfuse.contact.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brainfuse.contact.dataaccess.BasicAccess;
import com.brainfuse.contact.models.Contact;

@ManagedBean(name = "contactService", eager = true)
@ViewScoped
public class ContactService implements Serializable {

	private static final Logger logger = LoggerFactory
			.getLogger(ContactService.class);

	private static final long serialVersionUID = -6733222503219403705L;

	// private static final String CURRENT_CONTACT = "currentContact";

	@ManagedProperty(value = "#{baseAccessInMemoryImpl}")
	private BasicAccess<Contact> dao;

	private Contact currentContact;

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

	public void createNewContact(Contact c) {
		logger.debug("add contact using dao");
		dao.create(c);
	}

	public void deleteContact(long id) {
		logger.debug("deleting contact id {}", id);
		dao.delete(id);
	}

	public void updateCurrentContact() {
		Contact c = getCurrentContact();
		logger.debug("updating current contact {}", c);
		if (c != null) {
			if (c.getContactId() <= 0) {
				logger.debug("Contact is a new Contact");
				createNewContact(c);
			} else {
				logger.debug("Contact is an existing contact");
				dao.update(c);
			}
			removeCurrentContact();
		}

	}

	public String setCurrentContact(Contact c) {
		logger.debug("Setting Current contact to {}", c);
		this.currentContact = c;
		return null;
	}

	public Contact getCurrentContact() {
		return this.currentContact;
	}

	private void removeCurrentContact() {
		this.currentContact = null;
	}
	
	public void cancel(){
		removeCurrentContact();
	}
	
	public boolean hasContacts() {
		if (dao.find().size() > 0) {
			logger.debug("has contacts");
			return true;
		}
		logger.debug("has no contacts");
		return false;
	}

	@PostConstruct
	public void init() {
		logger.debug("{} is created by container", this);
		logger.debug("Dao object: {}", dao);

	}

	@PreDestroy
	public void preDestroy() {
		logger.debug("{} is in pre destroy", this);
	}

	public void newContact() {
		this.currentContact = new Contact();
	}

}
