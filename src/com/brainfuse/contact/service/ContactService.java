package com.brainfuse.contact.service;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.brainfuse.contact.dataaccess.BasicAccess;
import com.brainfuse.contact.models.Contact;

@ManagedBean(name = "contactService", eager = true)
@ViewScoped
public class ContactService implements Serializable {

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
		return dao.find();
	}

	public boolean newContact(Contact c) {
		return dao.create(c);
	}

	public void deleteContact(long id) {
		dao.delete(id);
	}

	public void updateCurrentContact() {
		if (currentContact != null) {
			if (currentContact.getContactId() <= 0) {
				newContact(currentContact);
			} else {
				dao.update(currentContact);
			}
			this.currentContact = null;
		}
	}

	public void setCurrentContact(Contact c) {
		currentContact = c;
	}

	public Contact getCurrentContact() {
		return currentContact;
	}

	public boolean hasContacts() {
		if (dao.find().size() > 0) {
			return true;
		}
		return false;

	}
}
