package com.brainfuse.contact.service;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brainfuse.contact.dataaccess.BasicAccess;
import com.brainfuse.contact.dataaccess.InMemoryRelationship;
import com.brainfuse.contact.models.Contact;
import com.brainfuse.contact.models.Relationship;
import com.brainfuse.contact.models.locations.Address;

@ManagedBean(name = "contactService", eager = true)
@ViewScoped
public class ContactService implements Serializable {

	private static final Logger logger = LoggerFactory
			.getLogger(ContactService.class);

	private static final long serialVersionUID = -6733222503219403705L;

	// private static final String CURRENT_CONTACT = "currentContact";

	@ManagedProperty(value = "#{baseAccessInMemoryImpl}")
	private BasicAccess<Contact> dao;

	@ManagedProperty(value = "#{relationshipDao}")
	private BasicAccess<Relationship> relDao;

	public BasicAccess<Relationship> getRelDao() {
		return relDao;
	}

	public void setRelDao(BasicAccess<Relationship> relDao) {
		this.relDao = relDao;
	}

	private Contact currentContact;
	private Relationship currentRelationship;

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

	public List<Contact> getAvailableContacts(Relationship selectedRel) {
		List<Relationship> relationships = getCurrentContact()
				.getRelationships();
		logger.debug("filtering all contacts from {}", relationships);
		List<Contact> allContacts = getContacts();

		List<Long> relatedIds = relationships.stream()
				.map(rel -> rel.getToContactId()).collect(Collectors.toList());

		if (relationships != null && relationships.size() > 0) {
			List<Contact> contacts = allContacts
					.stream()
					.filter(contact -> (!relatedIds.contains(contact
							.getContactId()) && contact.getContactId() != getCurrentContact()
							.getContactId())
							|| contact.getContactId() == selectedRel
									.getToContactId())
					.collect(Collectors.toList());
			return contacts;
		}

		else
			return allContacts;

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

			List<Relationship> rels = c.getRelationships();

			if (c.isNewContact()) {
				logger.debug("Contact is a new Contact");
				createNewContact(c);
				rels.forEach(rel -> {
					rel.setOwnerId(c.getContactId());
					if (rel.isNew()) {
						relDao.create(rel);
					} else {
						relDao.update(rel);
					}
				});

			}

			else {
				rels.forEach(rel -> {
					if (rel.isNew()) {
						relDao.create(rel);
					} else {
						relDao.update(rel);
					}
				});
				c.setRelationships(rels);
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

	public void cancel() {
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
		logger.debug("{} is loaded into dao", dao);
		logger.debug("{} is loaded into relDao", relDao);
	}

	@PreDestroy
	public void preDestroy() {
		logger.debug("{} is in pre destroy", this);
	}

	public void newContact() {
		this.currentContact = new Contact();
	}

	public void addAddress(Contact c) {
		c.addAddress(new Address());
	}

	public void removeLastAddress(Contact c) {
		c.removeLastAddress();
	}

	public Relationship getCurrentRelationship() {
		return currentRelationship;
	}

	public void setCurrentRelationship(Relationship currentRelationship) {
		logger.debug("Setting currentRelationship to object {}",
				currentRelationship);
		this.currentRelationship = currentRelationship;
	}

}
