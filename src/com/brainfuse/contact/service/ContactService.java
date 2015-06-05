package com.brainfuse.contact.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.facelets.FaceletContext;
import javax.servlet.jsp.PageContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brainfuse.contact.dataaccess.BasicAccess;
import com.brainfuse.contact.models.Contact;

@ManagedBean(name = "contactService", eager = true)
@ViewScoped
public class ContactService implements Serializable {
	
	private static final Logger logger = LoggerFactory.getLogger(ContactService.class);
	
	private static final long serialVersionUID = -6733222503219403705L;
	
	private static final String CURRENT_CONTACT = "currentContact";

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
		Contact c = getCurrentContact();
		logger.debug("updating current contact {}", c);
		if (c != null) {
			if (c.getContactId() <= 0) {
				logger.debug("Contact is a new Contact");
				newContact(c);
			} else {
				logger.debug("Contact is an existing contact");
				dao.update(c);
			}
			removeCurrentContact();
		}
	
	}
	
	public void setCurrentContact(Contact c) {
		logger.debug("Setting Current contact to {}",c);
		FacesContext context = FacesContext.getCurrentInstance();
		Map a = context.getViewRoot().getViewMap();
		context.getViewRoot().getViewMap().put(CURRENT_CONTACT, c);
	}

	public Contact getCurrentContact() {
		Contact c = (Contact) FacesContext.getCurrentInstance().getViewRoot().getViewMap().get(CURRENT_CONTACT);
		logger.debug("get current contact {}",c);
		return c;
	}
	private void removeCurrentContact(){
		FacesContext.getCurrentInstance().getViewRoot().getViewMap().remove(CURRENT_CONTACT);
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
		if (getCurrentContact() != null) {
			if (getCurrentContact().getContactId() <= 0)
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
